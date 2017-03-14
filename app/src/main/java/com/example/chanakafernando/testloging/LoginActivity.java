package com.example.chanakafernando.testloging;

import android.content.Intent;
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
    String URL = "http://192.168.43.166:8000/android";




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

        if (Validation.isValidName(etUsername) && Validation.isValidPassword(etPassword)) {
            // When Email entered is Valid
            if (Validation.isValidName(etUsername)) {


                postParam.put("userName", etUsername.getText().toString());
                postParam.put("password", etPassword.getText().toString());
                Log.i("Data",postParam.toString());
                serviceCall(postParam);
            }
            // When Username is invalid
            else {
                Toast.makeText(getApplicationContext(), "User name is already taken", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }


    private void serviceCall(Map<String, String> postParam) {
        JsonObjectRequest loginDitailsObject = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(postParam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                     int status =200;//response.getInt("status");
                     String msg = response.getString("message");
                     //String err = response.getString("Error");
                     Log.i("Login Response",response.toString());
                    if(status == 200){
                        Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(LoginActivity.this, WallpostActivity.class);
                        LoginActivity.this.startActivity(homeIntent);

                    }else {
                        Toast.makeText(LoginActivity.this, "Error Occur", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.i("Login Response",response.toString());
                    Toast.makeText(LoginActivity.this, "Error Occur1111"+response.toString(), Toast.LENGTH_SHORT).show();
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




}
