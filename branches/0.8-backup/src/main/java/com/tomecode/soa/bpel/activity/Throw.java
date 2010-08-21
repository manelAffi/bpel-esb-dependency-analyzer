package com.tomecode.soa.bpel.activity;

/**
 * 
 * throw activity in bpel process
 * 
 * @author Tomas Frastia
 * 
 */
public final class Throw extends Activity {

	private static final long serialVersionUID = -6296106634300381880L;

	private String faultVariable;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param faultVariable
	 */
	public Throw(String name, String faultVariable) {
		super(ActivityType.THROW, name);
		this.faultVariable = faultVariable;
	}

	public final String getFaultVariable() {
		return faultVariable;
	}

}