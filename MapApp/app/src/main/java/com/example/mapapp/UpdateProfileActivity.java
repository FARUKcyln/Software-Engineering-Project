package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.UpdateUser;
import com.example.mapapp.connector.UpdateUserResponse;

import java.io.IOException;

public class UpdateProfileActivity extends AppCompatActivity {

    ImageView update;
    ImageView cancel;
    EditText name;
    EditText surname;
    EditText phoneNumber;
    EditText eMail;
    EditText password;
    EditText confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name = findViewById(R.id.editTextTextPersonName3);
        surname = findViewById(R.id.editTextTextPersonSurname);
        phoneNumber = findViewById(R.id.editTextPhone);
        eMail = findViewById(R.id.editTextTextEmailAddress4);
        password = findViewById(R.id.editTextTextPassword4);
        confirmPassword = findViewById(R.id.editTextTextPassword5);

        cancel = findViewById(R.id.imageView4);

        update = findViewById(R.id.imageView12);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateUser updateUser = new UpdateUser(String.valueOf(name.getText()), String.valueOf(surname.getText())
                        , String.valueOf(eMail.getText()), String.valueOf(phoneNumber.getText()), String.valueOf(password.getText()));
                try {
                    UpdateUserResponse updateUserResponse = BackendConnector.updateUser(LoginActivity.jwt, updateUser);
                    if (updateUserResponse.getStatus()) {
                        Toast.makeText(getApplicationContext(), "***SUCCESSFULLY UPDATED***", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UpdateProfileActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), updateUserResponse.getResultText(), Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}