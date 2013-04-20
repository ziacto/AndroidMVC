package com.androidmvc.events;

import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;

public class SimpleEvent implements IEvent {

	private String type;
	private IDispatcher target;
	
	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SimpleEvent(String type) {
		this.type = type;
	}

	@Override
	public void setSource(IDispatcher target) {
		this.target = target;
	}

	@Override
	public Object getSource() {
		return this.target;
	}

}
