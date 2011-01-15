package com.tomecode.soa.protocols.jms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.tomecode.soa.dependency.analyzer.icons.ImageFace;
import com.tomecode.soa.dependency.analyzer.icons.ImageFactory;

/**
 * JMS Connection factory - A JMS ConnectionFactory object is used by the client
 * to make connections to the server
 * 
 * @author Tomas Frastia
 * @see http://www.tomecode.com
 *      http://code.google.com/p/bpel-esb-dependency-analyzer/ *
 */
public final class JMSConnectionFactory implements ImageFace {

	/**
	 * connection factory name
	 */
	private String name;
	/**
	 * list of {@link JMSQueue}
	 */
	private final List<JMSQueue> jmsQueues;
	/**
	 * parent {@link JMSServer}
	 */
	private JMSServer parentJmsServer;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            connection factory name
	 */
	public JMSConnectionFactory(String name) {
		this.jmsQueues = new ArrayList<JMSQueue>();
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final JMSServer getJmsServer() {
		return parentJmsServer;
	}

	public final void setJmsServer(JMSServer jmsServer) {
		this.parentJmsServer = jmsServer;
	}

	public final void addJmsQueue(String queue) {
		if (!existsQueue(queue)) {
			jmsQueues.add(new JMSQueue(queue, this));
		}
	}

	private final boolean existsQueue(String queue) {
		for (JMSQueue jmsQueue : jmsQueues) {
			if (jmsQueue.getName().equals(queue)) {
				return true;
			}
		}
		return false;
	}

	public final List<JMSQueue> getJmsQueues() {
		return jmsQueues;
	}

	public final String toString() {
		return name;
	}

	@Override
	public final Image getImage(boolean small) {
		if (small) {
			return ImageFactory.JMS_CONNECTION_FACTORY_SMALL;
		}
		return ImageFactory.JMS_CONNECTION_FACTORY;
	}

	@Override
	public final String getToolTip() {
		return "Type: JMS Connection Factory\nName: " + name;
	}
}