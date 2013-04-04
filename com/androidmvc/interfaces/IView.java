package com.androidmvc.interfaces;

public interface IView extends IDispatcher{

	public void registerMediator(IMediator mediator);

	public IMediator retrieveMediator(String mediatorName);

	public IMediator removeMediator(String mediatorName);

	public boolean hasMediator(String mediatorName);
}
