package com.androidmvc.core;

import java.util.HashMap;

import com.androidmvc.interfaces.ISystemCommand;
import com.androidmvc.interfaces.IController;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;

public class Controller implements IController {

	/**
	 * Reference to the singleton instance
	 */
	protected static Controller mInstance;
	/**
	 * Mapping of Notification names to Command Class references
	 */
	private HashMap<String, ISystemCommand> mCommandMap;
	private View mView;

	protected Controller() {
		mInstance = this;
		mCommandMap = new HashMap<String, ISystemCommand>();
		mView = View.getInstance();
	}

	/**
	 * <code>Controller</code> Singleton Factory method.
	 * 
	 * @return the Singleton instance of <code>Controller</code>
	 */
	public synchronized static Controller getInstance() {
		if (mInstance == null)
			mInstance = new Controller();

		return mInstance;
	}

	@Override
	public void registerCommand(String eventName, ISystemCommand command) {
		if (null != this.mCommandMap.put(eventName, command)) {
			return;
		}
		mView.addListener(eventName, executeListener);
	}

	@Override
	public void executeCommand(IEvent event) {
		ISystemCommand commandInstance = (ISystemCommand) this.mCommandMap.get(event
				.getType());
		if (commandInstance != null) {
			commandInstance.execute(event);
		}
	}

	@Override
	public void removeCommand(String eventName) {
		// if the Command is registered...
		if (hasCommand(eventName)) {
			// remove the observer
			mView.removeListener(eventName, executeListener);
			mCommandMap.remove(eventName);
		}
	}

	@Override
	public boolean hasCommand(String eventName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private IEventListener executeListener = new IEventListener()
	{

		@Override
		public void onEvent(IEvent event) {
			executeCommand(event);
		}
		
	};

}
