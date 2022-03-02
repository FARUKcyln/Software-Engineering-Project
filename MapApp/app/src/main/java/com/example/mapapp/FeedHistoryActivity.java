package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.example.mapapp.connector.BackendConnector;
import com.example.mapapp.connector.Feed;
import com.example.mapapp.connector.GetFeedResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FeedHistoryActivity extends AppCompatActivity {
    ScrollView feedHistory;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_history);
        feedHistory = findViewById(R.id.scrollView2);
        try {
            GetFeedResponse getFeedResponse = BackendConnector.getFeedHistory(LoginActivity.jwt);
            LinearLayout linearLayout = findViewById(R.id.linearLayout);
            for (Feed feed : getFeedResponse.getFeedList()) {
                LinearLayout verticalLinearLayout = new LinearLayout(this);
                verticalLinearLayout.setOrientation(LinearLayout.VERTICAL);
                verticalLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350));
                verticalLinearLayout.setBackgroundColor(Color.rgb(250, 219, 181));


                Space startSpace = new Space(this);
                startSpace.setLayoutParams(new LinearLayout.LayoutParams(40, LinearLayout.LayoutParams.MATCH_PARENT));

                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));

                ImageView weight = new ImageView(this);
                weight.setImageResource(R.drawable.weight_kilogram_icon);
                weight.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

                TextView weightText = new TextView(this);
                weightText.setText(String.valueOf(feed.getAmount()));
                weightText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));
                weightText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                weightText.setTextColor(Color.BLACK);
                weightText.setTypeface(null, Typeface.BOLD);

                linearLayout1.addView(startSpace);
                linearLayout1.addView(weight);
                linearLayout1.addView(weightText);


                LinearLayout linearLayout2 = new LinearLayout(this);
                linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));

                ImageView location = new ImageView(this);
                location.setImageResource(R.drawable.navigation);
                location.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

                TextView locationText = new TextView(this);
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(40.807940, 29.356220, 1);
                String pointLocation = "Point " + feed.getPoint_id() + ". " + addresses.get(0).getAddressLine(0) + ". ";
                locationText.setText(pointLocation);
                locationText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                locationText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                locationText.setTextColor(Color.BLACK);
                locationText.setTypeface(null, Typeface.BOLD);

                Space secondSpace = new Space(this);
                secondSpace.setLayoutParams(new LinearLayout.LayoutParams(40, LinearLayout.LayoutParams.MATCH_PARENT));

                linearLayout2.addView(secondSpace);
                linearLayout2.addView(location);
                linearLayout2.addView(locationText);

                LinearLayout linearLayout3 = new LinearLayout(this);
                linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 80));
                TextView date = new TextView(this);
                String dateText = "Date: " + feed.getDate();
                date.setText(dateText);
                date.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));
                date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                date.setTextColor(Color.BLACK);
                linearLayout3.addView(date);

                Space firstSpace = new Space(this);
                firstSpace.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20));

                verticalLinearLayout.addView(firstSpace);
                verticalLinearLayout.addView(linearLayout1);
                verticalLinearLayout.addView(linearLayout2);
                verticalLinearLayout.addView(linearLayout3);


                Space space = new Space(this);
                space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));


                linearLayout.addView(verticalLinearLayout);
                linearLayout.addView(space);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}