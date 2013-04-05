package com.androidmvc.events;

import com.androidmvc.interfaces.IEvent;

public class SimpleEvent implements IEvent {

	private String type;
	@Override public String getType() { return type; }
	public void setType(String type) { 
		this.type = type; 
	}
	
	protected Object source;
	@Override public Object getSource() {
		return source;
	}
	@Override public void setSource(Object source) {
		this.source = source;
	}
	
	public SimpleEvent(String type) {
		this.type = type;
	}

}
