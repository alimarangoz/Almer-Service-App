package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//This part belongs to Mertcan Onur
public class GetServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseFirestore db;
    String spinnerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_service);

        Spinner spinner = (Spinner) findViewById(R.id.subjects_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.webDesignSubjects, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);


        EditText deadline = findViewById(R.id.deadlineTxt);
        EditText location = findViewById(R.id.locationTxt);
        EditText name = findViewById(R.id.getServiceNameTxt);

        Map<String,Object> allServicesMap = new HashMap<>();

        db = FirebaseFirestore.getInstance();
        Button sendBtn = findViewById(R.id.getServiceSendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerItem = spinner.getSelectedItem().toString();
                allServicesMap.put("name",name.getText().toString());
                allServicesMap.put("day",Integer.parseInt(deadline.getText().toString()));
                allServicesMap.put("location",location.getText().toString());
                if(spinnerItem != null){
                    allServicesMap.put("job",spinnerItem);
                }


                db.collection("allServices").add(allServicesMap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GetServiceActivity.this, "Fail to send data", Toast.LENGTH_SHORT).show();
                    }
                });

                Toast.makeText(GetServiceActivity.this, "Job successfully created.", Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(GetServiceActivity.this, AllServicesActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner) findViewById(R.id.subjects_spinner);
        spin.setOnItemSelectedListener(this);
        Toast.makeText(getApplicationContext(), spin.getSelectedItem().toString()+" ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(GetServiceActivity.this, "Nothing Selected", Toast.LENGTH_LONG).show();
    }
}