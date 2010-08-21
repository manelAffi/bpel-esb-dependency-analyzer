package com.tomecode.soa.openesb.bpel;

import com.tomecode.soa.bpel.activity.Activity;

/**
 * 
 * Process structure which contain all activities in Open ESB - BPEL process
 * 
 * @author Frastia Tomas
 * 
 */
public final class OpenEsbBpelProcessStructure extends Activity {

	private static final long serialVersionUID = 601785178780916919L;

	private final OpenEsbBpelProcess process;

	/**
	 * Constructor
	 * 
	 * @param process
	 */
	public OpenEsbBpelProcessStructure(OpenEsbBpelProcess process) {
		this.process = process;
	}

	/**
	 * @return the process
	 */
	public final OpenEsbBpelProcess getProcess() {
		return process;
	}

	public final String toString() {
		return "!!!structura!!!";
	}

}