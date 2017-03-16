package com.example.chanakafernando.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CommentMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_menu);
        ImageButton ibSeats = (ImageButton) findViewById(R.id.ibSeats);
        ImageButton ibAccident = (ImageButton) findViewById(R.id.ibAccident);
        ImageButton ibCrowd =(ImageButton) findViewById(R.id.ibCrowd);
        ImageButton ibTrafic = (ImageButton) findViewById(R.id.ibTrafic);
        Button bComment =(Button) findViewById(R.id.bComment);




        /**
         * change UI from Menu to Menu item
         */
        bComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postComment = new Intent(CommentMenuActivity.this, CommentActivity.class);
                CommentMenuActivity.this.startActivity(postComment);
            }
        }); //EOF bComment block


        ibSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seatsIntent = new Intent(CommentMenuActivity.this, SeatsActivity.class);
                CommentMenuActivity.this.startActivity(seatsIntent);
            }
        }); //EoF seat activity

        ibTrafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent traficLevelIntent = new Intent(CommentMenuActivity.this, TraficLevelActivity.class);
                CommentMenuActivity.this.startActivity(traficLevelIntent);
            }
        });// eof trafic level activity

        ibAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accidentIntent = new Intent(CommentMenuActivity.this,AccidentActivity.class);
                CommentMenuActivity.this.startActivity(accidentIntent);
            }
        });// eof Accident activity

        ibCrowd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crowdLevelIntent = new Intent(CommentMenuActivity.this, CrowdLevelActivity.class);
                CommentMenuActivity.this.startActivity(crowdLevelIntent);
            }
        });// eof crowd level activity




    }
}
