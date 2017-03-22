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
import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.other.FutureBusSchedule;
import com.example.chanakafernando.other.FutureTrainSchedule;
import com.example.chanakafernando.other.PosibleTrainList;
import com.example.chanakafernando.utills.FutureBusScheduleAdapter;
import com.example.chanakafernando.utills.FutureTrainScheduleAdapter;
import com.example.chanakafernando.utills.NetConnection;
import com.example.chanakafernando.utills.PosibleTrainListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class TrainScheduleActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String TAG = TrainScheduleActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private FutureTrainScheduleAdapter adapter;
    private List<FutureTrainSchedule> trainscheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        listView = (ListView) findViewById(R.id.lv_train_schedule);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_train_schedule_layout);
        trainscheduleList = new ArrayList<>();
        adapter = new FutureTrainScheduleAdapter(this, trainscheduleList);
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

        String URL ="http://54.68.91.2:8000/get/pschedule/Moratuwa/Galle/"+1615;
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject trainObj = response.getJSONObject(i);

                                    String trainName = trainObj.getString("TrainName");
                                    String trainId = trainObj.getString("TrainId");
                                    String sLoc = trainObj.getString("StartLocation");
                                    String sTime = trainObj.getString("StartTime");
                                    String eLoc = trainObj.getString("EndLocation");
                                    String eTime = trainObj.getString("EndTime");
                                    String type = trainObj.getString("TrainType");
                                    String tRouteNo  =trainObj.getString("RouteNo");

                                    FutureTrainSchedule pTrain = new FutureTrainSchedule(trainId,trainName,sLoc,sTime,eLoc,eTime,type,tRouteNo);

                                    trainscheduleList.add(0, pTrain);


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

        NetConnection.getmInstanse(TrainScheduleActivity.this).addToRequestQueue(req);

    }


}
