package com.tomecode.soa.ora.osb10g.services.config;

/**
 * (c) Copyright Tomecode.com, 2010. All rights reserved.
 * 
 * 
 * Endpoint protocol - JCA
 * 
 * @author Tomas Frastia
 * @see http://www.tomecode.com
 *      http://code.google.com/p/bpel-esb-dependency-analyzer/
 * 
 */
@SuppressWarnings("rawtypes")
public final class EndpointJca extends EndpointConfig {

	public EndpointJca() {
		super(ProviderProtocol.JCA);
	}

}
