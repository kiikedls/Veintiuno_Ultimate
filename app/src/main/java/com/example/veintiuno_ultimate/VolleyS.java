package com.example.veintiuno_ultimate;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {
    private static VolleyS mVolleyS = null;
    //    este objeto es la cola que usara la aplicacion
    private RequestQueue mRequestQueue;

    private VolleyS(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }

    public RequestQueue getmRequestQueue() { return mRequestQueue; }
}
