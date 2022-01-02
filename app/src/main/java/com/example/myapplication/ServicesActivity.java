package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class ServicesActivity extends AppCompatActivity implements JobFragment.JobListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

    }



    @Override
    public void JobSelected(Job job) {

        int display_mode = getResources().getConfiguration().orientation;

        if(display_mode == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent(this, JobDetailsActivity.class);
            intent.putExtra("job",job);
            startActivity(intent);
        }else{

            JobDetailFragment df =
                    (JobDetailFragment)getSupportFragmentManager().findFragmentByTag("job");
            if (df == null) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                df = JobDetailFragment.newInstance(job);
                fts.add(R.id.container, df, "job");
                fts.commit();
            }else{
                df.setJob(findViewById(R.id.container), job);
            }

        }



    }
}