package com.example.mightymeals;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {

    private TextView tvSelectedCityArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvSelectedCityArea = findViewById(R.id.tvSelectedCityArea);

        String city = getIntent().getStringExtra("city");
        String area = getIntent().getStringExtra("area");

        tvSelectedCityArea.setText("City: " + city + "\nArea: " + area);
    }
}