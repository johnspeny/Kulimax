package com.example.okker.nkulimax;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView iv = (ImageView)findViewById(R.id.imageView4);

    }

    public void onClick(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
