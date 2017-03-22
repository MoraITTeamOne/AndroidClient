package com.example.chanakafernando.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chanakafernando.other.FutureBusSchedule;
import com.example.chanakafernando.other.Movie;
import com.example.chanakafernando.other.PosibleBusList;
import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.utills.FutureBusScheduleAdapter;
import com.example.chanakafernando.utills.NetConnection;
import com.example.chanakafernando.utills.SwipeListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FutureScheduleActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String TAG = FutureScheduleActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private FutureBusScheduleAdapter adapter;
    private List<FutureBusSchedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_schedule);
        listView = (ListView) findViewById(R.id.lv_schedule);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_schedule_layout);
        scheduleList = new ArrayList<>();
        adapter = new FutureBusScheduleAdapter(this, scheduleList);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        getPosibleBusList();
                                    }
                                }

        );
    }



    @Override
    public void onRefresh() {

        getPosibleBusList();
    }

    private void getPosibleBusList() {
        swipeRefreshLayout.setRefreshing(true);

        String URL ="http://54.68.91.2:8000/get/possible/bus/Moratuwa/Ambalangoda/0925";
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

                                    FutureBusSchedule pBus = new FutureBusSchedule(busId,sLoc,sTime,eLoc,eTime,bRouteNo);

                                    scheduleList.add(0, pBus);


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

        NetConnection.getmInstanse(FutureScheduleActivity.this).addToRequestQueue(req);


    }


}
