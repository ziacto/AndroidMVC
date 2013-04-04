package com.androidmvc.events;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IDispatcher;

import android.util.Log;


public class EventDispatcher implements IDispatcher {
	
	private static final String TAG = EventDispatcher.class.getSimpleName();
	
	private HashMap<String, CopyOnWriteArrayList<IEventListener>> listenerMap;
	private IDispatcher target;
	
	public EventDispatcher() {
		this(null);
	}
	public EventDispatcher(IDispatcher target) {
		listenerMap = new HashMap<String, CopyOnWriteArrayList<IEventListener>>();
		this.target = (target != null) ? target : this;
	}
	
	@Override
	public void addListener(String type, IEventListener listener) {
		synchronized (listenerMap) {
			CopyOnWriteArrayList<IEventListener> list = listenerMap.get(type);
			if (list == null) {
				list = new CopyOnWriteArrayList<IEventListener>();
				listenerMap.put(type, list);
			}
			list.add(listener);
		}
	}
	
	@Override
	public void removeListener(String type, IEventListener listener) {
		synchronized (listenerMap) {
			CopyOnWriteArrayList<IEventListener> list = listenerMap.get(type);
			if (list == null) return;
			list.remove(listener);
			if (list.size() == 0) {
				listenerMap.remove(type);
			}
		}
	}
	
	@Override
	public boolean hasListener(String type, IEventListener listener) {
		synchronized (listenerMap) {
			CopyOnWriteArrayList<IEventListener> list = listenerMap.get(type);
			if (list == null) return false;
			return list.contains(listener);
		}
	}
	
	@Override
	public void dispatchEvent(IEvent event) {
		if (event == null) {
			Log.e(TAG, "can not dispatch null event");
			return;
		}
		String type = event.getType();
		event.setSource(target);
		CopyOnWriteArrayList<IEventListener> list;
		synchronized (listenerMap) {
			list = listenerMap.get(type);
		}
		if (list == null) return;
		for (IEventListener l : list) {
			l.onEvent(event);
		}
	}
	
	public void dispose() {
		synchronized (listenerMap) {
			listenerMap.clear();
		}
		target = null;
	}
}
