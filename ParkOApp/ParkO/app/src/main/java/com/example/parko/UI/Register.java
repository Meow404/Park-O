package com.example.parko.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parko.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText username, password, confirmPassword, Email;
    //private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);

        Email = (EditText) findViewById(R.id.userID);
        password = (EditText) findViewById(R.id.phoneNumber);
        confirmPassword = (EditText) findViewById(R.id.register_confirmpassowrd);
        username = (EditText) findViewById(R.id.userNameProfile);
        bRegister = (Button) findViewById(R.id.bRegister);

        mAuth = FirebaseAuth.getInstance();


        bRegister.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //startActivity(new Intent(this, MapsActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                if (registerUser())
                    startActivity(new Intent(this, MapsActivity.class));
                break;
        }

    }

    private boolean registerUser() {
        final String name = username.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = confirmPassword.getText().toString().trim();

        if (name.isEmpty()) {
            username.setError(getString(R.string.input_error_name));
            username.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            Email.setError(getString(R.string.input_error_email));
            Email.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError(getString(R.string.input_error_email_invalid));
            Email.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            Email.setError(getString(R.string.input_error_password));
            Email.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            Email.setError(getString(R.string.input_error_password_length));
            Email.requestFocus();
            return false;
        }

        if (password.equals(confirmPassword)) {
            Email.setError("Password Mismatch");
            Email.requestFocus();
            return false;
        }

        // progressBar.setVisibility(View.VISIBLE);
        System.out.print("Last");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");

                    myRef.setValue("Hello, World!");

                } else {
                    Toast.makeText(Register.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return true;
    }


}
