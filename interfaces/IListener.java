package com.androidmvc.interfaces;

public interface IListener {
	void addListener(String type, IEventListener listener);
	void removeListener(String type, IEventListener listener);
	boolean hasListener(String type, IEventListener listener);
}
