package com.tomecode.soa.dependency.analyzer.tree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.tomecode.soa.dependency.analyzer.icons.ImageFactory;
import com.tomecode.soa.dependency.analyzer.tree.node.EmptyNode;
import com.tomecode.soa.openesb.bpel.OpenEsbBpelProcess;
import com.tomecode.soa.ora.osb10g.services.Service;
import com.tomecode.soa.ora.suite10g.project.BpelProject;
import com.tomecode.soa.workspace.MultiWorkspace;
import com.tomecode.soa.workspace.Workspace;

/**
 * 
 * Show the activities or services by dependencies
 * 
 * @author Tomas Frastia
 * 
 */
public final class ServiceOperationsDepNavigator extends ViewPart {

	public static final String ID = "view.serviceoperationsdepnavigator";

	private ServiceOperationsDepLabelProvider labelProvider;

	private ServiceOperationsDepContentProvider contentProvider;

	public final EmptyNode rootNode;
	private TreeViewer tree;

	public ServiceOperationsDepNavigator() {
		setTitleImage(ImageFactory.DEPENDNECY_BY_OPERATION_TREE);
		setTitleToolTip("Dependencies by operations");
		rootNode = new EmptyNode();
		labelProvider = new ServiceOperationsDepLabelProvider();
		contentProvider = new ServiceOperationsDepContentProvider();
	}

	@Override
	public final void createPartControl(Composite parent) {
		tree = new TreeViewer(parent, SWT.SIMPLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tree.setContentProvider(contentProvider);
		tree.setLabelProvider(labelProvider);
	}

	@Override
	public final void setFocus() {

	}

	public final void show(Object source) {
		if (source instanceof BpelProject) {
			BpelProject bpelProject = (BpelProject) source;
			setDataToTree(bpelProject.getBpelOperations());
		} else if (source instanceof Service) {
			setDataToTree((Service) source);
		} else if (source instanceof OpenEsbBpelProcess) {
			setDataToTree((OpenEsbBpelProcess) source);
		} else {
			clearTree();
		}
	}

	private final void setDataToTree(Object data) {
		rootNode.set(data);
		tree.setInput(rootNode);
		tree.expandToLevel(3);
	}

	private final void clearTree() {
		rootNode.set(null);
		tree.setInput(null);
	}

	public final void removeMultiWorkspace(MultiWorkspace multiWorkspace) {
		MultiWorkspace multiWorkspaceInTree = findMultiWorkspaceInTree();
		if (multiWorkspace.equals(multiWorkspaceInTree)) {
			clearTree();
		}
	}

	private final MultiWorkspace findMultiWorkspaceInTree() {
		if (rootNode.hasChildren()) {
			Object objectInTree = rootNode.getChildren()[0];
			if (objectInTree instanceof BpelProject) {
				return ((BpelProject) objectInTree).getWorkpsace().getMultiWorkspace();
			}
		}
		return null;
	}

	private final Workspace findWorkspaceInTree() {
		if (rootNode.hasChildren()) {
			Object objectInTree = rootNode.getChildren()[0];
			if (objectInTree instanceof BpelProject) {
				return ((BpelProject) objectInTree).getWorkpsace();
			}
		}
		return null;
	}

	public final void removeWorkspace(Workspace workspace) {
		Workspace workspaceInTree = findWorkspaceInTree();
		if (workspace.equals(workspaceInTree)) {
			clearTree();
		}
	}

	public final void showWithActivities() {
		contentProvider.setShowWithActivities(true);
		tree.refresh();
	}

	public final void showWithoutActivities() {
		contentProvider.setShowWithActivities(false);
		tree.refresh();
	}

}
