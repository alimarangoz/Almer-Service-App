package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

//This implementation belongs to Ali Marangoz.
public class ServicesActivity extends AppCompatActivity implements JobFragment.JobListener {

    public String URL = "";
    ProgressDialog PD;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        img = findViewById(R.id.downloadedImg);

        Button downloadBtn = findViewById(R.id.btnDownload);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = "https://previews.123rf.com/images/dariusl/dariusl1209/dariusl120900013/15082837-top-service-button.jpg";
                new DownloadImage().execute(URL);
                Toast.makeText(ServicesActivity.this, "Download is completed!", Toast.LENGTH_SHORT).show();

            }
        });



    }

    class DownloadImage extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PD = new ProgressDialog(ServicesActivity.this);
            PD.setTitle("Download image in Background Thread");
            PD.setMessage("Downloading... please wait...");
            PD.setIndeterminate(false);
            PD.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            String imgUrl = URL[0];
            Bitmap bitmap = null;

            try {
                InputStream input = new java.net.URL(imgUrl).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
            PD.dismiss();
        }
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

