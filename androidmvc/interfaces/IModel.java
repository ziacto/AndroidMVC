package com.androidmvc.interfaces;

public interface IModel {
	/**
	 * Register an <code>IProxy</code> instance with the <code>Model</code>.
	 * 
	 * @param proxy
	 *            an object reference to be held by the <code>Model</code>.
	 */
	public void registerProxy(IProxy proxy);

	/**
	 * Retrieve an <code>IProxy</code> instance from the Model.
	 * 
	 * @param proxy
	 * @return the <code>IProxy</code> instance previously registered with the
	 *         given <code>proxyName</code>.
	 */
	public IProxy retrieveProxy(String proxy);

	/**
	 * Remove an <code>IProxy</code> instance from the Model.
	 * 
	 * @param proxy
	 *            name of the <code>IProxy</code> instance to be removed.
	 */
	public IProxy removeProxy(String proxy);

	/**
	 * Check if a Proxy is registered
	 * 
	 * @param proxyName
	 * @return whether a Proxy is currently registered with the given
	 *         <code>proxyName</code>.
	 */
	boolean hasProxy(String proxyName);
}
