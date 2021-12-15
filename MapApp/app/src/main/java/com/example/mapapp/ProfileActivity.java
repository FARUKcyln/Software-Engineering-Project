package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.ProfileResponse;

import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {
    ImageView edit;
    ImageView profilePicture;
    TextView nameAndSurname;
    TextView eMail;
    TextView phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePicture = findViewById(R.id.imageView8);
        nameAndSurname = findViewById(R.id.textView6);
        eMail = findViewById(R.id.textView7);
        phoneNumber = findViewById(R.id.textView8);

        edit = findViewById(R.id.imageView6);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        nameAndSurname.setText(extras.getString("nameAndSurname"));
        eMail.setText(extras.getString("eMail"));
        phoneNumber.setText(extras.getString("phoneNumber"));


    }
}