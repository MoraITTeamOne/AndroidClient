package com.example.chanakafernando.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.chanakafernando.activities.R;

public class TraficLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_level);

        RatingBar rbTrafic =(RatingBar)findViewById(R.id.rbTrafic);


        rbTrafic.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rbAccident, float rating, boolean fromUser) {
                String textValue =(String.valueOf((int)rating));
                //======================================================
                Toast.makeText(TraficLevelActivity.this, "Congratulation You have added +5 Reward points ", Toast.LENGTH_LONG).show();
                Intent registerIntent = new Intent(TraficLevelActivity.this, CommentMenuActivity.class);
                TraficLevelActivity.this.startActivity(registerIntent);



            }


        });
    }
}
