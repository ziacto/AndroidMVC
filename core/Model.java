package com.androidmvc.core;

import java.util.HashMap;
import java.util.Map;

import com.androidmvc.interfaces.IModel;
import com.androidmvc.interfaces.IProxy;

public class Model implements IModel {

	/**
	 * Singleton instance
	 */
	protected static Model instance;

	/**
	 * Mapping of proxyNames to IProxy instances
	 */
	protected Map<String, IProxy> proxyMap;

	protected Model() {
		instance = this;
		proxyMap = new HashMap<String, IProxy>();
	}

	public synchronized static Model getInstance() {
		if (instance == null)
			instance = new Model();

		return instance;
	}

	@Override
	public void registerProxy(IProxy proxy) {
		this.proxyMap.put(proxy.getProxyName(), proxy);
		proxy.onRegister();
	}

	@Override
	public IProxy retrieveProxy(String proxy) {
		return (IProxy) this.proxyMap.get(proxy);
	}

	@Override
	public IProxy removeProxy(String proxyName) {
		IProxy proxy = (IProxy) this.proxyMap.get(proxyName);

		if (proxy != null) {
			this.proxyMap.remove(proxyName);
			proxy.onRemove();
		}

		return proxy;
	}

	@Override
	public boolean hasProxy(String proxyName) {
		return proxyMap.containsKey( proxyName );
	}

}
