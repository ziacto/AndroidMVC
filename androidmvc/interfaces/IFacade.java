package com.androidmvc.interfaces;

public interface IFacade {

	public void dispatchEvent(IEvent event);

	public void registerProxy(IProxy proxy);

	public IProxy retrieveProxy(String proxyName);

	public IProxy removeProxy(String proxyName);

	public boolean hasProxy(String proxyName);

	public void registerCommand(String eventName, ISystemCommand commandClassRef);

	public void removeCommand(String eventName);

	public boolean hasCommand(String eventName);

	public void registerMediator(IMediator mediator);

	public IMediator retrieveMediator(String mediatorName);

	public boolean hasMediator(String mediatorName);

	public IMediator removeMediator(String mediatorName);
}
