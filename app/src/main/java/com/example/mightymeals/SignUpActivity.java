package com.example.mightymeals;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, First_Name, Last_Name;
    private Button btnSignup;

    private TextView tvAlreadyHaveAccount;
    private ImageView ivLogo, CloseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignUp);
        tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);
        ivLogo = findViewById(R.id.ivLogo);
        First_Name = findViewById(R.id.first_Name);
        Last_Name = findViewById(R.id.last_Name);
        CloseApp = findViewById(R.id.CloseApp);


        CloseApp.setOnClickListener(v->{
            finish();
        });



        //Animation
        ObjectAnimator logo = ObjectAnimator.ofFloat(ivLogo, "translationY", -1000f, 0f);
        logo.setDuration(500);
        logo.start();

        ObjectAnimator firstname = ObjectAnimator.ofFloat(First_Name, "translationY", -1000f, 0f);
        firstname.setDuration(600);
        firstname.start();

        ObjectAnimator lastname = ObjectAnimator.ofFloat(Last_Name, "translationY", -1000f, 0f);
        lastname.setDuration(700);
        lastname.start();

        ObjectAnimator email = ObjectAnimator.ofFloat(etEmail, "translationX", -1000f, 0f);
        email.setDuration(800);
        email.start();

        ObjectAnimator password = ObjectAnimator.ofFloat(etPassword, "translationX", 1000f, 0f);
        password.setDuration(900);
        password.start();

        ObjectAnimator signup = ObjectAnimator.ofFloat(btnSignup, "translationX", -1000f, 0f);
        signup.setDuration(1000);
        signup.start();

        ObjectAnimator AlreadyHaveAnAccount = ObjectAnimator.ofFloat(tvAlreadyHaveAccount, "translationX", 1000f, 0f);
        AlreadyHaveAnAccount.setDuration(1100);
        AlreadyHaveAnAccount.start();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignup();
            }
        });


        tvAlreadyHaveAccount.setOnClickListener(v->{

            Intent Login = new Intent(SignUpActivity.this , LoginActivity.class);
            startActivity(Login);
            finish();
        });
    }

    private void handleSignup() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String FirstName = First_Name.getText().toString().trim();
        String LastName = Last_Name.getText().toString().trim();

        if (TextUtils.isEmpty(FirstName)) {
            etEmail.setError("First Name is required");
            return;
        }

        if (TextUtils.isEmpty(LastName)) {
            etEmail.setError("Last Name is required");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return;
        }

        // Make a POST request to the signup PHP file
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.30/mightymeals/sign_up.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("status");
                            if (status.equals("success")) {
                                Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(login);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("firstname", FirstName);
                params.put("lastname", LastName);
                return params;
            }
        };

        // Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
