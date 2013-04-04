package com.androidmvc.interfaces;

public interface ICommand {
	
	public void execute(IEvent event);
}
