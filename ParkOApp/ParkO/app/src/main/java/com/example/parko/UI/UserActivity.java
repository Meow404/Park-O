package com.example.parko.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.util.Map;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    Button signOut;
    ImageButton edit;
    TextView userName,userEmail,nric,phoneNumber;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);

        signOut = (Button) findViewById(R.id.SignOut);
        edit = (ImageButton) findViewById(R.id.editButton);

        signOut.setOnClickListener(this);
        edit.setOnClickListener(this);

        userName = findViewById(R.id.userNameEdit);
        profilePic = findViewById(R.id.ProfilePic);
        userEmail = findViewById(R.id.editEmailId);
        phoneNumber = findViewById(R.id.phoneNumberEdit);
        nric = findViewById(R.id.EditNRIC);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            readData(email);

            userEmail.setText(email);
            profilePic.setImageURI(photoUrl);
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        startActivity(new Intent(UserActivity.this, UserActivity.class));
                        return true;
                    case R.id.carParkList:
                        startActivity(new Intent(UserActivity.this, ListActivity.class));
                        return true;
                    case R.id.Map:
                        startActivity(new Intent(UserActivity.this, MapsActivity.class));
                        return true;
                    default:
                        return true;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.editButton:
                startActivity(new Intent(this, UserEdit.class));
                break;
        }

    }

    private void readData(final String email){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);


        DocumentReference docRef = db.collection("users").document(email.toLowerCase());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("10001", "DocumentSnapshot data: " + document.getData());
                        Map<String,Object> data = document.getData();
                        userName.setText((String)data.get("username"));
                        nric.setText((String)data.get("nric"));
                        phoneNumber.setText((String)data.get("contact"));
                    } else {
                        Log.d("10002", "No such document");
                    }
                } else {
                    Log.d("10003", "get failed with ", task.getException());
                }
            }
        });
    }

}
