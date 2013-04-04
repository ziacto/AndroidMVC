package com.androidmvc.activity;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.util.Log;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;

public class MediatorActivity extends Activity implements IMediator {

	private static final String TAG = EventDispatcher.class.getSimpleName();

	private HashMap<String, CopyOnWriteArrayList<IEventListener>> listenerMap;
	private IDispatcher target;
	
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

	public MediatorActivity() {
		this(null);
	}

	public MediatorActivity(IDispatcher target) {
		this.mediatorName = (mediatorName != null) ? mediatorName : NAME;
	}

	@Override
	public final String getMediatorName() {
		return mediatorName;
	}

	@Override
	public HashMap<String, IEventListener> getEventMap() {
		return new HashMap<String, IEventListener>();
	}

	@Override
	public void onRegister() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub

	}

}
