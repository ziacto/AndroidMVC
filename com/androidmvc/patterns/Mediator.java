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
		this.viewComponent = viewComponent;
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
	 * Set the <code>IMediator</code>'s view component.
	 * 
	 * @param viewComponent
	 *            The view component
	 */
	public void setViewComponent(Object viewComponent) {
		this.viewComponent = viewComponent;
	}

	/**
	 * Get the <code>Mediator</code>'s view component.
	 * 
	 * <P>
	 * Additionally, an implicit getter will usually be defined in the subclass
	 * that casts the view object to a type, like this:
	 * </P>
	 * 
	 * <listing> private function get comboBox : mx.controls.ComboBox { return
	 * viewComponent as mx.controls.ComboBox; } </listing>
	 * 
	 * @return the view component
	 */
	public Object getViewComponent() {
		return viewComponent;
	}

	/**
	 * List the <code>INotification</code> names this <code>Mediator</code> is
	 * interested in being notified of.
	 * 
	 * @return String[] the list of <code>INotification</code> names
	 */
	public String[] listNotificationInterests() {
		return new String[] {};
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
