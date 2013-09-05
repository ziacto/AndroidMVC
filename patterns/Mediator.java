package com.androidmvc.patterns;

import java.util.HashMap;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;

public class Mediator extends EventDispatcher implements IMediator
{

	public static final String NAME = "Mediator";

	protected String mediatorName = null;

	protected Object viewComponent = null;
	
	protected Facade facade = Facade.getInstance();

	/**
	 * Get the name of the <code>Mediator</code>.
	 * 
	 * @return the name
	 */
	public final String getMediatorName() {
		return mediatorName;
	}

	/**
	 * Called by the View when the Mediator is registered
	 */
	public void onRegister() {
	}

	/**
	 * Called by the View when the Mediator is removed
	 */
	public void onRemove() {
	}

	@Override
	public HashMap<String, IEventListener> getEventMap() {
		return new HashMap<String, IEventListener>();
	}
	
	@Override
	public void dispatchEvent(IEvent event) {
		facade.dispatchEvent(event);
	}
}
