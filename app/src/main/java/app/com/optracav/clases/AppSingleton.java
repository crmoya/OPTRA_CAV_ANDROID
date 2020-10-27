package app.com.optracav.clases;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ignacio on 03-04-2019.
 */

public class AppSingleton {


    public static String url = "https://www.notariamirandaapp.cl:8081/";

    //public static String url = "http://192.168.43.102/optra/";
    public static AppSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private AppSingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }

    public static synchronized AppSingleton getInstance(Context context){
        if (mInstance==null){
            mInstance = new AppSingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }

}
