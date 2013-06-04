package com.androidmvc.patterns;

import com.androidmvc.core.Controller;
import com.androidmvc.core.Model;
import com.androidmvc.core.View;
import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.ISystemCommand;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IFacade;
import com.androidmvc.interfaces.IMediator;
import com.androidmvc.interfaces.IProxy;

public class Facade implements IFacade {

	/**
	 * The Singleton instance of the Facade
	 */
	protected static Facade instance = null;

	/**
	 * Reference to the Controller
	 */
	protected Controller controller = null;

	/**
	 * Reference to the Model
	 */
	protected Model model = null;

	/**
	 * Reference to the View
	 */
	protected View view = null;


	protected Facade() {
		initializeFacade();
	}

	protected void initializeFacade() {
		initializeModel();
		initializeController();
		initializeView();
	}

	/**
	 * Facade Singleton Factory method
	 * 
	 * @return The Singleton instance of the Facade
	 */
	public synchronized static Facade getInstance() {
		if (instance == null)
			instance = new Facade();

		return instance;
	}

	/**
	 * Initialize the <code>Controller</code>.
	 * 
	 * <P>
	 * Called by the <code>initializeFacade</code> method. Override this method
	 * in your subclass of <code>Facade</code> if one or both of the following
	 * are true:
	 * <UL>
	 * <LI>You wish to initialize a different <code>IController</code>.</LI>
	 * <LI>You have <code>Commands</code> to register with the
	 * <code>Controller</code> at startup.</code>.</LI>
	 * </UL>
	 * If you don't want to initialize a different <code>IController</code>,
	 * call <code>super.initializeController()</code> at the beginning of your
	 * method, then register <code>Command</code>s.
	 * </P>
	 */
	protected void initializeController() {
		if (controller != null)
			return;

		controller = Controller.getInstance();
	}

	/**
	 * Initialize the <code>Model</code>.
	 * 
	 * <P>
	 * Called by the <code>initializeFacade</code> method. Override this method
	 * in your subclass of <code>Facade</code> if one or both of the following
	 * are true:
	 * <UL>
	 * <LI>You wish to initialize a different <code>IModel</code>.</LI>
	 * <LI>You have <code>Proxy</code>s to register with the Model that do not
	 * retrieve a reference to the Facade at construction time.</code></LI>
	 * </UL>
	 * If you don't want to initialize a different <code>IModel</code>, call
	 * <code>super.initializeModel()</code> at the beginning of your method,
	 * then register <code>Proxy</code>s.
	 * <P>
	 * Note: This method is <i>rarely</i> overridden; in practice you are more
	 * likely to use a <code>Command</code> to create and register
	 * <code>Proxy</code>s with the <code>Model</code>, since <code>Proxy</code>
	 * s with mutable data will likely need to send <code>INotification</code>s
	 * and thus will likely want to fetch a reference to the <code>Facade</code>
	 * during their construction.
	 * </P>
	 */
	protected void initializeModel() {
		if (model != null)
			return;

		model = Model.getInstance();
	}

	/**
	 * Initialize the <code>View</code>.
	 * 
	 * <P>
	 * Called by the <code>initializeFacade</code> method. Override this method
	 * in your subclass of <code>Facade</code> if one or both of the following
	 * are true:
	 * <UL>
	 * <LI>You wish to initialize a different <code>IView</code>.</LI>
	 * <LI>You have <code>Observers</code> to register with the
	 * <code>View</code></LI>
	 * </UL>
	 * If you don't want to initialize a different <code>IView</code>, call
	 * <code>super.initializeView()</code> at the beginning of your method, then
	 * register <code>IMediator</code> instances.
	 * <P>
	 * Note: This method is <i>rarely</i> overridden; in practice you are more
	 * likely to use a <code>Command</code> to create and register
	 * <code>Mediator</code>s with the <code>View</code>, since
	 * <code>IMediator</code> instances will need to send
	 * <code>INotification</code>s and thus will likely want to fetch a
	 * reference to the <code>Facade</code> during their construction.
	 * </P>
	 */
	protected void initializeView() {
		if (view != null)
			return;

		view = View.getInstance();
	}

	/**
	 * Register an <code>ICommand</code> with the <code>Controller</code> by
	 * Notification name.
	 * 
	 * @param noteName
	 *            the name of the <code>INotification</code> to associate the
	 *            <code>ICommand</code> with
	 * @param command
	 *            an instance of the <code>ICommand</code>
	 */
	public void registerCommand(String eventName, ISystemCommand command) {
		controller.registerCommand(eventName, command);
	}

	/**
	 * Remove a previously registered <code>ICommand</code> to
	 * <code>INotification</code> mapping from the Controller.
	 * 
	 * @param eventName
	 *            the name of the <code>INotification</code> to remove the
	 *            <code>ICommand</code> mapping for
	 */
	public void removeCommand(String eventName) {
		this.controller.removeCommand(eventName);
	}

	/**
	 * Check if a Command is registered for a given Notification
	 * 
	 * @param eventName
	 * @return whether a Command is currently registered for the given
	 *         <code>eventName</code>.
	 */
	public boolean hasCommand(String eventName) {
		return controller.hasCommand(eventName);
	}

	/**
	 * Register a <code>IMediator</code> with the <code>View</code>.
	 * 
	 * @param mediator
	 *            the name to associate with this <code>IMediator</code>
	 */
	public void registerMediator(IMediator mediator) {
		if (this.view != null) {
			this.view.registerMediator(mediator);
		}
	}

	/**
	 * Register an <code>IProxy</code> with the <code>Model</code> by name.
	 * 
	 * @param proxy
	 *            the name of the <code>IProxy</code> instance to be registered
	 *            with the <code>Model</code>.
	 */
	public void registerProxy(IProxy proxy) {
		this.model.registerProxy(proxy);
	}

	/**
	 * Remove an <code>IMediator</code> from the <code>View</code>.
	 * 
	 * @param mediatorName
	 *            name of the <code>IMediator</code> to be removed.
	 * @return the <code>IMediator</code> that was removed from the
	 *         <code>View</code>
	 */
	public IMediator removeMediator(String mediatorName) {
		if (this.view != null) {
			return this.view.removeMediator(mediatorName);
		}
		return null;
	}

	/**
	 * Remove an <code>IProxy</code> from the <code>Model</code> by name.
	 * 
	 * @param proxyName
	 *            the <code>IProxy</code> to remove from the <code>Model</code>.
	 * @return the <code>IProxy</code> that was removed from the
	 *         <code>Model</code>
	 */
	public IProxy removeProxy(String proxyName) {
		if (this.model != null) {
			return this.model.removeProxy(proxyName);
		}
		return null;
	}

	/**
	 * Check if a Proxy is registered
	 * 
	 * @param proxyName
	 * @return whether a Proxy is currently registered with the given
	 *         <code>proxyName</code>.
	 */
	public boolean hasProxy(String proxyName) {
		return model.hasProxy(proxyName);
	}

	/**
	 * Check if a Mediator is registered or not
	 * 
	 * @param mediatorName
	 * @return whether a Mediator is registered with the given
	 *         <code>mediatorName</code>.
	 */
	public boolean hasMediator(String mediatorName) {
		return view.hasMediator(mediatorName);
	}

	/**
	 * Retrieve an <code>IMediator</code> from the <code>View</code>.
	 * 
	 * @param mediatorName
	 * @return the <code>IMediator</code> previously registered with the given
	 *         <code>mediatorName</code>.
	 */
	public IMediator retrieveMediator(String mediatorName) {
		return this.view.retrieveMediator(mediatorName);
	}

	/**
	 * Retrieve an <code>IProxy</code> from the <code>Model</code> by name.
	 * 
	 * @param proxyName
	 *            the name of the proxy to be retrieved.
	 * @return the <code>IProxy</code> instance previously registered with the
	 *         given <code>proxyName</code>.
	 */
	public IProxy retrieveProxy(String proxyName) {
		return this.model.retrieveProxy(proxyName);
	}

	@Override
	public void dispatchEvent(IEvent event) {
		if (view != null)
			view.dispatchEvent(event);
	}
}
