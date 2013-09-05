package org.puremvc.java.common.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import org.puremvc.java.interfaces.IMediator;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.interfaces.INotifier;
import org.puremvc.java.patterns.facade.Facade;

/**
 * Created by shaunkutch on 5/18/13.
 */
public class MediatorFragment extends Fragment implements IMediator, INotifier {

    public static String NAME = "mediatorFragment";

    /**
     * Local reference to the Facade Singleton
     */
    protected Facade facade = Facade.getInstance();
    private Object viewComponent;

    @Override
    public String getMediatorName() {
        return NAME;
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
        return new String[0];
    }

    @Override
    public void handleNotification(INotification notification) {

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
        facade.sendNotification(notificationName);
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
