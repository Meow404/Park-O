package com.example.parko;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button blogin;
    EditText etPassword, etUsername;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        blogin = (Button) findViewById(R.id.blogin);

        register = (Button) findViewById(R.id.Bforgot);
        register.setOnClickListener(this);
        blogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blogin:
                startActivity(new Intent(Login.this, MapsActivity.class));
                break;
            case R.id.Bforgot:
                startActivity(new Intent(Login.this, Register.class));

                break;
        }

    }
}
