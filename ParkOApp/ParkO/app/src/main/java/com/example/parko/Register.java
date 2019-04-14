package com.example.parko;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText username,password, confirmPassword,carNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=(EditText)findViewById(R.id.register_username);
        password=(EditText)findViewById(R.id.register_password);
        confirmPassword=(EditText)findViewById(R.id.register_confirmpassowrd);
        carNumber=(EditText)findViewById(R.id.car_number);
        bRegister=(Button) findViewById(R.id.bRegister);



        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsActivity();
            }
        });

    }

    public void openMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                startActivity(new Intent(this, MapsActivity.class));
                break;
        }

    }
}
