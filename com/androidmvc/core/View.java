package com.androidmvc.core;

import java.util.HashMap;
import java.util.Map.Entry;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;
import com.androidmvc.interfaces.IView;

public class View extends EventDispatcher implements IView {

	private HashMap<String, IMediator> mediatorMap;
	private static View mInstance;

	protected View() {
		mInstance = this;

		this.mediatorMap = new HashMap<String, IMediator>();
	}

	/**
	 * View Singleton Factory method.
	 * 
	 * @return the Singleton instance of <code>View</code>
	 */
	public synchronized static View getInstance() {
		if (mInstance == null) {
			mInstance = new View();
		}
		return mInstance;
	}

	@Override
	public void registerMediator(IMediator mediator) {
		if (this.mediatorMap.containsKey(mediator.getMediatorName()))
			return;

		// Register the Mediator for retrieval by name
		this.mediatorMap.put(mediator.getMediatorName(), mediator);

		// Get Notification interests, if any.
		HashMap<String, IEventListener> interests = mediator.getEventMap();
		if (interests.size() != 0) {
			for (Entry<String, IEventListener> entry : interests.entrySet()) {
				String key = entry.getKey();
				IEventListener value = entry.getValue();
				this.addListener(key, value);
			}
		}
		// alert the mediator that it has been registered
		mediator.onRegister();
	}

	@Override
	public IMediator retrieveMediator(String mediatorName) {
		return (IMediator) this.mediatorMap.get(mediatorName);
	}

	@Override
	public IMediator removeMediator(String mediatorName) {
		// Retrieve the named mediator
		IMediator mediator = mediatorMap.get(mediatorName);

		if (mediator != null) {
			// for every notification this mediator is interested in...
			HashMap<String, IEventListener> interests = mediator.getEventMap();
			for (Entry<String, IEventListener> entry : interests.entrySet()) {
				String key = entry.getKey();
				IEventListener value = entry.getValue();
				this.removeListener(key, value);
			}

			// remove the mediator from the map
			mediatorMap.remove(mediatorName);

			// alert the mediator that it has been removed
			mediator.onRemove();
		}

		return mediator;
	}

	@Override
	public boolean hasMediator(String mediatorName) {
		return mediatorMap.containsKey(mediatorName);
	}

}
