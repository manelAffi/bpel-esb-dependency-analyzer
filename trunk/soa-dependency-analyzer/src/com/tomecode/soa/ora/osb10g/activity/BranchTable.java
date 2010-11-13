package com.tomecode.soa.ora.osb10g.activity;

import org.eclipse.swt.graphics.Image;

/**
 * element: branch-table
 * 
 * @author Tomas Frastia
 * 
 */
public final class BranchTable extends OsbActivity {

	private String variable;

	public BranchTable(String variable) {
		super();
		this.variable = variable;
	}

	public final String getVariable() {
		return variable;
	}

	public final String toString() {
		return "branch-table";
	}

	@Override
	public final Image getImage() {
		return null;
	}
}