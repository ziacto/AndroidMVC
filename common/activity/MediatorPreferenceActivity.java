package com.androidmvc.common.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.androidmvc.interfaces.IDispatcher;
import com.androidmvc.interfaces.IEvent;
import com.androidmvc.interfaces.IEventListener;
import com.androidmvc.interfaces.IMediator;
import com.androidmvc.patterns.Facade;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by shaunkutch on 6/25/13.
 */
public class MediatorPreferenceActivity extends PreferenceActivity implements IMediator, IDispatcher {


    protected Facade facade = Facade.getInstance();
    private HashMap<String, CopyOnWriteArrayList<IEventListener>> listenerMap;
    private IDispatcher target;

    /**
     * The default name of the <code>Mediator</code>.
     */
    public static final String NAME = "Mediator";

    /**
     * The name of the <code>Mediator</code>.
     */
    protected String mediatorName = null;

    public MediatorPreferenceActivity() {
        this.mediatorName = (mediatorName != null) ? mediatorName : NAME;
    }

    @Override
    public String getMediatorName() {
        return mediatorName;
    }

    @Override
    public HashMap<String, IEventListener> getEventMap() {
        return new HashMap<String, IEventListener>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Facade.getInstance().registerMediator(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Facade.getInstance().removeMediator(this.getMediatorName());
    }

    @Override
    public void onRegister() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRemove() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispatchEvent(IEvent event) {
        facade.dispatchEvent(event);
    }
}
