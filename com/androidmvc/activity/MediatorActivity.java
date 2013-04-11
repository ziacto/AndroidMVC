package com.androidmvc.activity;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.androidmvc.events.EventDispatcher;
import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;
import com.plastku.pingallery.App;

public class MediatorActivity extends Activity implements IMediator {
	
	/**
	 * The default name of the <code>Mediator</code>.
	 */
	public static final String NAME = "Mediator";

	/**
	 * The name of the <code>Mediator</code>.
	 */
	protected String mediatorName = null;

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
		}else{	
			mediatorName = NAME;
		}
		
		App.getFacade().registerMediator(this);
	}
	
	@Override
	public void onDestroy()
	{
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