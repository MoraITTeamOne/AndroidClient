package com.example.chanakafernando.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chanakafernando.other.GlobalVariables;
import com.example.chanakafernando.utills.NetConnection;
import com.example.chanakafernando.utills.Validation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button bLogin;
    TextView registerLink;
    String URL ="http://54.68.91.2:8000/login/user";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);

        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvToRegister);


        /**
         * change UI from Loging to Register
         */
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }

        //******************************************************************

        /**
         * Method gets triggered when Login button is clicked
         *
         * @param view
         */

    public void userLogin(View view) {
        Map<String, String> postParam = new HashMap<String, String>();

        if (Validation.isValidName(etUsername)) {

            if(Validation.isValidPassword(etPassword)){
                postParam.put("userName", etUsername.getText().toString());
                postParam.put("password", etPassword.getText().toString());
                Log.i("logging",postParam.toString());
                serviceCall(postParam);
            }else{
                Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, "8 characters must", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this, "Incorrect User Name", Toast.LENGTH_SHORT).show();
        }


    }


    private void serviceCall(Map<String, String> postParam) {
        JsonObjectRequest loginDitailsObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(postParam), new Response.Listener<JSONObject>() {
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


    private void dialogTraveling(){
        AlertDialog.Builder adTraveling = new AlertDialog.Builder(LoginActivity.this);
        adTraveling.setMessage("Hey dude! Are You Traveling?");
        adTraveling.setCancelable(true);
        adTraveling.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GlobalVariables.areUtravel =true;
                        dialog.cancel();
                        dialogTrainOrBus();
                    }
                });
        adTraveling.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog1 = adTraveling.create();
        dialog1.show();

    }

    private void dialogTrainOrBus(){
        AlertDialog.Builder adTrainBus = new AlertDialog.Builder(LoginActivity.this);
        adTrainBus.setMessage("Hey Bro.. Train Or Bus?");
        adTrainBus.setCancelable(true);
        adTrainBus.setPositiveButton(
                "Train",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        GlobalVariables.trainOrBus ="train";
                        Intent homeIntent = new Intent(LoginActivity.this, Questionactivity.class);
                        LoginActivity.this.startActivity(homeIntent);
                    }
                });
        adTrainBus.setNegativeButton(
                "Bus",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        GlobalVariables.trainOrBus="bus";
                        Intent homeIntent = new Intent(LoginActivity.this, SelectYourBusActivity.class);
                        LoginActivity.this.startActivity(homeIntent);
                    }
                });
        AlertDialog dialog2 = adTrainBus.create();
        dialog2.show();
    }




}
