package com.example.parko.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parko.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    Button signOut;
    TextView userName;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);

        signOut = (Button) findViewById(R.id.signOut);

        signOut.setOnClickListener(this);

        userName = findViewById(R.id.userNameProfile);
        profilePic = findViewById(R.id.ProfilePic);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();
            String phoneNumber = user.getPhoneNumber();
            userName.setText(name + " (" + email + ")");
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

}
