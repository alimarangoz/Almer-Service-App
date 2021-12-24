package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView profileImgView = findViewById(R.id.profileActivityImgView);
        profileImgView.setImageResource(R.mipmap.ic_profile_foreground);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText name = (EditText) findViewById(R.id.editTextTextPersonNameId);
        EditText email = findViewById(R.id.editTextTextEmailAddressId);
        EditText location = findViewById(R.id.editTextLocationId);
        EditText job = findViewById(R.id.editTextJobId);
        EditText description = findViewById(R.id.editTextTextMultiLineDescId);

        Map<String,Object> user = new HashMap();

        Button profileSaveBtn = findViewById(R.id.saveChangesBtn);

        profileSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.put("name",name.getText().toString());
                user.put("email",email.getText().toString());
                user.put("location",location.getText().toString());
                user.put("job",job.getText().toString());
                user.put("description",description.getText().toString());

                    db.collection("users").add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

                Toast.makeText(ProfileActivity.this, "Changes recorded.", Toast.LENGTH_SHORT).show();

            }
        });


    }
}