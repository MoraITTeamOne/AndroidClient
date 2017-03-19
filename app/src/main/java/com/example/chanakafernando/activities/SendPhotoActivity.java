package com.example.chanakafernando.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chanakafernando.activities.R;

public class SendPhotoActivity extends AppCompatActivity {
    Button btn;
    private static final int CAMERA_REQUEST =123;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_photo);
        btn = (Button)findViewById(R.id.idbtn);
        imageView= (ImageView) findViewById(R.id.imageview);

    }

    public void btnClick(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);

    }
        public void onActivityResult(int requestCode,int resultCode,Intent data){
            if(requestCode == CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);


            }

        }


}



