package com.example.chanakafernando.activities;

import android.app.Activity;
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
import com.example.chanakafernando.other.Movie;
import com.example.chanakafernando.other.PosibleTrainList;
import com.example.chanakafernando.utills.MyService;
import com.example.chanakafernando.utills.NetConnection;
import com.example.chanakafernando.utills.PosibleTrainListAdapter;
import com.example.chanakafernando.utills.SwipeListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;





public class Questionactivity extends AppCompatActivity  {

    private String TAG = Questionactivity.class.getSimpleName();
    //private String URL ="http://api.androidhive.info/json/imdb_top_250.php?offset=";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private PosibleTrainListAdapter adapter;
    private List<PosibleTrainList> trainList;
    public String posibleTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionactivity);

        Log.i("Helloo","jjjj"+GlobalVariables.endLocation);
        TextView find =(TextView) findViewById(R.id.tvFind) ;
        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes() -30;
        GlobalVariables.localTime=hours+""+minutes;
        Log.i("TIME",GlobalVariables.localTime);


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvStartPlace =(TextView) findViewById(R.id.tvStartPlace);
                TextView tvEndPlace = (TextView) findViewById(R.id.tvEndPlace);
                GlobalVariables.startLocation=tvStartPlace.getText().toString();
                GlobalVariables.endLocation =tvEndPlace.getText().toString();
                Log.i("StartLocation",GlobalVariables.endLocation);
                Log.i("End location",GlobalVariables.startLocation);
                getPosibleTrainList();

            }
        });







        listView = (ListView) findViewById(R.id.lv_train);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_train);

        trainList = new ArrayList<>();
        adapter = new PosibleTrainListAdapter(this, trainList);
        listView.setAdapter(adapter);





            //swipeRefreshLayout.setOnRefreshListener(this);
            /*swipeRefreshLayout.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshLayout.setRefreshing(true);
                                            getPosibleTrainList();
                                        }
                                    }

            );*/




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.tvTrainName);
                posibleTrain = textView.getText().toString();
                Log.i("PosibleTrain",posibleTrain);
                GlobalVariables.posibleTrain=posibleTrain;

                Log.i("AA",GlobalVariables.passWord);
                Log.i("AA",GlobalVariables.userName);
                Log.i("AA",GlobalVariables.trainOrBus);

                startService(new Intent(Questionactivity.this, MyService.class));//start bakground service for send location
                Intent registerIntent = new Intent(Questionactivity.this, WallpostActivity.class);
                Questionactivity.this.startActivity(registerIntent);

            }});


    }


  /*


    @Override
    public void onRefresh() {

        getPosibleTrainList();
    }
*/


    private void getPosibleTrainList() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);


        Log.i("Variable",GlobalVariables.startLocation);
        Log.i("Variables",GlobalVariables.endLocation);
        String URL ="http://54.68.91.2:8000/get/pschedule/"+GlobalVariables.startLocation+"/"+GlobalVariables.endLocation+"/"+1615;
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if (response.length() > 0) {
                            // looping through json and adding to movies list
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

                                    PosibleTrainList pTrain = new PosibleTrainList(trainId,trainName,sLoc,sTime,eLoc,eTime,type);

                                    trainList.add(0, pTrain);


                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }

                        // stopping swipe refresh
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

        // Adding request to request queue
        NetConnection.getmInstanse(Questionactivity.this).addToRequestQueue(req);

    }




}
