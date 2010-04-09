package com.tomecode.soa.bpel.dependency.analyzer.gui.components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.tomecode.soa.bpel.dependency.analyzer.gui.tree.ProcessStructureTree;
import com.tomecode.soa.bpel.dependency.analyzer.gui.tree.ProjectOperationTree;
import com.tomecode.soa.bpel.dependency.analyzer.gui.tree.WorkspaceTree;
import com.tomecode.soa.bpel.dependency.analyzer.gui.tree.node.ErrorNode;
import com.tomecode.soa.oracle10g.bpel.BpelOperations;
import com.tomecode.soa.oracle10g.bpel.Bpel;
import com.tomecode.soa.oracle10g.bpel.Operation;
import com.tomecode.soa.oracle10g.bpel.Workspace;
import com.tomecode.util.gui.PanelFactory;

/**
 * The panel contains list of components wich display dependency for bpel
 * processes
 * 
 * @author Tomas Frastia
 * 
 */
public final class WorkspacePanel extends JPanel {

	private static final long serialVersionUID = 1530206367436072362L;

	private static final String P_TREE = "p.tree";

	private static final String P_ERROR = "p.error";
	/**
	 * {@link WorkspaceTree}
	 */
	private final WorkspaceTree workspaceTree;
	/**
	 * {@link ProjectOperationTree}
	 */
	private final ProjectOperationTree projectOperationTree;
	/**
	 * {@link ProcessStructureTree}
	 */
	private final ProcessStructureTree processStructureTree;

	/**
	 * path of selected bpel process
	 */
	// private final JTextArea txtProcessFolder;

	private final JTextField txtError;

	private final JTextField txtErrorWsdl;

	private final DefaultListModel listDependency;

	private Workspace workspace;

	/**
	 * Constructor
	 * 
	 * @param workspace
	 */
	public WorkspacePanel(Workspace workspace) {
		super(new BorderLayout());
		this.workspace = workspace;
		// this.txtProcessFolder = new JTextArea();
		// txtProcessFolder.setEditable(false);
		// txtProcessFolder.setWrapStyleWord(true);
		this.workspaceTree = new WorkspaceTree(workspace);
		this.projectOperationTree = new ProjectOperationTree();
		this.processStructureTree = new ProcessStructureTree();
		JSplitPane spWorkspace = PanelFactory.createSplitPanel();
		spWorkspace.add(PanelFactory.createBorderLayout("Workspace Dependecies", new JScrollPane(workspaceTree)));
		spWorkspace.setDividerLocation(200);
		JSplitPane spProjectTrees = PanelFactory.createSplitPanel();
		spProjectTrees.add(PanelFactory.createBorderLayout("Project-Operations Dependecies", new JScrollPane(projectOperationTree)));
		spProjectTrees.add(PanelFactory.createBorderLayout("Project Structure", new JScrollPane(processStructureTree)));
		spProjectTrees.setDividerLocation(350);

		JSplitPane spProjectBase = PanelFactory.createSplitPanel();
		spProjectBase.setOrientation(JSplitPane.VERTICAL_SPLIT);
		spProjectBase.setDividerLocation(350);
		spProjectBase.add(spProjectTrees);

		JPanel pProject = PanelFactory.createBorderLayout();
		pProject.add(spProjectBase, BorderLayout.CENTER);

		listDependency = new DefaultListModel();
		JList list = new JList(listDependency);

		JPanel pProjectDetail = PanelFactory.createBorderLayout();

		pProjectDetail.add(PanelFactory.wrapWithTile("Usage", new JScrollPane(list)), BorderLayout.CENTER);

		spProjectBase.add(pProjectDetail);

		final JPanel pCardPanel = new JPanel(new CardLayout());
		pCardPanel.add(pProject, P_TREE);

		JPanel pError = PanelFactory.createBorderLayout("Parse Error");
		txtError = new JTextField();
		txtError.setEditable(false);

		txtErrorWsdl = new JTextField();
		txtErrorWsdl.setEditable(false);

		JPanel pErrorRow = PanelFactory.createGridLayout(2, 1);
		pErrorRow.add(PanelFactory.wrapWithLabelNorm("Error:", txtError, 16), BorderLayout.NORTH);
		pErrorRow.add(PanelFactory.wrapWithLabelNorm("WSDL:", txtErrorWsdl, 10), BorderLayout.NORTH);
		pError.add(pErrorRow, BorderLayout.NORTH);
		pCardPanel.add(pError, P_ERROR);

		spWorkspace.add(pCardPanel);

		add(spWorkspace, BorderLayout.CENTER);

		// select bpel proces
		this.workspaceTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public final void valueChanged(TreeSelectionEvent e) {

				if (e.getPath().getLastPathComponent() instanceof Bpel) {
					Bpel bpelProcess = (Bpel) e.getPath().getLastPathComponent();
					if (bpelProcess != null) {
						selectBpelProcess(bpelProcess);
						// txtProcessFolder.setText(bpelProcess.getBpelXmlFile().getParent());
					}
					CardLayout cardLayout = (CardLayout) pCardPanel.getLayout();
					cardLayout.show(pCardPanel, P_TREE);
				} else if (e.getPath().getLastPathComponent() instanceof ErrorNode) {
					processStructureTree.clear();
					projectOperationTree.clear();

					ErrorNode errorNode = (ErrorNode) e.getPath().getLastPathComponent();
					txtError.setText(errorNode.getErrorText());
					txtErrorWsdl.setText(errorNode.getWsdl());
					CardLayout cardLayout = (CardLayout) pCardPanel.getLayout();
					cardLayout.show(pCardPanel, P_ERROR);

				} else {
					processStructureTree.clear();
					projectOperationTree.clear();
					CardLayout cardLayout = (CardLayout) pCardPanel.getLayout();
					cardLayout.show(pCardPanel, P_TREE);
				}

			}

		});

		// select operation in selected bpel proces in workspaceTree
		this.projectOperationTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public final void valueChanged(TreeSelectionEvent e) {
				if (e.getPath().getLastPathComponent() instanceof Operation) {
					Operation operation = (Operation) e.getPath().getLastPathComponent();
					displayBpelProcessStructure(operation.getPartnerLinkBinding().getParent());// .getBpelProcess());
				} else if (e.getPath().getLastPathComponent() instanceof BpelOperations) {
					BpelOperations bpelOperations = (BpelOperations) e.getPath().getLastPathComponent();
					displayBpelProcessStructure(bpelOperations.getBpelProcess());
				}
			}
		});

	}

	/**
	 * display bpel process
	 * 
	 * @param bpelProcess
	 */
	private final void displayBpelProcessStructure(Bpel bpelProcess) {
		if (bpelProcess == null) {
			processStructureTree.clear();
			// txtProcessFolder.setText("");
		} else {
			processStructureTree.addBpelProcessStrukture(bpelProcess.getBpelProcessStrukture());
			// txtProcessFolder.setText(bpelProcess.getBpelXmlFile().getParent());
		}

	}

	private final void selectBpelProcess(Bpel bpelProcess) {
		projectOperationTree.addBpelProcessOperations(bpelProcess.getBpelOperations());
		processStructureTree.addBpelProcessStrukture(bpelProcess.getBpelProcessStrukture());

		listDependency.clear();

		List<Bpel> listUsage = this.workspace.findUsages(bpelProcess);
		for (Bpel usageBpelProcess : listUsage) {
			listDependency.addElement(usageBpelProcess);
		}

	}
}
