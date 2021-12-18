package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class GetServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_service);
        Spinner spinner = (Spinner) findViewById(R.id.subjects_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.webDesignSubjects, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button sendBtn = findViewById(R.id.getServiceSendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetServiceActivity.this, AllServicesActivity.class);
                startActivity(intent);
            }
        });

    }
}