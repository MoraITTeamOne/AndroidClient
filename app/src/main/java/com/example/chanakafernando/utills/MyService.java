package com.example.chanakafernando.utills;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chanakafernando.activities.LoginActivity;
import com.example.chanakafernando.other.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMImplementation;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service
{
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;  // minute
    private static final float LOCATION_DISTANCE = 0;  //distance in meter

    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location)
        {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            double lon =mLastLocation.getLongitude();
            double lat =mLastLocation.getLatitude();
            Log.i("Location",lon+" "+lat);
            sendLocaton(lon,lat);
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public void sendLocaton(double lon,double lat){
        String transId;
        if(GlobalVariables.trainOrBus == "train"){
            transId=GlobalVariables.posibleTrain;
        }else{
            transId =GlobalVariables.posibleBus;
        }
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("userName", GlobalVariables.userName);
        postParam.put("type",GlobalVariables.trainOrBus );
        postParam.put("transId",transId);
        postParam.put("longitude",lon+"");
        postParam.put("latitude",lat+"");
        Log.i("logging",postParam.toString());

    }


    private void serviceCall(Map<String, String> postParam) {
        String URL =""
        JsonObjectRequest locationObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(postParam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status =response.getInt("status");
                    String msg = response.getString("Message");
                    Log.i("Login Response",response.toString());

                    if(status ==200 ){
                        GlobalVariables.userName =etUsername.getText().toString();
                        GlobalVariables.passWord =etPassword.getText().toString();
                        Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        dialogTraveling();


                    }else if(status==400){
                        Toast.makeText(LoginActivity.this, "Incorrect User Name or Password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(LoginActivity.this,"Error Occur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        NetConnection.getmInstanse(LoginActivity.this).addToRequestQueue(loginDitailsObject);

    }
}