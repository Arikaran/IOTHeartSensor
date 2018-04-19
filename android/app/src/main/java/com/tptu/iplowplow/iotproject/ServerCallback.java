package com.tptu.iplowplow.iotproject;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Created by iPlowPlow on 18/04/2018.
 */

public interface ServerCallback {
    void onSuccess(JSONObject result);
    void onError(VolleyError error);
}