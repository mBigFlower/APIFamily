package com.flowerfat.apilibrary.main;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式。
 * 集成了get、post
 */
public class apiVolley {

    private final String TAG = this.getClass().getSimpleName();
    private static final String apiKey = "f343d88cf9bf6c5e59fff63b3edf7136";

    private static apiVolley volley;
    private RequestQueue mRequestQueue;
    private Context mContext;

    public apiVolley() {
        mRequestQueue = getRequestQueue();
    }

    public static synchronized apiVolley getInstance() {
        if (volley == null) {
            volley = new apiVolley();
        }
        return volley;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(MyApplication.getInstance());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void cancel(String tag) {
        mRequestQueue.cancelAll(tag);
    }

    /**
     * 取消所有？里面是用this
     */
    public void cancelAll() {
        mRequestQueue.cancelAll(this);
    }

    // //////////////////////////////////////////////////////////////////////

    /**
     * 设置请求头信息
     *
     * @return
     */
    public Map<String, String> setHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("apikey", apiKey);
        headers.put("content-type", "application/x-www-form-urlencoded");
        headers.put("Charset", "UTF-8");
        return headers;
    }

    /**
     * @param stringRequest
     * @param tag
     */
    private void addToRequestQueue(StringRequest stringRequest, String tag) {
        stringRequest.setTag(tag);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f)); // 设置超时时间10s，重连次数为一次
        addToRequestQueue(stringRequest);
    }

    ////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////

    public void get(String url, String tag, Listener<String> listener,
                    ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener,
                errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return setHeaders();
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String dataString = new String(response.data, "UTF-8");
                    return Response.success(dataString,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

        };
        addToRequestQueue(stringRequest, tag);
    }

    /**
     * Post方式的Volley网络请求
     *
     * @param url
     * @param params        // post的参数
     * @param tag
     * @param listener
     * @param errorListener
     */
    public void post(String url, final Map params, String tag,
                     Listener<String> listener, ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                listener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return setHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String dataString = new String(response.data, "UTF-8");
                    return Response.success(dataString,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

        };
        addToRequestQueue(stringRequest, tag);
    }


    /**
     * just为了一个 UTF-8
     *
     * @param response
     * @return
     */
    private Response<JSONObject> parseNetWorkResponse(NetworkResponse response) {
        try {
            String dataString = new String(response.data, "UTF-8");
            return Response.success(new JSONObject(dataString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

}
