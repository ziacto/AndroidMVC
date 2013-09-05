package org.puremvc.java.common.view;

import android.app.Application;
import android.content.Context;
import org.puremvc.java.patterns.facade.Facade;

/**
 * Created by shaunkutch on 5/18/13.
 */
public class FacadeApplication extends Application{
	private static Facade mFacade;

	private static Context context;

	public FacadeApplication() {
		mFacade = Facade.getInstance();
	}

	public void onCreate(){
		super.onCreate();
		context = getApplicationContext();
	}

	public static Context getContext() {
		System.out.print("CONTEXT");
		return context;
	}

	public static Facade getFacade()
	{
		return mFacade;
	}
}
