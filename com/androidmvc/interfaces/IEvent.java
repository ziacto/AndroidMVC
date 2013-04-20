package com.androidmvc.interfaces;

public interface IEvent {	
	public String getType();
	public void setSource(IDispatcher target);
	public Object getSource();
}
