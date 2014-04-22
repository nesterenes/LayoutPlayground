package com.oneupapplications.layoutplayground.api.dao;

import android.content.Context;

import com.android.volley.Response;
import com.oneupapplications.layoutplayground.api.util.WsApiUtil;

import org.json.JSONObject;

public class WsApiDao
{
    private WsApiUtil apiUtil;

    public WsApiDao(Context context)  {
        this.apiUtil = new WsApiUtil(context);
    }

    public void getArticleById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetArticleById(id, responseListener, errorListener);
    }

}
