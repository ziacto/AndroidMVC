package com.androidmvc.common.events;

import android.content.Context;

import com.androidmvc.events.SimpleEvent;

public class NavigationEvent extends SimpleEvent {
	
	public static String NAVIGATE = "navigate";
	public static String PATH_CHANGE = "pathChange";

	public String path;
	public Context context;
	
	public NavigationEvent(String type) {
		super(type);
	}

}
