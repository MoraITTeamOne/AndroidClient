package com.example.chanakafernando.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.other.GlobalVariables;
import com.example.chanakafernando.utills.NetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TraficLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_level);

        RatingBar rbTrafic =(RatingBar)findViewById(R.id.rbTrafic);


        rbTrafic.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rbAccident, float rating, boolean fromUser) {
                String rankValue =(String.valueOf((int)rating));
                //======================================================
                Map<String, String> rankingData = new HashMap<String, String>();
                String transId ="";
                String rootNo ="";
                String trainName ="";
                if(GlobalVariables.trainOrBus == "train"){
                    transId=GlobalVariables.getPosibleTrainId;
                    rootNo =GlobalVariables.pTrainRouteNo;
                    trainName =GlobalVariables.posibleTrainName;

                }else{
                    transId =GlobalVariables.posibleBus;
                    rootNo =GlobalVariables.pBusRouteNo;
                }
                Map<String, String> locationData = new HashMap<String, String>();
                rankingData.put("userName", GlobalVariables.userName);
                rankingData.put("time",GlobalVariables.localTime );
                rankingData.put("longitude",GlobalVariables.longitude+"");
                rankingData.put("latitude",GlobalVariables.latitude+"");
                rankingData.put("transId",transId);
                rankingData.put("RouteNo",rootNo);
                rankingData.put("transName",trainName);
                rankingData.put("rankType","trafic");
                rankingData.put("rank",rankValue);
                rankingData.put("type",GlobalVariables.trainOrBus);

                sendComment(rankingData);



                Toast.makeText(TraficLevelActivity.this, "Congratulation You have added +5 Reward points ", Toast.LENGTH_LONG).show();
                Intent registerIntent = new Intent(TraficLevelActivity.this, CommentMenuActivity.class);
                TraficLevelActivity.this.startActivity(registerIntent);



            }


        });
    }

    private void sendComment(Map<String, String> rankingData) {

        String URL ="http://54.68.91.2:8000/post/ranking";

        JsonObjectRequest loginDitailsObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(rankingData), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status =response.getInt("status");
                    String msg = response.getString("Message");
                    Log.i("Login Response",response.toString());

                    if(status ==200 ){
                        Toast.makeText(TraficLevelActivity.this, "Successfully Sent", Toast.LENGTH_SHORT).show();

                    }else if(status==400){
                        Toast.makeText(TraficLevelActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TraficLevelActivity.this,"",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(TraficLevelActivity.this,"Error Occur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(TraficLevelActivity.this, "Something wrong please try again", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        NetConnection.getmInstanse(TraficLevelActivity.this).addToRequestQueue(loginDitailsObject);

    }

}
