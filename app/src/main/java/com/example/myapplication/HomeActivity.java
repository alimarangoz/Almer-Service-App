package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    Button getService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView serviceImgView = (ImageView)findViewById(R.id.servicesImgView);
        serviceImgView.setImageResource(R.drawable.service);

        ImageView accommodatorsImgView = (ImageView)findViewById(R.id.accommodatorsImgView);
        accommodatorsImgView.setImageResource(R.drawable.accommodators);

        ImageView rightArrowImgView = (ImageView) findViewById(R.id.rightArrowBtn1);
        rightArrowImgView.setImageResource(R.drawable.right_arrow);

        ImageView rightArrowImgView2 = (ImageView) findViewById(R.id.rightArrowBtn2);
        rightArrowImgView2.setImageResource(R.drawable.right_arrow);

        ImageView homeImgView = (ImageView) findViewById(R.id.homeImgView);
        homeImgView.setImageResource(R.drawable.home);

        ImageView profileImgView = (ImageView) findViewById(R.id.profileImgView);
        profileImgView.setImageResource(R.drawable.profile);

        getService = findViewById(R.id.getServiceBtn);
        getService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, GetServiceActivity.class);
                startActivity(intent);

            }
        });

        Button servicesBtn = findViewById(R.id.servicesBtn);
        servicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        });

        Button accommodatorsBtn = findViewById(R.id.accommodatorsBtn);
        accommodatorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AccommodatorsActivity.class);
                startActivity(intent);
            }
        });

        Button provideBtn = findViewById(R.id.provideServiceBtn);
        provideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}