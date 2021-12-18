package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AllServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);
        TextView customerNameTxt = (TextView) findViewById(R.id.customerTxtId);
        TextView jobTxtId  = (TextView) findViewById(R.id.jobTxtId);
        TextView dayTxtId = (TextView) findViewById(R.id.dayTxtId);
        TextView locationTxtId = (TextView) findViewById(R.id.locationTxtId);
        customerNameTxt.setText("Mertcan Onur");
        jobTxtId.setText("Web Design");
        dayTxtId.setText("10 days");
        locationTxtId.setText("İstanbul");

    }
}