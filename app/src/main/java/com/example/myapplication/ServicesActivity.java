package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ImageButton webDesignImgBtn = (ImageButton)findViewById(R.id.webDesignImageButtonId);
        webDesignImgBtn.setImageResource(R.mipmap.ic_web_design_foreground);
        webDesignImgBtn.setBackgroundColor(Color.WHITE);

        ImageButton privateLessonImgBtn = (ImageButton)findViewById(R.id.privateLessonImgBtn);
        privateLessonImgBtn.setImageResource(R.mipmap.ic_private_lesson_foreground);
        privateLessonImgBtn.setBackgroundColor(Color.WHITE);

        privateLessonImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServicesActivity.this,GetServiceActivity.class);
                startActivity(intent);
            }
        });


        webDesignImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServicesActivity.this,GetServiceActivity.class);
                startActivity(intent);
            }
        });

    }
}