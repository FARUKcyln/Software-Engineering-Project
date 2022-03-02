package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.GetFeedResponse;
import com.example.mapapp.connector.ProfileResponse;

import java.io.IOException;
import java.io.Serializable;

public class MenuActivity extends AppCompatActivity {

    Button profile;
    Button feedHistory;
    Button contactUs;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        profile = findViewById(R.id.button6);
        feedHistory = findViewById(R.id.button7);
        contactUs = findViewById(R.id.button8);
        back = findViewById(R.id.imageView13);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ProfileResponse profileResponse = BackendConnector.getUser(LoginActivity.jwt);
                    if (profileResponse.isStatus()){
                        Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                        String nameAndSurnameText = profileResponse.getProfile().getName() + " " + profileResponse.getProfile().getSurname();
                        intent.putExtra("nameAndSurname", nameAndSurnameText);
                        intent.putExtra("eMail", profileResponse.getProfile().getEmail());
                        intent.putExtra("phoneNumber", profileResponse.getProfile().getPhone());
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        feedHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    GetFeedResponse getFeedResponse = BackendConnector.getFeedHistory(LoginActivity.jwt);
                    if (getFeedResponse.isStatus()){
                        Intent intent = new Intent(MenuActivity.this, FeedHistoryActivity.class);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}