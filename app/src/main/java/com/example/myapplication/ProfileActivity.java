package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//This parts UI belongs Mertcan Onur, functionality belongs Ali Marangoz.
public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser fBaseUser;
    private DatabaseReference reference;
    private String userID;
    ProgressBar loadingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView profileImgView = findViewById(R.id.profileActivityImgView);
        profileImgView.setImageResource(R.mipmap.ic_profile_foreground);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        loadingProgressBar = findViewById(R.id.loading);
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
                                Toast.makeText(ProfileActivity.this, "Changes recorded.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileActivity.this,AccommodatorsActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });



            }
        });

        loadingProgressBar.setVisibility(View.VISIBLE);
        fBaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = fBaseUser.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String fName = userProfile.getName();
                    String pEmail = userProfile.getEmail();

                    name.setText(fName);
                    email.setText(pEmail);
                    loadingProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Error detected related with Database!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}