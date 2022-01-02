package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {

    Button getService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView serviceImgView = (ImageView)findViewById(R.id.servicesImgView);
        serviceImgView.setImageResource(R.mipmap.ic_services_foreground);

        ImageView accommodatorsImgView = (ImageView)findViewById(R.id.accommodatorsImgView);
        accommodatorsImgView.setImageResource(R.mipmap.ic_acommodator_foreground);

        ImageView rightArrowImgView = (ImageView) findViewById(R.id.rightArrowBtn1);
        rightArrowImgView.setImageResource(R.drawable.right_arrow);

        ImageView rightArrowImgView2 = (ImageView) findViewById(R.id.rightArrowBtn2);
        rightArrowImgView2.setImageResource(R.drawable.right_arrow);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(HomeActivity.this, "Search is expanded", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(HomeActivity.this, "Search is collapse", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        menu.findItem(R.id.nav_search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.nav_search).getActionView();
        searchView.setQueryHint("Search here...");
        return super.onCreateOptionsMenu(menu);

    }
}