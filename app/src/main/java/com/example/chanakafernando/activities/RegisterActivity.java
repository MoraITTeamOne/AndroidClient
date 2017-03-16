package com.example.chanakafernando.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chanakafernando.utills.NetConnection;
import com.example.chanakafernando.utills.Validation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etFName = (EditText) findViewById(R.id.etFName);
        final EditText etLName = (EditText) findViewById(R.id.etLName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etNumber = (EditText) findViewById(R.id.nuTelephone);
        final Button bRegister = (Button) findViewById(R.id.bRegister);



        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = etFName.getText().toString();
                final String lastName = etLName.getText().toString();
                final String userName = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String telNumber = etNumber.getText().toString();
                final String email = etEmail.getText().toString();

                Map<String, String> postParam = new HashMap<String, String>();

                if (Validation.isValidName(etFName)) {
                    if (Validation.isValidName(etLName)) {
                        if (Validation.isValidName(etUsername)) {
                            if (Validation.isValidPassword(etPassword)) {
                                if (Validation.isValidEmail(etEmail)) {
                                    if (Validation.isValidMobile(etNumber)) {
                                        postParam.put("firstName",firstName);
                                        postParam.put("lastName",lastName);
                                        postParam.put("email",email);
                                        postParam.put("telNumber",telNumber);
                                        postParam.put("userName",userName);
                                        postParam.put("password",password);
                                        serviceCall(postParam);


                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Incorrect Phone Number", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Incorrect User Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Incorrect Last Name", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Invalid First Name", Toast.LENGTH_SHORT).show();
                } //end of the ifelse block





            }
        });


    } //oof the on click listner


    private void serviceCall(Map<String, String> postParam) {
        final String URL ="http://54.68.91.2:8000/reg/user";
        JsonObjectRequest registerDitailsObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(postParam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status =response.getInt("status");
                    String msg = response.getString("Message");
                    Log.i("Register Response",response.toString());

                    if(status ==200 ){
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(loginIntent);

                    }else if(status==400){
                        Toast.makeText(RegisterActivity.this, "Username Already Taken", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this,"Check Again",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(RegisterActivity.this,"Error Occur", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        NetConnection.getmInstanse(RegisterActivity.this).addToRequestQueue(registerDitailsObject);

    }





}
