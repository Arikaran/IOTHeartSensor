package com.tptu.iplowplow.iotproject;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by iPlowPlow on 18/04/2018.
 */

public class WebServices {

    private ArrayList<String> listConstantes = new ArrayList<>();

    public WebServices(String url, final ServerCallback serverCallback){

        String urlFiches = url ;
        // Request a string response from the provided URL.

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlFiches, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "Error: " + error.getMessage());
                serverCallback.onError(error);
            }
        });

        // Adding request to request queue
        VolleyServices.getInstance().addToRequestQueue(jsonObjReq);
    }

    public ArrayList<String> getlistConstantes() {
        return listConstantes;
    }
}


