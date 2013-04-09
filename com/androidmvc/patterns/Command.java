package com.androidmvc.patterns;

import com.androidmvc.interfaces.ICommand;
import com.androidmvc.interfaces.IEvent;

public class Command implements ICommand {

	public void execute()
	{
		this.execute(null);
	}
	
	@Override
	public void execute(IEvent event) {
		
	}

}
