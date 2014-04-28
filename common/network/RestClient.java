package org.puremvc.java.common.network;

import android.os.Build;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaunkutch on 10/2/13.
 */
public class RestClient {
    private static String baseUrl;
    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private static HttpClient httpClient = asyncHttpClient.getHttpClient();
    private static HashMap<String, String> defaultParams = new HashMap<String, String>();

	public static void postAsync(String url, AsyncHttpResponseHandler responseHandler)
	{
		postAsync(url, getDefaultParams(), responseHandler);
	}

    public static void postAsync(String url, HashMap<String, String> params, AsyncHttpResponseHandler responseHandler) {
        //Convert to RequestParams
        RequestParams requestParams = new RequestParams(getDefaultParams(params));
        asyncHttpClient.post(getAbsoluteUrl(url), requestParams, responseHandler);
    }

	public static void post(String url, SyncHttpResponseHandler responseHandler)
	{
		post(url, getDefaultParams(), responseHandler);
	}

    public static void post(String url, HashMap<String, String> params, SyncHttpResponseHandler responseHandler)
    {
        HashMap<String, String> requestParams = getDefaultParams(params);
        HttpPost httpPost = new HttpPost(getAbsoluteUrl(url));
        String response = null;
        try {
            //Convert to NameValuePair
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            ResponseHandler<String> httpResponseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpPost, httpResponseHandler);
            responseHandler.onSuccess(response);

        } catch (ClientProtocolException e) {
            responseHandler.onFailure(e);
        } catch (IOException e) {
            responseHandler.onFailure(e);
        }
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String url) {
        baseUrl = url;
    }

    public static void setDefaultParams(HashMap<String, String> params)
    {
        defaultParams = params;
    }

    public static void addDefaultParam(String key, String value)
    {
        defaultParams.put(key, value);
    }

    public static void removeDefaultParam(String key)
    {
        defaultParams.remove(key);
    }

    private static HashMap<String, String> getDefaultParams()
    {
        return defaultParams;
    }

    private static HashMap<String, String> getDefaultParams(HashMap<String, String> params)
    {
        HashMap<String, String> mergedParams = new HashMap<String, String>(defaultParams);
        if(params != null)
        {
            mergedParams.putAll(params);
        }
        return mergedParams;
    }

	public static void setTimeout(int timeout)
	{
		asyncHttpClient.setTimeout(timeout);
	}

	public static void setUserAgent(String agent)
	{
		asyncHttpClient.setUserAgent(agent);
	}

    public interface SyncHttpResponseHandler
    {
        public void onSuccess(String response);
        public void onFailure(Throwable throwable);
    }
}
