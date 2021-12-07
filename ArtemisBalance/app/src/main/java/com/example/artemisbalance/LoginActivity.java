package com.example.artemisbalance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView forgotPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotPassword = (TextView) findViewById(R.id.textView);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                                                  startActivity(intent);
                                              }
                                          }
        );

        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new Intent(LoginActivity.this, MapsActivity2.class);
                                         startActivity(intent);

                                     }
                                 }
        );
    }
}