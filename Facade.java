package com.androidmvc;

import com.androidmvc.events.Event;
import com.androidmvc.events.EventDispatcher;

public class Facade extends EventDispatcher{

	public void sendEvent(Event event)
	{
		this.dispatchEvent(event);
	}
}
