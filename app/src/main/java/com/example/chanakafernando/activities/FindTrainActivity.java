package com.example.chanakafernando.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.chanakafernando.activities.R;

public class FindTrainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView2;
    EditText editText2;
    Button button7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_train);


        String [] texts= {"Galle","Matara","Jaffna","Gampaha","Kandy","Anuradapura"};
        autoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,texts);
        autoCompleteTextView.setAdapter(adapter);

        String [] text = {"Galle","Matara","Jaffna","Gampaha","Kandy","Anuradapura"};
        autoCompleteTextView2=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter adapte = new ArrayAdapter(this,android.R.layout.simple_list_item_1,texts);
        autoCompleteTextView2.setAdapter(adapte);



    }
}
