package com.example.mightymeals;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MenuActivity extends AppCompatActivity {

    public boolean backpress = false;

    private TextView textView, Count;
    private ImageView imageView3;

    private View BelowBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Correctly initialize NavController
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        // Correctly initialize BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNav, navController);


        textView = findViewById(R.id.textView);
        BelowBar = findViewById(R.id.BelowBar);
//        imageView3 = findViewById(R.id.imageView3);
//        Count = findViewById(R.id.Count);

//        ObjectAnimator Countt = ObjectAnimator.ofFloat(Count, "translationX", -1000f, 0f);
//        Countt.setDuration(1000);
//        Countt.start();

        ObjectAnimator TextVieww = ObjectAnimator.ofFloat(textView, "translationX", -1000f, 0f);
        TextVieww.setDuration(1000);
        TextVieww.start();

        ObjectAnimator BelowBarr = ObjectAnimator.ofFloat(BelowBar, "translationX", 1000f, 0f);
        BelowBarr.setDuration(1000);
        BelowBarr.start();

//        ObjectAnimator ImageView33 = ObjectAnimator.ofFloat(imageView3, "translationY", -1000f, 0f);
//        ImageView33.setDuration(1000);
//        ImageView33.start();

    }
    @Override
    public void onBackPressed() {


        if (backpress)
        {
            Intent back = new Intent(this,LocationActivity.class);
            startActivity(back);
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        }
        else {
            backpress = true;
            super.onBackPressed();
        }
    }
}
