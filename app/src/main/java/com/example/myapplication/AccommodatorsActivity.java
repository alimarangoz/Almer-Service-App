package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AccommodatorsActivity extends AppCompatActivity {

    private RecyclerView userRV;
    private ArrayList<User> usersArrayList;
    private AcommodatorRVAdapter acommodatorRVAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodators);

        userRV = findViewById(R.id.idRVAcommodator);
        loadingPB = findViewById(R.id.idProgressBar);

        db = FirebaseFirestore.getInstance();

        usersArrayList = new ArrayList<>();
        userRV.setHasFixedSize(true);
        userRV.setLayoutManager(new LinearLayoutManager(this));

        acommodatorRVAdapter = new AcommodatorRVAdapter(usersArrayList,this);
        userRV.setAdapter(acommodatorRVAdapter);

        db.collection("users").get()
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
                            acommodatorRVAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(AccommodatorsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AccommodatorsActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });


    }


}