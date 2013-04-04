package com.androidmvc.patterns;

import java.util.HashMap;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;

public class Mediator extends EventDispatcher implements IMediator, IDispatcher{
	/**
	 * The default name of the <code>Mediator</code>.
	 */
	public static final String NAME = "Mediator";

	/**
	 * The name of the <code>Mediator</code>.
	 */
	protected String mediatorName = null;

	/**
	 * The view component
	 */
	protected Object viewComponent = null;

	/**
	 * Constructor.
	 * 
	 * @param mediatorName
	 * @param viewComponent
	 */
	public Mediator(String mediatorName, Object viewComponent) {
		this.mediatorName = (mediatorName != null) ? mediatorName : NAME;
	}

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
}
