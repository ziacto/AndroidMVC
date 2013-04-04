package com.androidmvc.patterns;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IProxy;

public class Proxy extends EventDispatcher implements IProxy {

	// the proxy name
	protected String proxyName = "Proxy";

	// the data object
	protected Object data = null;

	/**
	 * Constructor
	 * 
	 * @param proxyName
	 * @param data
	 */
	public Proxy(String proxyName, Object data) {
		if (proxyName != null) {
			this.proxyName = proxyName;
		}
		if (data != null) {
			this.data = data;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param proxyName
	 *            Name of the <code>Proxy</code>
	 */
	public Proxy(String proxyName) {
		if (proxyName != null)
			this.proxyName = proxyName;

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
