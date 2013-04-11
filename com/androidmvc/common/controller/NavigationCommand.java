package com.androidmvc.common.controller;

import com.androidmvc.common.events.NavigationEvent;
import com.androidmvc.core.Router;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.patterns.Command;
import com.androidmvc.patterns.Facade;

public class NavigationCommand extends Command {
	
	@Override
	public void execute(IEvent event) {
		NavigationEvent e = (NavigationEvent)event;
		if(e.context != null)
		{
			Router.sharedRouter().open(e.path, e.context);
		}else{
			Router.sharedRouter().open(e.path);
		}
		
		NavigationEvent navEvent = new NavigationEvent(NavigationEvent.PATH_CHANGE);
		navEvent.path = e.path;
		Facade.getInstance().dispatchEvent(navEvent);
	}
}
