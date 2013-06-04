package com.androidmvc.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

	private Context mContext;
	private NetworkCallback mCallback;
	private ConnectivityManager mConnectivityManager;

	public NetworkUtils(Context context)
	{
		this.mContext = context;
		mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		mContext.registerReceiver(connectionReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}
	
	public void onDestroy()
	{
		mContext.unregisterReceiver(connectionReceiver);
	}
	
	public boolean isNetworkAvailable() {

	    return this.getNetworkInfo() != null && getNetworkInfo().isConnected();
	}
	
	public NetworkInfo getNetworkInfo()
	{
		 return mConnectivityManager.getActiveNetworkInfo();
	}
	
	public void setNetworkCallback(NetworkCallback callback)
	{
		mCallback = callback;
	}
	
	private BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(mCallback != null)
            {
            	mCallback.onConnectionChange(getNetworkInfo());
            }
        }
    };
	
    public static interface NetworkCallback
    {
    	public void onConnectionChange(NetworkInfo networkInfo);
    }
    
    public static boolean hasInternetAccess(Context context) {
		boolean hasInternet = false;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
			hasInternet = true;
		}
		
		return hasInternet;
	}
}
