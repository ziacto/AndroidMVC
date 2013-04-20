package com.androidmvc.patterns;

import com.androidmvc.interfaces.ISystemCommand;
import com.androidmvc.interfaces.IEvent;

public class Command implements ISystemCommand {

	public void execute()
	{
		this.execute(null);
	}
	
	@Override
	public void execute(IEvent event) {
	
	}

}
