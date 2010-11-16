package com.tomecode.soa.dependency.analyzer.tree;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

import com.tomecode.soa.dependency.analyzer.gui.utils.GuiUtils;
import com.tomecode.soa.dependency.analyzer.gui.utils.HideView;
import com.tomecode.soa.dependency.analyzer.gui.utils.PopupMenuUtils;
import com.tomecode.soa.dependency.analyzer.gui.utils.WindowChangeListener;
import com.tomecode.soa.dependency.analyzer.icons.ImageFace;
import com.tomecode.soa.dependency.analyzer.tree.node.EmptyNode;
import com.tomecode.soa.dependency.analyzer.view.graph.VisualGraphView;
import com.tomecode.soa.project.Project;

/**
 * 
 * Show all services in project
 * 
 * @author Tomas Frastia
 * @see http://www.tomecode.com
 *      http://code.google.com/p/bpel-esb-dependency-analyzer/
 * 
 */
public final class ProjectServicesNavigator extends ViewPart implements HideView, IDoubleClickListener {

	public static final String ID = "view.projectServiceNavigator";

	private TreeViewer tree;

	private final ProjectServicesLabelProvider labelProvider;

	private final ProjectServicesContentProvider contentProvider;

	private final EmptyNode rootNode;

	public ProjectServicesNavigator() {
		super();
		setTitleToolTip("Project Services");
		rootNode = new EmptyNode();
		labelProvider = new ProjectServicesLabelProvider();
		contentProvider = new ProjectServicesContentProvider();
	}

	@Override
	public final void createPartControl(Composite parent) {
		tree = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tree.setLabelProvider(labelProvider);
		tree.setContentProvider(contentProvider);
		tree.addDoubleClickListener(this);
		hookContextMenu();
	}

	@Override
	public final void setFocus() {
		MenuManager menuManager = new MenuManager("#PopupMenuServicesNavigator");
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public final void menuAboutToShow(IMenuManager manager) {

				IStructuredSelection selection = (IStructuredSelection) tree.getSelection();
				if (!selection.isEmpty()) {
					PopupMenuUtils.fillServicesNavigator(selection.getFirstElement(), manager);
				}
			}
		});

		Menu menu = menuManager.createContextMenu(tree.getControl());
		tree.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, tree);
	}

	private final void hookContextMenu() {

	}

	@Override
	public final void hideMe() {
		getSite().getPage().hideView(this);
	}

	public final void dispose() {
		WindowChangeListener.getInstance().hideFromView(ID);
		super.dispose();
	}

	public final void show(Object source) {
		if (source == null) {
			clearTree();
		} else if (source instanceof Project) {
			tree.setInput(source);
		} else {
			clearTree();
		}
	}

	private final void clearTree() {
		rootNode.set(null);
		tree.setInput(rootNode);
	}

	@Override
	public final void doubleClick(DoubleClickEvent event) {
		try {
			IStructuredSelection selection = (IStructuredSelection) tree.getSelection();
			if (!selection.isEmpty()) {
				VisualGraphView visualGraphView = GuiUtils.getVisualGraphView();
				if (visualGraphView != null) {
					visualGraphView.showGraph(selection.getFirstElement());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(null, "Error", "oops2 error:" + e.getMessage());
		}
	}

	/**
	 * 
	 * {@link LabelProvider} for {@link ProjectServicesNavigator}
	 * 
	 * @author Tomas Frastia
	 * @see http://www.tomecode.com
	 *      http://code.google.com/p/bpel-esb-dependency-analyzer/
	 * 
	 */
	final class ProjectServicesLabelProvider extends LabelProvider {

		public final Image getImage(Object element) {
			if (element instanceof ImageFace) {
				return ((ImageFace) element).getImage();
			}
			return null;
		}
	}

}
