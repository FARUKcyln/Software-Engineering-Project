package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.Login;
import com.example.mapapp.connector.LoginResponse;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    static String jwt;
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
                /*Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);*/
                Login l = new Login(String.valueOf(e_mail.getText()), String.valueOf(password.getText()));
                System.out.println(l.getEmail() + " " + l.getPassword());
                try {
                    LoginResponse loginResponse = BackendConnector.login(l);
                    if (loginResponse.isStatus()) {
                        jwt = loginResponse.getJwt();
                        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), loginResponse.getErrorText().toUpperCase(), Toast.LENGTH_LONG).show();
                    }
                } catch ( IOException e) {
                    e.printStackTrace();
                }
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