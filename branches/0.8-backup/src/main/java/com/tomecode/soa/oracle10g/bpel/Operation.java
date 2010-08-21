package com.tomecode.soa.oracle10g.bpel;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import com.tomecode.soa.bpel.activity.Activity;
import com.tomecode.soa.bpel.activity.ActivityType;
import com.tomecode.soa.dependency.analyzer.gui.tree.node.EmptyNode;
import com.tomecode.soa.dependency.analyzer.gui.tree.node.ErrorNode;
import com.tomecode.soa.dependency.analyzer.gui.tree.node.EsbServiceNode;
import com.tomecode.soa.dependency.analyzer.gui.tree.node.IconNode;

/**
 * Partner link operation
 * 
 * @author Tomas Frastia
 * 
 */
public final class Operation implements TreeNode, IconNode, Serializable {

	private static final long serialVersionUID = -5643471889740129373L;

	private final ActivityType activtyType;
	private String activity;

	/**
	 * operation name
	 */
	private String name;

	/**
	 * wsdl operation
	 */
	private String operation;

	private PartnerLinkBinding partnerLinkBinding;

	private List<Activity> activities;
	/**
	 * ownere project
	 */
	private BpelProject ownerBpelProject;
	/**
	 * default value is true but if service not found (unknown ) then defualt
	 * value is false
	 */
	private boolean visiblePartnerLinksDependnecie = true;

	/**
	 * Constructor
	 * 
	 * @param activity
	 * @param name
	 * @param operation
	 * @param partnerLinkBinding
	 */
	public Operation(String activity, String name, String operation, BpelProject ownerBpelProject, PartnerLinkBinding partnerLinkBinding, List<Activity> activities) {
		this.activity = activity;
		this.activtyType = ActivityType.parseActivtyType(activity);
		this.name = name;
		this.operation = operation;
		this.partnerLinkBinding = partnerLinkBinding;
		this.activities = activities;
		this.ownerBpelProject = ownerBpelProject;
	}

	public final List<Activity> getActivities() {
		return activities;
	}

	public final String getOperation() {
		return operation;
	}

	public final String getActivity() {
		return activity;
	}

	public final String getName() {
		return name;
	}

	public final PartnerLinkBinding getPartnerLinkBinding() {
		return partnerLinkBinding;
	}

	@Override
	public Enumeration<?> children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		if (!visiblePartnerLinksDependnecie) {
			return false;
		}
		if (partnerLinkBinding != null) {
			if (partnerLinkBinding.getDependencyBpelProject() != null) {
				return true;
			}
			if (partnerLinkBinding.getDependencyEsbProject() != null) {
				return true;
			}
			if (partnerLinkBinding.getUnknownProject() != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {

		BpelProject project = partnerLinkBinding.getDependencyBpelProject();
		if (project != null) {
			if (project.getBpelOperations() == null) {
				return new ErrorNode("ERROR:not found " + partnerLinkBinding.getName(), partnerLinkBinding.getWsdlLocation(), null);
			}
			return project.getBpelOperations();
		} else if (partnerLinkBinding.getDependencyEsbProject() != null) {
			return new EsbServiceNode(partnerLinkBinding.getDependencyEsbProject());
		} else if (partnerLinkBinding.getUnknownProject() != null) {
			return partnerLinkBinding.getUnknownProject();
		}
		return new EmptyNode(project);

	}

	@Override
	public int getChildCount() {
		return getAllowsChildren() ? 1 : 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return partnerLinkBinding.getDependencyEsbProject();
	}

	@Override
	public boolean isLeaf() {
		return !getAllowsChildren();
	}

	public final BpelProject getOwnerBpelProject() {
		return ownerBpelProject;
	}

	public final BpelProject getPartnerLinkBpelProcess() {
		return partnerLinkBinding.getDependencyBpelProject();
	}

	public final ActivityType getActivtyType() {
		return activtyType;
	}

	public final String toString() {
		if (name == null) {
			return (operation == null ? "" : operation);
		}
		return name + (operation == null ? "" : " - " + operation);
	}

	@Override
	public ImageIcon getIcon() {
		if (activities != null) {
			return activtyType.getImageIcon();
		}
		return null;
	}

	public final void setVisiblePartnerLinks(boolean visible) {
		this.visiblePartnerLinksDependnecie = visible;
	}

	public final Operation clone() {
		return new Operation(activity, name, operation, ownerBpelProject, partnerLinkBinding, activities);
	}
}