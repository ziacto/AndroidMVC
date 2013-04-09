package com.androidmvc.common.activity;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;
import com.plastku.pingallery.App;

public class MediatorFragmentActivity extends FragmentActivity implements
		IMediator {

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

	public MediatorFragmentActivity() {
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle intentExtras = getIntent().getExtras();
		if(intentExtras != null)
		{
			mediatorName = (String) intentExtras.get("path");
		}
		
		App.getFacade().registerMediator(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		App.getFacade().removeMediator(this.getMediatorName());
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
