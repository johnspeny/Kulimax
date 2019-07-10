package com.example.okker.nkulimax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ResultsActivity extends  AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ImageView iv = (ImageView)findViewById(R.id.imageView4);

    }

    public void onClick(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}

