package com.example.mapapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.Point;
import com.example.mapapp.connector.PostFeed;
import com.example.mapapp.connector.PostFeedResponse;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PopActivity extends Activity {
    TextView location;
    TextView amount;
    Button addFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        addFood = findViewById(R.id.button14);
        location = findViewById(R.id.location);
        amount = findViewById(R.id.amount);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width* .9), (int) (height * .3));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 70;

        getWindow().setAttributes(params);

        Bundle extras = getIntent().getExtras();
        LatLng latLng = (LatLng) extras.get("LatLng");
        double storage = 0;
        for (Point p: LoginActivity.pointList) {
            if(p.getLatitude() == latLng.latitude && p.getLongitude() == latLng.longitude){
                storage = p.getStorage();
            }
        }


        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String locationText = addresses.get(0).getAddressLine(0);
            location.setText(locationText);
            String amountText = String.valueOf(storage);
            amount.setText(amountText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostFeed postFeed = new PostFeed(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude), "2");
                try {
                    PostFeedResponse postFeedResponse = BackendConnector.postFeed(LoginActivity.jwt, postFeed);
                    if (postFeedResponse.getStatus()) {
                        Toast.makeText(getApplicationContext(), "SUCCESSFULLY Food Added", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), postFeedResponse.getErrorText(), Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}