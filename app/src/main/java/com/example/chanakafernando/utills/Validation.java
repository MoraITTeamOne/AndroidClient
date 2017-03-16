package com.example.chanakafernando.utills;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chanaka Fernando on 3/9/2017.
 */

public class Validation {
    private String email;
    private String userName;
    private String password;
    private Number tpNumber;



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }



    public static boolean isValidEmail(EditText Email){
        String email =Email.getText().toString();
        return validate(email);
    }
    //==============================================================================================
    public static boolean isValidMobile(EditText Mobile){
        String mobile =Mobile.getText().toString();
        if(mobile.length() == 10){
            return true;
        }
        return false;
    }

    //==============================================================================================

    public static boolean isValidName(EditText Name){
        String name = Name.getText().toString();
        if(name.length() < 15 && name.length() >4){
            return true;
        }
        return false;
    }

    //==============================================================================================

    public static boolean isValidPassword(EditText Password ){
        String pas =Password.getText().toString();
        if(pas.length() == 8){
           return true;
        }
        return false;
    }

    //==============================================================================================

    public static boolean isValidAge(EditText Age){
        int age = Integer.parseInt(Age.getText().toString());
        if(age >0 && age < 120)
            return true;
        return false;
    }




}
