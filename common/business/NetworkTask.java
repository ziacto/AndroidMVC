package org.puremvc.java.common.business;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.AsyncTask;

import org.puremvc.java.common.utils.NetworkUtils;


abstract public class NetworkTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	
	protected Context mContext;
	
	public interface NetworkCallback<Result> {
		public void onSuccess(Result result);
		public void onError(Exception exception);
	}
		
	public static interface OnNetworkUnavailableListener {
		public void onNetworkException(NetworkErrorException exception);
	}
	
	private Exception exception;
	
	private boolean isComplete = false;
	public boolean isComplete() {
		return isComplete;
	}
	
	private boolean isAborted = false;
	public boolean isAborted() { return isAborted; }
	
	private OnNetworkUnavailableListener networkUnavailableListener;
	public void setOnNetworkUnavailableListener(
			OnNetworkUnavailableListener networkUnavailableListener) {
		this.networkUnavailableListener = networkUnavailableListener;
	}
	
	private NetworkCallback<Result> mNetworkCallback;

	public void setNetworkCallback(NetworkCallback<Result> callback)
	{
		this.mNetworkCallback = callback;
	}
	
	public NetworkTask(Context context) {
		super();
		this.mContext = context;
	}
	
	public NetworkTask() {
		super();
	}
	
	/**
	 * A convenience method used to hide the poor API of the internal execute method that can't be overridden.
	 */
	@SuppressWarnings("unchecked")
	public void execute() {
		execute(null, null);
	}
	
	/**
	 * Silly AsynTask has the cancel marked as final. Use abort instead;
	 */
	public void abort() {
		isAborted = true;
		cancel(true);
	}
	
	/**
	 * This is where we make the network call. We're not passing object here, so this method must get the params
	 * it needs from the class properties. Since this is thread be sure to make as volatile if needed.
	 * 
	 * @return
	 * @throws Exception
	 */
	abstract protected Result doNetworkAction() throws Exception;
	
	/**
	 * This method runs on the UI Thread.
	 * Use this hook for what happens when the doNetworkAction method returns successfully.
	 * 
	 * @param result The result from doNetworkAction
	 */
	 protected void onPostSuccess(Result result) { }
	 protected void onPostFault(Exception e) { }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		isComplete = false;
		isAborted = false;
		boolean hasNetworkConnection = NetworkUtils.hasInternetAccess(mContext);
		if (!hasNetworkConnection) {
			if (networkUnavailableListener != null) {
				networkUnavailableListener.onNetworkException(new NetworkErrorException("Internet connection unavailable"));
			}else{
				mNetworkCallback.onError(new Exception("Internet connection unavailable"));
			}
			abort();
		}
	}
	
	/**
	 * Mostly likely you should not override this. It's not marked as final, but treat it like that.
	 */
	@Override
	protected Result doInBackground(Params... params) {
		if (isCancelled()) {
			return null;
		}
		try {
			return doNetworkAction();
		} catch (Exception e) {
			exception = e;
			return null;
		}
	}
	
	/**
	 * Out logic to figure what kind of result we got.
	 */
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		isComplete = true;
		if (isCancelled() || isAborted()) {
			return;
		}
		
		if (exception != null) {
			onPostFault(exception);
			mNetworkCallback.onError(exception);
		} 
		
		// SUCCESS!
		else {
			onPostSuccess(result);
			mNetworkCallback.onSuccess(result);
		}
	}
}
