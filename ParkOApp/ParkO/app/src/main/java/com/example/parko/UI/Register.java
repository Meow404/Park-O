package com.example.parko.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parko.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText username, password, confirmPassword, Email, phoneNumber, NRIC;
    //private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);

        Email = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.password1);
        confirmPassword = (EditText) findViewById(R.id.password2);
        username = (EditText) findViewById(R.id.regUserName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEdit);
        NRIC = (EditText) findViewById(R.id.nric);


        bRegister = (Button) findViewById(R.id.bRegister);

        mAuth = FirebaseAuth.getInstance();


        bRegister.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        final String name = username.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String userPassword = password.getText().toString().trim();
        final String conPassword = confirmPassword.getText().toString().trim();
        final String contact = phoneNumber.getText().toString().trim();
        final String userNRIC = NRIC.getText().toString().trim();

        if (name.isEmpty()) {
            username.setError(getString(R.string.input_error_name));
            username.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            Email.setError(getString(R.string.input_error_email));
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError(getString(R.string.input_error_email_invalid));
            Email.requestFocus();
            return;
        }

        if (userPassword.isEmpty()) {
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return;
        }

        if (userPassword.length() < 6) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return;
        }

        if (!userPassword.equals(conPassword)) {
            password.setError("Password Mismatch");
            password.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    addData(email,name,  contact, userNRIC);;
                    startActivity(new Intent(Register.this, MapsActivity.class));

                } else {
                    Toast.makeText(Register.this, "User Already Exists.",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Register.class));
                }
            }
        });

    }

    private void addData(String email, String username, String contact, String nric) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("username", username);
        userMap.put("contact", contact);
        userMap.put("nric", nric);

// Add a new document with a generated ID
        db.collection("users").document(email.toLowerCase())
                .set(userMap)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("1001", "DocumentSnapshot added with ID: ");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("1002", "Error adding document", e);
                    }
                });
    }


}
