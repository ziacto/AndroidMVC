package com.androidmvc.patterns;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IProxy;

public class Proxy extends EventDispatcher implements IProxy {

	public static final String NAME = "Proxy";
	
	// the proxy name
	protected String proxyName = null;

	// the data object
	protected Object data = null;

	/**
	 * Constructor
	 * 
	 * @param proxyName
	 *            Name of the <code>Proxy</code>
	 */
	public Proxy(String proxyName) 
	{
		this.proxyName = (proxyName != null) ? proxyName : NAME;
	}

	/**
	 * Get the proxy name
	 * 
	 * @return the proxy name
	 */
	public String getProxyName() {
		return this.proxyName;
	}

	/**
	 * Set the data object
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Get the data object
	 * 
	 * @return the data object
	 */
	public Object getData() {
		return this.data;
	}

	/**
	 * Called by the Model when the Proxy is registered
	 */
	public void onRegister() {
	}

	/**
	 * Called by the Model when the Proxy is removed
	 */
	public void onRemove() {
	}

}
