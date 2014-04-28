package com.oneupapplications.layoutplayground.api.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class WsApiUtil {

    private RequestQueue requestQueue;

    private static final String GET_ARTICLE_URL = "http://google2099.com/api/Article";
    private static final String GET_CATEGORY_URL = "http://google2099.com/api/Category";


    public WsApiUtil(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void queueGetArticleById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        if (id == 0)
            return;

        String url = GET_ARTICLE_URL + "?id="+ id;
        queueRequest(url, responseListener, errorListener);
    }

    public void queueGetCategoriesAll(Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        String url = GET_CATEGORY_URL;
        queueArrayRequest(url, responseListener, errorListener);
    }

    private void queueRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void queueArrayRequest(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(url, responseListener, errorListener);
        request.setShouldCache(false);
        requestQueue.add(request);
    }


}
