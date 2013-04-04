package com.androidmvc.interfaces;

public interface IProxy {
	/**
	 * Get the Proxy name
	 * 
	 * @return the Proxy instance name
	 */
	public String getProxyName();

	/**
	 * Set the data object
	 * 
	 * @param data
	 *            the data object
	 */
	public void setData(Object data);

	/**
	 * Get the data object
	 * 
	 * @return the data as type Object
	 */
	public Object getData();

	/**
	 * Called by the Model when the Proxy is registered
	 */
	public void onRegister();

	/**
	 * Called by the Model when the Proxy is removed
	 */
	public void onRemove();
}
