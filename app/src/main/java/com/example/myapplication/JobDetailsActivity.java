package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

//All Job Fragment implementations belogns to Ali Marangoz
public class JobDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        Job job = (Job) getIntent().getSerializableExtra("job");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        JobDetailFragment jdf = JobDetailFragment.newInstance(job);
        ft.add(R.id.container,jdf);
        ft.commit();
    }
}