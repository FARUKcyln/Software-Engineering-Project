package com.example.mapapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.ContactResponse;
import com.example.mapapp.connector.PostContact;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ContactActivity extends AppCompatActivity {
    ImageView contact;
    Button addAddress;
    TextView messageText;
    TextView addressText;
    TextView nameText;
    TextView emailText;
    Button removeAddress;

    int PLACE_PICKER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        messageText = findViewById(R.id.editTextTextMultiLine);
        addressText = findViewById(R.id.textView16);
        nameText = findViewById(R.id.editTextTextPersonName4);
        emailText = findViewById(R.id.editTextTextEmailAddress5);
        removeAddress = findViewById(R.id.button9);

        removeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressText.setText("");
            }
        });

        contact = findViewById(R.id.imageView14);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = null;
                if (addressText.getText() != null){
                    message = messageText.getText() + " Address : "+ addressText.getText();
                }else{
                    message = (String) messageText.getText();
                }
                if (message != null && emailText.getText().length() > 0 && nameText.getText().length() > 0){
                    PostContact postContact = new PostContact(String.valueOf(nameText.getText()), String.valueOf(emailText.getText()), message);
                    try {
                        ContactResponse contactResponse = BackendConnector.postContact(LoginActivity.jwt, postContact);
                        if (contactResponse.getStatus()){
                            Toast.makeText(getApplicationContext(), contactResponse.getText().toUpperCase(Locale.ROOT), Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), contactResponse.getText().toUpperCase(Locale.ROOT), Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        addAddress = findViewById(R.id.button10);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(ContactActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(data, this);
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addresses != null;
                String locationText = addresses.get(0).getAddressLine(0);
                if (locationText != null){
                    addressText.setText(locationText);
                }
            }
        }
    }
}