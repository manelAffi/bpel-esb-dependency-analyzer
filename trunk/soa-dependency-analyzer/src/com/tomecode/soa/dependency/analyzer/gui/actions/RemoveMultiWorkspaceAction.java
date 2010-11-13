package com.tomecode.soa.dependency.analyzer.gui.actions;

import java.util.List;

import org.eclipse.jface.action.Action;

import com.tomecode.soa.dependency.analyzer.gui.utils.GuiUtils;
import com.tomecode.soa.dependency.analyzer.icons.ImageFactory;
import com.tomecode.soa.dependency.analyzer.tree.BpelProcessStructureNavigator;
import com.tomecode.soa.dependency.analyzer.tree.ProjectFilesNavigator;
import com.tomecode.soa.dependency.analyzer.tree.ServiceBusStructureNavigator;
import com.tomecode.soa.dependency.analyzer.tree.ServiceOperationsDepNavigator;
import com.tomecode.soa.dependency.analyzer.tree.WorkspacesNavigator;
import com.tomecode.soa.dependency.analyzer.view.PropertiesView;
import com.tomecode.soa.dependency.analyzer.view.graph.FlowGraphView;
import com.tomecode.soa.dependency.analyzer.view.graph.VisualGraphView;
import com.tomecode.soa.workspace.MultiWorkspace;

/**
 * 
 * Remove selected multi workspace in {@link WorkspacesNavigator}
 * 
 * @author Tomas Frastia
 * @see http://www.tomecode.com
 *      http://code.google.com/p/bpel-esb-dependency-analyzer
 * 
 */
public final class RemoveMultiWorkspaceAction extends Action {

	public RemoveMultiWorkspaceAction() {
		super();
		setText("Remove Multi Workspace");
		setToolTipText("Remove Multi Workspace");
		setImageDescriptor(ImageFactory.trash);
	}

	public final void run() {
		MultiWorkspace multiWorkspace = GuiUtils.getWorkspacesNavigator().removeMultiWorkspace();
		if (multiWorkspace != null) {

			ProjectFilesNavigator projectStructureNavigator = GuiUtils.getProjectStructureNavigator();
			if (projectStructureNavigator != null) {
				projectStructureNavigator.removeMultiWorkspace(multiWorkspace);
			}
			BpelProcessStructureNavigator bpelProcessStructureNavigator = GuiUtils.getBpelProcessStructureNavigator();
			if (bpelProcessStructureNavigator != null) {
				bpelProcessStructureNavigator.removeMultiWorkspace(multiWorkspace);
			}
			PropertiesView propertiesView = GuiUtils.getPropertiesView();
			if (propertiesView != null) {
				propertiesView.removeMultiWorkspace(multiWorkspace);
			}
			ServiceBusStructureNavigator serviceBusStructureNavigator = GuiUtils.getServiceBusStructureNavigator();
			if (serviceBusStructureNavigator != null) {
				serviceBusStructureNavigator.removeMultiWorkspace(multiWorkspace);
			}
			ServiceOperationsDepNavigator serviceOperationsDepNavigator = GuiUtils.getServiceOperationsDepNavigator();
			if (serviceOperationsDepNavigator != null) {
				serviceOperationsDepNavigator.removeMultiWorkspace(multiWorkspace);
			}

			List<VisualGraphView> visualGraphViews = GuiUtils.getVisualGraphViews();
			for (VisualGraphView visualGraphView : visualGraphViews) {
				visualGraphView.removeMultiWorkspace(multiWorkspace);
			}
			List<FlowGraphView> flowGraphViews = GuiUtils.getFlowGraphViews();
			for (FlowGraphView flowGraphView : flowGraphViews) {
				flowGraphView.removeMultiWorkspace(multiWorkspace);
			}
		}
	}

	public final void setEnableFor(Object object) {
		setEnabled((object instanceof MultiWorkspace));
	}
}