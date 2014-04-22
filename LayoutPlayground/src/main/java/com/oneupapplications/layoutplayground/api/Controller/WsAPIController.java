package com.oneupapplications.layoutplayground.api.Controller;


import android.content.Context;
import com.android.volley.Response;
import com.oneupapplications.layoutplayground.api.dao.WsApiDao;
import org.json.JSONObject;

public class WsAPIController {


    private static WsAPIController controller;
    private WsApiDao apiDao;

    private WsAPIController(Context context) {
        apiDao = new WsApiDao(context);
    }

    public static WsAPIController getInstance(Context context) {
        if (controller == null)
            controller = new WsAPIController(context);

        return controller;
    }

    public void getArticleById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getArticleById(id, responseListener, errorListener);
    }

}
