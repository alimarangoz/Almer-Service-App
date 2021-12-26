package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllServicesActivity extends AppCompatActivity {

    private RecyclerView allServiceRV;
    private ArrayList<User> usersArrayList;
    private AllServicesRVAdapter allServiceRVAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);

        allServiceRV = findViewById(R.id.idRVAllServices);
        loadingPB = findViewById(R.id.idProgressBar2);

        db = FirebaseFirestore.getInstance();

        usersArrayList = new ArrayList<>();
        allServiceRV.setHasFixedSize(true);
        allServiceRV.setLayoutManager(new LinearLayoutManager(this));


        allServiceRVAdapter = new AllServicesRVAdapter(usersArrayList,this);
        allServiceRV.setAdapter(allServiceRVAdapter);

        db.collection("allServices").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            loadingPB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                User c = d.toObject(User.class);
                                usersArrayList.add(c);
                            }
                            allServiceRVAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(AllServicesActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllServicesActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}