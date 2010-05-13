package com.tomecode.soa.oracle10g.parser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.tomecode.soa.oracle10g.MultiWorkspace;
import com.tomecode.soa.oracle10g.Workspace;
import com.tomecode.soa.oracle10g.bpel.BpelProject;
import com.tomecode.soa.oracle10g.bpel.PartnerLinkBinding;
import com.tomecode.soa.oracle10g.esb.BasicEsbNode;
import com.tomecode.soa.oracle10g.esb.EsbGrp;
import com.tomecode.soa.oracle10g.esb.EsbProject;
import com.tomecode.soa.oracle10g.esb.EsbSvc;
import com.tomecode.soa.oracle10g.esb.EsbSys;
import com.tomecode.soa.process.Project;
import com.tomecode.soa.process.ProjectType;

/**
 * 
 * Parser for file with extension .jws
 * 
 * @author Tomas Frastia
 * 
 */
public final class MultiWorkspaceParser extends AbstractParser {

	/**
	 * bpel parser
	 */
	private final BpelParser bpelParser;

	/**
	 * esb parser
	 */
	private final EsbParser esbParser;

	/**
	 * Constructor
	 */
	public MultiWorkspaceParser() {
		bpelParser = new BpelParser();
		esbParser = new EsbParser();
	}

	/**
	 * parse {@link MultiWorkspace}
	 * 
	 * @param workspaceFolder
	 * @return
	 * @throws ServiceParserException
	 */
	public final MultiWorkspace parse(File workspaceFolder) throws ServiceParserException {
		List<File> jwsFiles = new ArrayList<File>();
		findAllJws(workspaceFolder, jwsFiles);

		MultiWorkspace multiWorkspace = new MultiWorkspace(workspaceFolder);

		for (File jwsFile : jwsFiles) {

			// create new workspace
			Workspace workspace = new Workspace(jwsFile);
			multiWorkspace.addWorkspace(workspace);
			try {
				// load list of projects from jws
				List<File> jwsProjectFiles = parseListOfProjectsFromJWS(parseXml(jwsFile), jwsFile.getParentFile());

				// parse all projects from jws and return list this process
				List<File> listOfParsedBpelEsb = parseBpelEsbProjects(jwsProjectFiles, workspace, true);

				// parse all projects from file system wiche not are in jws
				List<File> fsProjects = new ArrayList<File>();
				findBpelEsbProjectFromFS(listOfParsedBpelEsb, fsProjects, jwsFile.getParentFile());
				parseBpelEsbProjects(fsProjects, workspace, false);
			} catch (ServiceParserException e) {
				multiWorkspace.addException(jwsFile, e);
			}

		}

		for (Workspace workspace : multiWorkspace.getWorkspaces()) {
			analysesBpelDependencies(workspace);
			analysesEsbDependencies(workspace);
			analysesDepBetweenBpelEsb(workspace);
		}

		return multiWorkspace;
	}

	private final void analysesDepBetweenBpelEsb(Workspace workspace) {
		for (Project project : workspace.getProjects()) {
			if (project.getType() == ProjectType.ORACLE10G_BPEL) {
				BpelProject bpelProject = (BpelProject) project;
				for (PartnerLinkBinding partnerLinkBinding : bpelProject.getPartnerLinkBindings()) {
					if (partnerLinkBinding.getDependencyProject() == null) {

						URL urlWsdl = parseWsdlToUrl(partnerLinkBinding.getWsdlLocation());
						if (urlWsdl != null) {
							String qName = esbParser.convertWsdlToQname(urlWsdl);
							if (qName != null) {
								Project qNameProject = findEsbProjectByQname(qName, urlWsdl, workspace);
								if (qNameProject != null) {
									partnerLinkBinding.setDependencyProject(qNameProject);
								}
							}

						}

					}
				}
			} else if (project.getType() == ProjectType.ORACLE10G_ESB) {
				EsbProject esbProject = (EsbProject) project;

				List<EsbSvc> esbSvcs = esbProject.getAllEsbSvc();
				for (EsbSvc esbSvc : esbSvcs) {
					URL url = parseWsdlToUrl(esbSvc.getWsdlURL());
					if (url != null) {
						if (url.getFile().startsWith("/orabpel")) {
							BpelProject bpelProject = findBpelProjectForEsb(workspace, url.getFile());
							if (bpelProject != null) {
								esbSvc.getOwnerEsbProject().addDependency(bpelProject);
							}

						}
					}
				}
			}
		}

	}

	private final BpelProject findBpelProjectForEsb(Workspace workspace, String url) {
		int index = url.indexOf("?wsdl");
		if (index != -1) {
			url = url.replace("?", ".");

			index = url.lastIndexOf("/");
			if (index != -1) {
				url = url.substring(0, index);
				String processName = url.substring(url.lastIndexOf("/") + 1, index);
				return findBpelByName(workspace, processName);
			}

		}
		return null;
	}

	private final BpelProject findBpelByName(Workspace workspace, String bpelProcessName) {
		for (Project project : workspace.getProjects()) {
			if (project.getType() == ProjectType.ORACLE10G_BPEL) {
				BpelProject bpelProject = (BpelProject) project;
				if (bpelProject.toString().equals(bpelProcessName)) {
					return bpelProject;
				}
			}
		}
		return null;
	}

	/**
	 * parse all bpel and esb files
	 * 
	 * @param listOfProjects
	 * @param workspace
	 * @param isInJws
	 * @return list of bpel.xml and *.esb witch is parsed
	 */
	private final List<File> parseBpelEsbProjects(List<File> listOfProjects, Workspace workspace, boolean isInJws) {
		List<File> parsedBpelEsbFiles = new ArrayList<File>();
		for (File projectFile : listOfProjects) {
			File bpelFile = findBpelXmlFile(projectFile.getParentFile());
			if (bpelFile != null) {
				try {

					parsedBpelEsbFiles.add(bpelFile);
					BpelProject bpelProject = bpelParser.parseBpelXml(bpelFile);
					bpelProject.setInJws(isInJws);
					workspace.addProject(bpelProject);
				} catch (ServiceParserException ep) {
					// TODO: dokoncit error project
					ep.printStackTrace();
				}
			} else {
				File esbFile = findEsbProjectFolder(projectFile.getParentFile());
				if (esbFile != null) {
					try {

						parsedBpelEsbFiles.add(esbFile);
						EsbProject esbProject = esbParser.parse(esbFile);
						esbProject.setInJws(isInJws);
						workspace.addProject(esbProject);
					} catch (ServiceParserException ep) {
						// TODO: dokoncit error project
						ep.printStackTrace();
					}
				}
			}
		}
		return parsedBpelEsbFiles;
	}

	/**
	 * parse all project from file system wiche not found in jws
	 * 
	 * @param projectFiles
	 * @param workspace
	 * @return
	 */
	private final void findBpelEsbProjectFromFS(List<File> projectFiles, List<File> fsProjects, File workspace) {
		File[] files = workspace.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					findBpelEsbProjectFromFS(projectFiles, fsProjects, file);
				}
				if (file.isFile() && file.getName().equals("bpel.xml") && !projectFiles.contains(file) && !fsProjects.contains(file)) {
					fsProjects.add(file);
				} else if (file.isFile() && file.getName().endsWith(".esb") && !projectFiles.contains(file.getParentFile()) && !fsProjects.contains(file.getParentFile())) {
					fsProjects.add(file.getParentFile());
				}
			}
		}
	}

	/**
	 * analyses dependencies between bpel processes
	 * 
	 * @param workspace
	 */
	private final void analysesBpelDependencies(Workspace workspace) {
		// analyze of bpel dependecy
		for (Project service : workspace.getProjects()) {
			if (service.getType() == ProjectType.ORACLE10G_BPEL) {
				BpelProject bpel = (BpelProject) service;
				for (PartnerLinkBinding partnerLinkBinding : bpel.getPartnerLinkBindings()) {
					if (partnerLinkBinding.getDependencyProject() == null) {
						try {
							bpelParser.parseBpelByWsdl(partnerLinkBinding);
						} catch (ServiceParserException e) {
							// TODO dokoncit
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private final void analysesEsbDependencies(Workspace workspace) {

		for (Project project : workspace.getProjects()) {
			if (project.getType() == ProjectType.ORACLE10G_ESB) {
				EsbProject esbProject = (EsbProject) project;
				// mal by nacitat vsetky projekty atd.
				analyzeEsbDependencies(esbProject.getBasicEsbNodes(), workspace);
			}
		}
	}

	private final void analyzeEsbDependencies(List<BasicEsbNode> basicEsbNodes, Workspace workspace) {
		for (BasicEsbNode basicEsbNode : basicEsbNodes) {
			if (basicEsbNode.get() instanceof EsbSvc) {
				EsbSvc esbSvc = (EsbSvc) basicEsbNode.get();
				URL url = parseWsdlToUrl(esbSvc.getWsdlURL());
				if (url != null) {
					String qName = esbParser.convertWsdlToQname(url);
					Project esbProject = findEsbProjectByQname(qName, url, workspace);
					if (esbProject != null) {
						esbSvc.getOwnerEsbProject().addDependency(esbProject);
					}
				}

			} else if (basicEsbNode.get() instanceof EsbSys) {
				EsbSys esbSys = (EsbSys) basicEsbNode.get();
				analyzeEsbDependencies(esbSys.getBasicEsbNodes(), workspace);
			} else if (basicEsbNode.get() instanceof EsbGrp) {
				EsbGrp esbGrp = (EsbGrp) basicEsbNode.get();
				analyzeEsbDependencies(esbGrp.getBasicEsbNodes(), workspace);
			}
		}
	}

	/**
	 * find {@link EsbProject} by qName
	 * 
	 * @param qName
	 *            //service/@name
	 * @param serviceURL
	 *            wsld from //service/serviceDefinition/wsdlURL
	 * @param workspace
	 * @return
	 */
	private final Project findEsbProjectByQname(String qName, URL serviceURL, Workspace workspace) {
		for (Project project : workspace.getProjects()) {
			if (project.getType() == ProjectType.ORACLE10G_ESB) {
				EsbProject esbProject = (EsbProject) project;
				EsbProject fProject = esbProject.findEsbProjectByQname(qName, serviceURL);
				if (fProject != null) {
					return fProject;
				}
			}
		}
		return null;
	}

	private final URL parseWsdlToUrl(String wsdl) {
		if (wsdl != null) {
			try {
				return new URL(wsdl);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * find bpel xml in project folder
	 * 
	 * @param project
	 * @return
	 */
	private final File findBpelXmlFile(File project) {
		File[] files = project.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					File bpel = findBpelXmlFile(file);
					if (bpel != null) {
						return bpel;
					}
				}
				if (file.isFile() && file.getName().equals("bpel.xml")) {
					return file;
				}
			}
		}
		return null;
	}

	/**
	 * find *.esb in workspace and return folder path
	 * 
	 * @param project
	 * @return
	 */
	private final File findEsbProjectFolder(File project) {
		File[] files = project.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					File esb = findEsbProjectFolder(file);
					if (esb != null) {
						return esb;
					}
				}
				if (file.isFile() && (file.getName().endsWith(".esb"))) {
					return file.getParentFile();
				}
			}
		}
		return null;
	}

	/**
	 * parser jws file in workspaceFolder
	 * 
	 * @param element
	 * @param workspaceFolder
	 * @return
	 */
	private final List<File> parseListOfProjectsFromJWS(Element element, File workspaceFolder) {
		List<File> files = new ArrayList<File>();

		Element eList = element.element("list");
		if (eList != null) {
			String value = eList.attributeValue("n");
			if (value != null && "listOfChildren".equals(value)) {

				List<?> eListHash = eList.elements("hash");
				for (Object e : eListHash) {
					Element eHash = (Element) e;
					String url = parseEhash(eHash);
					if (url != null) {
						files.add(new File(workspaceFolder + File.separator + url));
					}
				}
			}
		}
		return files;
	}

	/**
	 * parse element hash
	 * 
	 * @param eHash
	 * @return
	 */
	private final String parseEhash(Element eHash) {
		Element eValue = eHash.element("value");
		if (eValue != null) {
			String vValue = eValue.attributeValue("v");
			if (vValue != null && "oracle.ide.model.Project".equals(vValue)) {
				Element eUrl = eHash.element("url");
				if (eUrl != null) {
					return eUrl.attributeValue("path");
				}
			}

		}

		return null;
	}

	/***
	 * find all file with extension .jws in workspace folder
	 * 
	 * @param workspaceFolder
	 * @param jwsFiles
	 * @throws ServiceParserException
	 */
	private final void findAllJws(File workspaceFolder, List<File> jwsFiles) throws ServiceParserException {
		if (workspaceFolder.isFile()) {
			throw new ServiceParserException("workspace folder: " + workspaceFolder + " is file", true);
		}
		File[] files = workspaceFolder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					findAllJws(file, jwsFiles);
				}
				if (file.isFile() && file.getName().endsWith(".jws")) {
					jwsFiles.add(file);
				}
			}
		}
	}
}