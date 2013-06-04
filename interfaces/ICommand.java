package com.androidmvc.interfaces;

public interface ICommand<Result> {
	public Result execute() throws Exception;
}
