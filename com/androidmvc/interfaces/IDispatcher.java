package com.androidmvc.interfaces;

import com.androidmvc.events.Event;
import com.androidmvc.events.EventListener;


public interface IDispatcher {
	void addListener(String type, EventListener listener);
	void removeListener(String type, EventListener listener);
	boolean hasListener(String type, EventListener listener);
	void dispatchEvent(Event event);
}