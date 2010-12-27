package com.tomecode.soa.ora.suite10g.activity;

import org.eclipse.swt.graphics.Image;

import com.tomecode.soa.activity.Activity;
import com.tomecode.soa.activity.ActivityType;
import com.tomecode.soa.dependency.analyzer.icons.ImageFactory;

/**
 * (c) Copyright Tomecode.com, 2010. All rights reserved.
 * 
 * HumanTask in BPEL process
 * 
 * @author Tomas Frastia
 * @see http://www.tomecode.com
 *      http://code.google.com/p/bpel-esb-dependency-analyzer/
 * 
 */
public final class HumanTask extends Activity {

	private static final long serialVersionUID = 4638676565146012787L;

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public HumanTask(String name) {
		super(ActivityType.ORACLE_10G_HUMANTASK, name);
	}

	public final Image getImage() {
		return ImageFactory.ORACLE_10G_HUMANTASK;
	}
}