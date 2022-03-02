package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.Register;
import com.example.mapapp.connector.RegisterResponse;

import java.io.IOException;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText name;
    EditText surname;
    EditText phone;
    EditText eMail;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.button3);

        name = findViewById(R.id.editTextTextPersonName);
        surname = findViewById(R.id.editTextTextPersonName2);
        phone = findViewById(R.id.editTextTextPhone);
        eMail = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        confirmPassword = findViewById(R.id.editTextTextPassword3);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(password.getText()).equals(String.valueOf(confirmPassword.getText()))) {
                    Register register = new Register(String.valueOf(name.getText()), String.valueOf(surname.getText())
                            , String.valueOf(phone.getText()), String.valueOf(eMail.getText()), String.valueOf(password.getText()));
                    try {
                        RegisterResponse registerResponse = BackendConnector.register(register);
                        if (registerResponse.isStatus()){
                            Toast.makeText(getApplicationContext(), "SUCCESSFULLY REGISTERED", Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), registerResponse.getErrorText().toUpperCase(Locale.ROOT), Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "PASSWORDS DO NOT MATCH !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}