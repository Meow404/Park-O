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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parko.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    Button signOut;
    TextView userName,userEmail,nric,phoneNumber;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);

        signOut = (Button) findViewById(R.id.signOut);

        signOut.setOnClickListener(this);

        userName = findViewById(R.id.userNameProfile);
        profilePic = findViewById(R.id.ProfilePic);
        userEmail = findViewById(R.id.profileEmailId);
        phoneNumber = findViewById(R.id.phoneNumber);
        nric = findViewById(R.id.profileNRIC);

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
            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }

    private void readData(final String email){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> objectMap= document.getData();
                                if(String.valueOf(objectMap.get("email")).equals(email)){
                                    userName.setText((String)objectMap.get("username"));
                                    phoneNumber.setText((String)objectMap.get("contact"));
                                     nric.setText((String)objectMap.get("nric"));
                                     return;
                                }
                            }
                        } else {
                            Log.w("1002", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
