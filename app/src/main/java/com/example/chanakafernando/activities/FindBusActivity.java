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

public class FindBusActivity extends AppCompatActivity {

    public  AutoCompleteTextView slocationForBus, eLocationForBus ;
    public  EditText searchTime ;
    public  Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bus);


        String[] texts = {"Galle", "Matara", "Jaffna", "Gampaha", "Kandy", "Anuradapura"};
        slocationForBus = (AutoCompleteTextView) findViewById(R.id.acTrainFrom);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, texts);
        slocationForBus.setAdapter(adapter);

        String[] text = {"Galle", "Matara", "Jaffna", "Gampaha", "Kandy", "Anuradapura"};
        eLocationForBus = (AutoCompleteTextView) findViewById(R.id.acTrainTo);
        ArrayAdapter adapte = new ArrayAdapter(this, android.R.layout.simple_list_item_1, texts);
        eLocationForBus.setAdapter(adapte);

         final TextView searchTime =(TextView)findViewById(R.id.tvTimeTrain);
        searchButton = (Button) findViewById(R.id.bSearchBus);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sLocation = slocationForBus.getText().toString();
                String eLocation = eLocationForBus.getText().toString();
                String time = searchButton.getText().toString();


                Intent intent=new Intent(FindBusActivity.this,FutureScheduleActivity.class);
                intent.putExtra("startLocation",sLocation);
                intent.putExtra("endLocation", eLocation);
                intent.putExtra("time", time);
                startActivity(intent);


            }
        });
    }
}
