/**
 * Created by Chanaka Fernando on 3/7/2017.
 */

package com.example.chanakafernando.testloging;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;



public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://192.168.43.168:8000/android";
    private Map<String ,String> params;


    public RegisterRequest(String name, String userName , int age, String password, Response.Listener<String>listener){
        super(Method.GET,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("username",userName);
        params.put("age",age+"");
        params.put("password",password);
        }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

