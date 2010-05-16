package com.tomecode.soa.bpel.dependency.analyzer.gui.tree;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import com.tomecode.soa.bpel.dependency.analyzer.gui.tree.node.IconNode;
import com.tomecode.soa.oracle10g.bpel.BpelOperations;

/**
 * 
 * Display operation from selected proces in {@link WorkspaceTree}
 * 
 * @author Tomas Frastia
 * 
 */
public final class ProjectOperationTree extends BasicTree {

	private static final long serialVersionUID = -3750125284965106516L;

	/**
	 * Constructor
	 */
	public ProjectOperationTree() {
		super();
		setCellRenderer(new OperationTreeRenderer());
	}

	/**
	 * display selected bpel proces in {@link WorkspaceTree}
	 * 
	 * @param bpelProcess
	 */
	public final void addBpelProcessOperations(BpelOperations bpelOperations) {
		treeModel.setRoot(bpelOperations);
		expandPath(new TreePath(bpelOperations));
	}

	/**
	 * 
	 * @author Tomas Frastia
	 * 
	 */
	private final class OperationTreeRenderer implements TreeCellRenderer {

		public final Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

			DefaultTreeCellRenderer rnd = (DefaultTreeCellRenderer) new DefaultTreeCellRenderer().getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

			if (value instanceof IconNode) {
				rnd.setIcon(((IconNode) value).getIcon());
			}

			return rnd;
		}
	}

	@Override
	public void showPopupMenu(int x, int y) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
