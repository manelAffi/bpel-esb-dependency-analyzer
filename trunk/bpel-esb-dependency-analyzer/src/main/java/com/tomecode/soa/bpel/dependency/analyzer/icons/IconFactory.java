package com.tomecode.soa.bpel.dependency.analyzer.icons;

import javax.swing.ImageIcon;

/**
 * 
 * Contains all incons in project
 * 
 * @author Tomas Frastia
 */
public final class IconFactory {

	public static final ImageIcon ASSIGN = new ImageIcon(IconFactory.class.getResource("assign.gif"));
	public static final ImageIcon SEQUENCE = new ImageIcon(IconFactory.class.getResource("sequence.gif"));
	public static final ImageIcon SCOPE = new ImageIcon(IconFactory.class.getResource("scope.gif"));
	public static final ImageIcon CATCH = new ImageIcon(IconFactory.class.getResource("catch.gif"));
	public static final ImageIcon CATCHALL = new ImageIcon(IconFactory.class.getResource("catchall.gif"));
	public static final ImageIcon EMPTY = new ImageIcon(IconFactory.class.getResource("empty.gif"));
	public static final ImageIcon RECEIVE = new ImageIcon(IconFactory.class.getResource("receive.gif"));
	public static final ImageIcon INVOKE = new ImageIcon(IconFactory.class.getResource("invoke.gif"));
	public static final ImageIcon REPLY = new ImageIcon(IconFactory.class.getResource("reply.gif"));
	public static final ImageIcon SWITCH = new ImageIcon(IconFactory.class.getResource("switch.gif"));
	public static final ImageIcon ONALARM = new ImageIcon(IconFactory.class.getResource("onAlarm.gif"));
	public static final ImageIcon ONMESSAGE = new ImageIcon(IconFactory.class.getResource("onMessage.gif"));
	public static final ImageIcon COMPENSATIONHANDLER = new ImageIcon(IconFactory.class.getResource("compensationHandler.gif"));
	public static final ImageIcon PROCESS = new ImageIcon(IconFactory.class.getResource("process.gif"));
	public static final ImageIcon BPELX_EXEC = new ImageIcon(IconFactory.class.getResource("process.gif"));
	public static final ImageIcon PICK = new ImageIcon(IconFactory.class.getResource("pick.gif"));
	public static final ImageIcon FLOW = new ImageIcon(IconFactory.class.getResource("flow.gif"));
	public static final ImageIcon FLOWN = new ImageIcon(IconFactory.class.getResource("flowN.gif"));
	public static final ImageIcon COMPENSTATE = new ImageIcon(IconFactory.class.getResource("compensate.gif"));
	public static final ImageIcon TERMINATE = new ImageIcon(IconFactory.class.getResource("terminate.gif"));
	public static final ImageIcon THROW = new ImageIcon(IconFactory.class.getResource("throw.gif"));
	public static final ImageIcon ESB = new ImageIcon(IconFactory.class.getResource("esb.gif"));

	private IconFactory() {
	}

}