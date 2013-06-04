package com.androidmvc.fragment;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.androidmvc.patterns.Facade;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;

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
		Facade.getInstance().registerMediator(this);
	}
	
	@Override
	public void onDetach()
	{
		super.onDetach();
		Facade.getInstance().removeMediator(this.getMediatorName());
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
