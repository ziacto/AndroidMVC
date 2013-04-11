package com.androidmvc.fragment;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;
import com.plastku.pingallery.App;

public class MediatorFragment extends Fragment implements IMediator {
	
	public static final String NAME = "Mediator";
	
	protected String mediatorName;
	
	public MediatorFragment()
	{
		this.mediatorName = (mediatorName != null) ? mediatorName : NAME;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getFacade().registerMediator(this);
	}
	
	@Override
	public void onDetach()
	{
		super.onDetach();
		App.getFacade().removeMediator(this.getMediatorName());
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
	public void onRegister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

}
