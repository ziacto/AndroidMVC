package com.androidmvc.common.activity;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.androidmvc.patterns.Facade;
import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;

public class MediatorFragmentActivity extends FragmentActivity implements IMediator, IDispatcher {

	protected Facade facade = Facade.getInstance();
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

	public MediatorFragmentActivity() {
		this.mediatorName = (mediatorName != null) ? mediatorName : NAME;
	}

	@Override
	public String getMediatorName() {
		return mediatorName;
	}

	@Override
	public HashMap<String, IEventListener> getEventMap() {
		return new HashMap<String, IEventListener>();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		facade.registerMediator(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		facade.removeMediator(this.getMediatorName());
	}

	@Override
	public void onRegister() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispatchEvent(IEvent event) {
		facade.dispatchEvent(event);
	}

}
