package com.example.parko.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parko.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UserEdit extends AppCompatActivity implements View.OnClickListener {

    Button save;
    TextView userName, userEmail, nric, phoneNumber;
    ImageView profilePic;
    FirebaseFirestore db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        save = (Button) findViewById(R.id.SaveEdit);

        save.setOnClickListener(this);

        userName = findViewById(R.id.userNameEdit);
        profilePic = findViewById(R.id.ProfilePic);
        userEmail = findViewById(R.id.editEmailId);
        phoneNumber = findViewById(R.id.phoneNumberEdit);
        nric = findViewById(R.id.EditNRIC);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            readData();

            userEmail.setText(email);
            profilePic.setImageURI(photoUrl);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SaveEdit:
                updateData();
                startActivity(new Intent(this, UserActivity.class));
                break;
        }

    }

    private void readData() {


        DocumentReference docRef = db.collection("users").document(email.toLowerCase());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("10001", "DocumentSnapshot data: " + document.getData());
                        Map<String, Object> data = document.getData();
                        userName.setHint((String) data.get("username"));
                        nric.setHint((String) data.get("nric"));
                        phoneNumber.setHint((String) data.get("contact"));
                    } else {
                        Log.d("10002", "No such document");
                    }
                } else {
                    Log.d("10003", "get failed with ", task.getException());
                }
            }
        });
    }

    private void updateData() {
        Map<String, Object> data = new HashMap<>();
        if (!userName.getText().toString().trim().isEmpty())
            data.put("username", userName.getText().toString().trim());
        if (!nric.getText().toString().trim().isEmpty())
            data.put("nric", nric.getText().toString().trim());
        if (phoneNumber.getText().toString().trim().length() >= 6)
            data.put("contact", phoneNumber.getText().toString().trim());

        db.collection("users").document(email.toLowerCase())
                .set(data, SetOptions.merge());
    }

}
