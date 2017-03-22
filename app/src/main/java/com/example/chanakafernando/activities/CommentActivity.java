package com.example.chanakafernando.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {


    public EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Button button3 = (Button) findViewById(R.id.bcomment);
        text = (EditText) findViewById(R.id.etCommentText);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> rankingData = new HashMap<String, String>();
                String transId = "";
                String rootNo = "";
                String trainName = "";
                if (GlobalVariables.trainOrBus == "train") {
                    transId = GlobalVariables.getPosibleTrainId;
                    rootNo = GlobalVariables.pTrainRouteNo;
                    trainName = GlobalVariables.posibleTrainName;
                } else {
                    transId = GlobalVariables.posibleBus;
                    rootNo = GlobalVariables.pBusRouteNo;
                }
                rankingData.put("userName", GlobalVariables.userName);
                rankingData.put("longitude", GlobalVariables.longitude + "");
                rankingData.put("latitude", GlobalVariables.latitude + "");
                rankingData.put("transId", transId);
                rankingData.put("RouteNo", rootNo);
                rankingData.put("transName", trainName);
                rankingData.put("rankType", "seats");
                rankingData.put("text", text.getText().toString());
                rankingData.put("type", GlobalVariables.trainOrBus);

                sendComment(rankingData);


                Intent registerIntent = new Intent(CommentActivity.this, CommentMenuActivity.class);
                CommentActivity.this.startActivity(registerIntent);


            }
        });
    }

    private void sendComment(Map<String, String> rankingData) {

        String URL ="http://54.68.91.2:8000/post/comment";

        JsonObjectRequest loginDitailsObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(rankingData), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status =response.getInt("status");
                    String msg = response.getString("Message");
                    Log.i("Login Response",response.toString());

                    if(status ==200 ){
                        Toast.makeText(CommentActivity.this, "Successfully Sent Your Data", Toast.LENGTH_SHORT).show();

                    }else if(status==400){
                        Toast.makeText(CommentActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CommentActivity.this,"",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(CommentActivity.this,"Error Occur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CommentActivity.this, "Something wrong please try again", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        NetConnection.getmInstanse(CommentActivity.this).addToRequestQueue(loginDitailsObject);

    }




}