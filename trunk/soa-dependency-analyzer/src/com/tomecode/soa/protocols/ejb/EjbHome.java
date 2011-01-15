package com.tomecode.soa.protocols.ejb;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.tomecode.soa.dependency.analyzer.icons.ImageFace;
import com.tomecode.soa.dependency.analyzer.icons.ImageFactory;

/**
 * EJB home
 * 
 * @author Tomas Frastia
 * @see http://www.tomecode.com
 *      http://code.google.com/p/bpel-esb-dependency-analyzer/ *
 */
public final class EjbHome implements ImageFace {

	/**
	 * ejb home name
	 */
	private String name;
	/**
	 * parent provider
	 */
	private EjbProvider ejbProvider;
	/**
	 * list of {@link EjbObject}
	 */
	private final List<EjbObject> ejbObjects;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            ejb home name
	 */
	public EjbHome(String name) {
		this.ejbObjects = new ArrayList<EjbObject>();
		this.name = name;
	}

	/**
	 * @return the ejbObjects
	 */
	public final List<EjbObject> getEjbObjects() {
		return ejbObjects;
	}

	public final String getName() {
		return name;
	}

	public final EjbProvider getEjbProvider() {
		return ejbProvider;
	}

	public final void addEjbObject(EjbObject ejbObject) {
		ejbObjects.add(ejbObject);
		ejbObject.setEjbHome(this);
	}

	/**
	 * @param ejbProvider
	 *            the ejbProvider to set
	 */
	public final void setEjbProvider(EjbProvider ejbProvider) {
		this.ejbProvider = ejbProvider;
	}

	@Override
	public final Image getImage(boolean small) {
		if (small) {
			return ImageFactory.EJB_HOME_SMALL;
		}
		return ImageFactory.EJB_HOME;
	}

	@Override
	public final String getToolTip() {
		return "Type: EJB Home\nName: " + name;
	}

}