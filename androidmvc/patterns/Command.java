package com.androidmvc.patterns;

import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.ISystemCommand;
import com.androidmvc.interfaces.IEvent;

public class Command implements ISystemCommand, IDispatcher {

	protected Facade facade = Facade.getInstance();
	
	public void execute()
	{
		this.execute(null);
	}
	
	@Override
	public void execute(IEvent event) {
	
	}

	@Override
	public void dispatchEvent(IEvent event) {
		facade.dispatchEvent(event);
	}

}
