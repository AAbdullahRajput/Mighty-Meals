package com.example.mightymeals;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.PublicKey;

public class LocationActivity extends AppCompatActivity {

    private Spinner spinnerCity, spinnerArea;
    private Button btnConfirm, btnLocateOnMap;

    private ImageView ivLogo;

    private TextView textView3;

    private String selectedCity = "";
    private String selectedArea = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerArea = findViewById(R.id.spinnerArea);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnLocateOnMap = findViewById(R.id.btnLocateOnMap);
        ivLogo = findViewById(R.id.ivLogo);
        textView3 =  findViewById(R.id.textView3);



        ObjectAnimator Logo = ObjectAnimator.ofFloat(ivLogo, "translationY", -1000f, 0f);
        Logo.setDuration(800);
        Logo.start();

        ObjectAnimator CityAndAreaText = ObjectAnimator.ofFloat(textView3, "translationY", -1000f, 0f);
        CityAndAreaText.setDuration(900);
        CityAndAreaText.start();

        ObjectAnimator cityspinner = ObjectAnimator.ofFloat(spinnerCity, "translationY", -1000f, 0f);
        cityspinner.setDuration(1000);
        cityspinner.start();

        ObjectAnimator areaspinner = ObjectAnimator.ofFloat(spinnerArea, "translationY", -1000f, 0f);
        areaspinner.setDuration(1100);
        areaspinner.start();

        ObjectAnimator confirm = ObjectAnimator.ofFloat(btnConfirm, "translationY", -1000f, 0f);
        confirm.setDuration(1200);
        confirm.start();

        ObjectAnimator locaton = ObjectAnimator.ofFloat(btnLocateOnMap, "translationY", -1000f, 0f);
        locaton.setDuration(1300);
        locaton.start();

        // Populate the city spinner
        String[] cities = {"Select a city", "New York", "Los Angeles", "Chicago"};
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        // Populate the area spinner dynamically based on the city
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = cities[position];

                String[] areas;
                switch (position) {
                    case 1:
                        areas = new String[]{"Select an area", "Manhattan", "Brooklyn", "Queens"};
                        break;
                    case 2:
                        areas = new String[]{"Select an area", "Hollywood", "Santa Monica", "Venice"};
                        break;
                    case 3:
                        areas = new String[]{"Select an area", "Downtown", "Hyde Park", "Lincoln Park"};
                        break;
                    default:
                        areas = new String[]{"Select an area"};
                        break;
                }

                ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(LocationActivity.this, android.R.layout.simple_spinner_item, areas);
                areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerArea.setAdapter(areaAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedArea = spinnerArea.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Confirm button click listener
        btnConfirm.setOnClickListener(v -> {
            if (selectedCity.equals("Select a city") || selectedArea.equals("Select an area")) {
                Toast.makeText(LocationActivity.this, "Please select both city and area", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(LocationActivity.this, MenuActivity.class);
                intent.putExtra("city", selectedCity);
                intent.putExtra("area", selectedArea);
                startActivity(intent);
            }
        });

        // Locate on Map button click listener
        btnLocateOnMap.setOnClickListener(v -> {
            if (selectedCity.equals("Select a city") || selectedArea.equals("Select an area")) {
                Toast.makeText(LocationActivity.this, "Please select both city and area", Toast.LENGTH_SHORT).show();
            } else {
                String location = selectedArea + ", " + selectedCity;
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}