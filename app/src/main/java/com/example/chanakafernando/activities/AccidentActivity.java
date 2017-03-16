package com.example.chanakafernando.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.chanakafernando.activities.R;

public class AccidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);

        RatingBar rbAccident =(RatingBar)findViewById(R.id.rbAccident);


        rbAccident.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rbAccident, float rating, boolean fromUser) {
                String textValue =(String.valueOf((int)rating));
                Toast.makeText(AccidentActivity.this, textValue, Toast.LENGTH_SHORT).show();


            }


    });
}

}