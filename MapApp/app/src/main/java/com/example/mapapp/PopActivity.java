package com.example.mapapp;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

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

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String locationText = addresses.get(0).getAddressLine(0);
            location.setText(locationText);
            String amountText = "5 kg";
            amount.setText(amountText);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}