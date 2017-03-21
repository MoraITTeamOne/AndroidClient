package com.example.chanakafernando.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.other.GlobalVariables;
import com.example.chanakafernando.other.PosibleBusList;
import com.example.chanakafernando.utills.MyService;
import com.example.chanakafernando.utills.NetConnection;
import com.example.chanakafernando.utills.PosibleBusAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectYourBusActivity extends AppCompatActivity {
    private String TAG = SelectYourBusActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private PosibleBusAdapter adapter;
    private List<PosibleBusList> busList;
    public String posibleBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_bus);

        TextView findBus =(TextView) findViewById(R.id.tvSelectYourBus) ;
        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes() -30;
        GlobalVariables.localTime=hours+""+minutes;
        Log.i("TIME",GlobalVariables.localTime);


        findBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvStartPlace =(TextView) findViewById(R.id.tvBusStartLocation);
                TextView tvEndPlace = (TextView) findViewById(R.id.tvBusEndLocation);
                GlobalVariables.startLocation=tvStartPlace.getText().toString();
                GlobalVariables.endLocation =tvEndPlace.getText().toString();
                Log.i("StartLocation",GlobalVariables.endLocation);
                Log.i("End location",GlobalVariables.startLocation);
                getPosibleBusList();
            }
        });



        listView = (ListView) findViewById(R.id.lv_bus);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_bus);

        busList = new ArrayList<>();
        adapter = new PosibleBusAdapter(this, busList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView busId =(TextView) view.findViewById(R.id.tvBusId);
                TextView rootNo=(TextView) view.findViewById(R.id.tvBusRoute);
                TextView sLoc=(TextView) view.findViewById(R.id.tvBStartPlace) ;
                TextView eLoc=(TextView) view.findViewById(R.id.tvBusELocation);



                String busNumber = busId.getText().toString();
                String busRoute = rootNo.getText().toString();
                String startLocation = sLoc.getText().toString();
                String endLocation = eLoc.getText().toString();


                GlobalVariables.pBusId=busNumber;
                GlobalVariables.pBusRouteNo =busRoute;
                GlobalVariables.startLocation=startLocation;
                GlobalVariables.endLocation=endLocation;

                Log.i("AA",GlobalVariables.passWord);
                Log.i("AA",GlobalVariables.userName);

                Log.i("AA",GlobalVariables.pBusId);
                Log.i("AA",GlobalVariables.pBusRouteNo);
                Log.i("AA",GlobalVariables.startLocation);
                Log.i("AA",GlobalVariables.endLocation);



                startService(new Intent(SelectYourBusActivity.this, MyService.class));//start bakground service for send location
                Intent registerIntent = new Intent(SelectYourBusActivity.this, WallpostActivity.class);
                SelectYourBusActivity.this.startActivity(registerIntent);

            }});






    }


    private void getPosibleBusList() {
        swipeRefreshLayout.setRefreshing(true);
        String URL ="http://54.68.91.2:8000/get/possible/bus/Fort/Kegalle/1200";
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject trainObj = response.getJSONObject(i);

                                    String busId = trainObj.getString("busId");
                                    String sLoc = trainObj.getString("StartLocation");
                                    String sTime = trainObj.getString("StartTime");
                                    String eLoc = trainObj.getString("EndLocation");
                                    String eTime = trainObj.getString("EndTime");
                                    String bRouteNo  =trainObj.getString("RouteNo");

                                    PosibleBusList pBus = new PosibleBusList(busId,sLoc,sTime,eLoc,eTime,bRouteNo);

                                    busList.add(0, pBus);


                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }

                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        NetConnection.getmInstanse(SelectYourBusActivity.this).addToRequestQueue(req);


    }
}
