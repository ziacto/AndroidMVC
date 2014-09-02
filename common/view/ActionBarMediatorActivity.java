package org.puremvc.java.common.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.puremvc.java.interfaces.IFunction;
import org.puremvc.java.interfaces.IMediator;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.interfaces.INotifier;
import org.puremvc.java.patterns.facade.Facade;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by shaunkutch on 9/5/13.
 */
public class ActionBarMediatorActivity extends ActionBarActivity implements IMediator, INotifier {

    /**
     * The name of the <code>Mediator</code>.
     */
    protected String mediatorName = null;

    /**
     * Local reference to the Facade Singleton
     */
    protected Facade facade = Facade.getInstance();
    private Object viewComponent;

    protected HashMap<String, IFunction> interests = new HashMap<String, IFunction>();

    public ActionBarMediatorActivity() {
    }

    @Override
    public String getMediatorName() {
        return mediatorName;
    }

    @Override
    public Object getViewComponent() {
        return viewComponent;
    }

    @Override
    public void setViewComponent(Object viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Override
    public String[] listNotificationInterests() {
        Object[] objectArray = interests.keySet().toArray();
        String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        return stringArray;
    }

    @Override
    public void handleNotification(INotification notification) {
        IFunction function = interests.get(notification.getName());
        if(function != null) {
            function.onNotification(notification);
        }
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }

    /**
     * Send an <code>INotification</code>s.
     *
     * <P>
     * Keeps us from having to construct new notification instances in our
     * implementation code.
     *
     * @param notificationName
     *            the name of the notiification to send
     * @param body
     *            the body of the notification (optional)
     * @param type
     *            the type of the notification (optional)
     */

    public void sendNotification( String notificationName, Object body,	String type )
    {
        facade.sendNotification( notificationName, body, type );
    }

    /**
     * Send an <code>INotification</code>s.
     *
     * <P>
     * Keeps us from having to construct new notification instances in our
     * implementation code.
     *
     * @param notificationName
     *            the name of the notiification to send
     * @param body
     *            the body of the notification (optional)
     */

    public void sendNotification( String notificationName, Object body)
    {
        facade.sendNotification( notificationName, body);
    }

    /**
     * Send an <code>INotification</code>s.
     *
     * <P>
     * Keeps us from having to construct new notification instances in our
     * implementation code.
     *
     * @param notificationName
     *            the name of the notiification to send
     */

    public void sendNotification( String notificationName)
    {
        facade.sendNotification( notificationName);
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
}
