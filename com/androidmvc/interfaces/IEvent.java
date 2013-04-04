package com.androidmvc.interfaces;

public interface IEvent {
	
	public String getType();
	public Object getSource();
	public void setSource(Object source);
}
