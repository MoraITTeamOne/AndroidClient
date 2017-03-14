/**
 * Created by Chanaka Fernando on 3/9/2017.
 */

package com.example.chanakafernando.utills;


import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



public class NetConnection {

        private static NetConnection mInstanse;  //ok
        private RequestQueue requestQueue;  //ok
        private static Context mCtx;


        private NetConnection(Context context){
            mCtx =context;
            requestQueue = getRequestQueue();
        }

        public RequestQueue getRequestQueue(){
            if(requestQueue == null){
                requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

            }
            return requestQueue;
        }
        public static synchronized NetConnection getmInstanse(Context context){
            if(mInstanse == null){
                mInstanse = new NetConnection(context);
            }
            return mInstanse;
        }

        public <T> void addToRequestQueue(Request<T> request){

            requestQueue.add(request);
        }


}
