package com.androidmvc.interfaces;

import java.util.HashMap;

public interface IMediator {

	public String getMediatorName();
	public HashMap<String, IEventListener> getEventMap();
	public void onRegister();
	void onRemove();
}
