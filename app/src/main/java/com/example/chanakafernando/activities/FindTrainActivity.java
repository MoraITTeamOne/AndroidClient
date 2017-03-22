package com.example.chanakafernando.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindTrainActivity extends AppCompatActivity {

    AutoCompleteTextView slocationForTrain,eLocationForTrain;
    EditText Train_time;
    Button findTrain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_train);


        String [] texts= {"Galle","Matara","Jaffna","Gampaha","Kandy","Anuradapura"};
        slocationForTrain=(AutoCompleteTextView) findViewById(R.id.acTrainFrom);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,texts);
        slocationForTrain.setAdapter(adapter);

        String [] text = {"Galle","Matara","Jaffna","Gampaha","Kandy","Anuradapura"};
        eLocationForTrain=(AutoCompleteTextView) findViewById(R.id.acTrainTo);
        ArrayAdapter adapte = new ArrayAdapter(this,android.R.layout.simple_list_item_1,texts);
        eLocationForTrain.setAdapter(adapte);
        TextView time =(TextView) findViewById(R.id.tvTimeTrain);
        Button findTrain =(Button) findViewById(R.id.bFindTrain);


        final String sLocation = slocationForTrain.getText().toString();
        final String eLocation = eLocationForTrain.getText().toString();
        final String TrainTime = time.getText().toString();


        findTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindTrainActivity.this, TrainScheduleActivity.class);
                intent.putExtra("startLocation", sLocation);
                intent.putExtra("endLocation", eLocation);
                intent.putExtra("time", TrainTime);
                startActivity(intent);
            }});

    }
}
