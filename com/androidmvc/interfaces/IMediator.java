package com.androidmvc.interfaces;

import java.util.HashMap;

public interface IMediator extends IDispatcher{

	public String getMediatorName( );

	public HashMap<String, IEventListener> getEventMap( );

	/**
	 * Called by the View when the Mediator is registered
	 */ 
	public void onRegister();

	/**
	 * Called by the View when the Mediator is removed
	 */ 
	void onRemove();
}
