package com.androidmvc.interfaces;

public interface IDispatcher {
	void addListener(String type, IEventListener listener);
	void removeListener(String type, IEventListener listener);
	boolean hasListener(String type, IEventListener listener);
	void dispatchEvent(IEvent event);
}