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
import com.example.chanakafernando.utills.MyService;
import com.example.chanakafernando.utills.NetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SeatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);


        RatingBar rbAccident =(RatingBar)findViewById(R.id.rbSeats);


        rbAccident.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rbAccident, float rating, boolean fromUser) {
                String rateValue =(String.valueOf((int)rating));

                ranking(rateValue);
                Intent registerIntent = new Intent(SeatsActivity.this, CommentMenuActivity.class);
                SeatsActivity.this.startActivity(registerIntent);



            }


        });
    }

    public void ranking(String userRank){
        String transId;
        if(GlobalVariables.trainOrBus == "train"){
            transId=GlobalVariables.getPosibleTrainId;
        }else{
            transId =GlobalVariables.posibleBus;
        }
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("userName", GlobalVariables.userName);
        postParam.put("type",GlobalVariables.trainOrBus );
        postParam.put("transId",transId);
        postParam.put("longitude",GlobalVariables.longitude+"");
        postParam.put("latitude",GlobalVariables.latitude+"");
        postParam.put("rankType","S");
        postParam.put("rank",userRank);
        postParam.put("time",GlobalVariables.localTime);
        postParam.put("route","");
        postParam.put("TransPortName",GlobalVariables.posibleTrainName);

        Log.i("logging",postParam.toString());
        sendRankingData(postParam);  // send user's location

    }


    private void sendRankingData(Map<String, String> postParam) {
        String URL ="http://54.68.91.2:8000/post/ranking";
        JsonObjectRequest locationObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(postParam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status =response.getInt("status");
                    String msg = response.getString("Message");
                    Log.i("Login Response",response.toString());

                    if(status ==200 ){
                        Toast.makeText(SeatsActivity.this, "Congratulation You have added +5 Reward points ", Toast.LENGTH_LONG).show();

                    }else if(status==500){
                        Toast.makeText(SeatsActivity.this, "Does not save location ranking data", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SeatsActivity.this,"1111",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(SeatsActivity.this,"Error Occur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SeatsActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        NetConnection.getmInstanse(SeatsActivity.this).addToRequestQueue(locationObject);

    }

}
