package com.example.mightymeals;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mightymeals.Fragment.ProfileFragment;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnSignUp;
    private TextView welcometomightymeals;
    private ImageView CloseApp;

    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        welcometomightymeals = findViewById(R.id.welcometomightymeals);
        CloseApp = findViewById(R.id.CloseApp);

        CloseApp.setOnClickListener(v -> {
            finish();
        });


        //Animation
        ObjectAnimator Welcomeanimator = ObjectAnimator.ofFloat(welcometomightymeals, "translationX", -1000f, 0f);
        Welcomeanimator.setDuration(400);
        Welcomeanimator.start();

        ObjectAnimator email = ObjectAnimator.ofFloat(etEmail, "translationX", 1000f, 0f);
        email.setDuration(500);
        email.start();

        ObjectAnimator password = ObjectAnimator.ofFloat(etPassword, "translationX", -1000f, 0f);
        password.setDuration(600);
        password.start();

        ObjectAnimator login = ObjectAnimator.ofFloat(btnLogin, "translationX", 1000f, 0f);
        login.setDuration(700);
        login.start();

        ObjectAnimator forgot = ObjectAnimator.ofFloat(tvForgotPassword, "translationX", -1000f, 0f);
        forgot.setDuration(800);
        forgot.start();

        ObjectAnimator signup = ObjectAnimator.ofFloat(btnSignUp, "translationX", 1000f, 0f);
        signup.setDuration(900);
        signup.start();



        btnSignUp.setOnClickListener(v -> {
            Intent next = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(next);
            finish();
        });

        tvForgotPassword.setOnClickListener(v->{
            Toast.makeText(this, "In Progress",Toast.LENGTH_SHORT).show();
        });

        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return;
        }

        // Make a POST request to the login PHP file
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.30/mightymeals/login_up.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("status");
                            String message = jsonResponse.getString("message");

                            if (status.equals("success")) {
                                // Assuming first_name, last_name, and email are included in the response
                                String firstName = jsonResponse.getString("first_name");
                                String lastName = jsonResponse.getString("last_name");
                                String email = jsonResponse.getString("email");

                                // Store data in SharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("first_name", firstName);
                                editor.putString("last_name", lastName);
                                editor.putString("email", email);
                                editor.apply();

                                // Pass data to ProfileFragment using Intent
                                Intent intent = new Intent(LoginActivity.this, LocationActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Volley Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        // Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }




}
