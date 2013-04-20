package com.androidmvc.interfaces;

public interface IController {

	public void registerCommand(String eventName, ISystemCommand command);

	public void executeCommand(IEvent event);

	public void removeCommand(String eventName);

	public boolean hasCommand(String eventName);
}
