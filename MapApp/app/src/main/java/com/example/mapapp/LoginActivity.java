package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapapp.connector.Connector;
import com.example.mapapp.connector.Login;
import com.example.mapapp.connector.LoginResponse;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    Button login;
    TextView e_mail;
    TextView password;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e_mail = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
                /*Connector c = new Connector();
                Login l = new Login(String.valueOf(e_mail.getText()), String.valueOf(password.getText()));
                System.out.println(l.getEmail() + " " + l.getPassword());
                try {
                    LoginResponse loginResponse = c.login(l);
                    if (loginResponse.getResponseCode() == 200) {
                        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), loginResponse.getJwt().toUpperCase(), Toast.LENGTH_LONG).show();

                        System.out.println(loginResponse.getJwt());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        forgotPassword = findViewById(R.id.textView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}