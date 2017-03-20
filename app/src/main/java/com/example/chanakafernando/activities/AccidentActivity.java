package com.example.chanakafernando.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.utills.NetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);

        RatingBar rbAccident =(RatingBar)findViewById(R.id.rbAccident);


        rbAccident.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rbAccident, float rating, boolean fromUser) {
                String textValue =(String.valueOf((int)rating));
                Toast.makeText(AccidentActivity.this, "Congratulation You have added +5 Reward points ", Toast.LENGTH_LONG).show();
                Intent registerIntent = new Intent(AccidentActivity.this, CommentMenuActivity.class);
                AccidentActivity.this.startActivity(registerIntent);



            }


    });
}




    private void sendComment(Map<String, String> postParam) {
        Map<String, String> locationData = new HashMap<String, String>();
        locationData.put("userName", "Chanaka");
        locationData.put("password","19941215" );
        Log.i("logging",locationData.toString());
        String URL ="Htt";

        JsonObjectRequest loginDitailsObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(locationData), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status =response.getInt("status");
                    String msg = response.getString("Message");
                    Log.i("Login Response",response.toString());

                    if(status ==200 ){
                        Toast.makeText(AccidentActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                    }else if(status==400){
                        Toast.makeText(AccidentActivity.this, "Incorrect User Name or Password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AccidentActivity.this,"",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(AccidentActivity.this,"Error Occur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AccidentActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        NetConnection.getmInstanse(AccidentActivity.this).addToRequestQueue(loginDitailsObject);

    }


}