package com.example.mightymeals.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mightymeals.LoginActivity;
import com.example.mightymeals.R;

import java.security.PrivateKey;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private LinearLayout ProfileFragmentt;

    private ImageView ivLogo;
    private Button btnLogout;

    private TextView mightymeals, YourProfile, EmailTextView, etEmaill, FirstNameTextView, F_Name, LastNameTextView, L_Name;

    private View DetailBar;

    // Declare UI elements to display user info
    private TextView tvFirstName, tvLastName, tvEmail;

    public ProfileFragment() {

        // Required empty public constructor
    }





    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        //ids For ANimation -------------------------------------------------

        ProfileFragmentt = view.findViewById(R.id.ProfileFragmenttt);
        ivLogo = view.findViewById(R.id.ivLogo);
        btnLogout = view.findViewById(R.id.btnLogout);
        mightymeals = view.findViewById(R.id.mightymeals);
        YourProfile = view.findViewById(R.id.YourProfile);
        DetailBar = view.findViewById(R.id.DetailBar);
        EmailTextView = view.findViewById(R.id.EmailTextView);
        etEmaill = view.findViewById(R.id.etEmaill);
        FirstNameTextView = view.findViewById(R.id.FirstNameTextView);
        F_Name = view.findViewById(R.id.F_Name);
        LastNameTextView = view.findViewById(R.id.LastNameTextView);
        L_Name = view.findViewById(R.id.L_Name);


        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                // Your intent code here
                if (getActivity() != null) {
                    Intent LoginPage = new Intent(getActivity(), LoginActivity.class);
                    startActivity(LoginPage);
                    getActivity().finish();
                }
            });
        } else {
            Log.e("ProfileFragment", "Button not found!");
        }



        ObjectAnimator ProfileFragmenttt = ObjectAnimator.ofFloat(ProfileFragmentt, "translationY", -1000f, 0f);
        ProfileFragmenttt.setDuration(800);
        ProfileFragmenttt.start();

        ObjectAnimator Logo = ObjectAnimator.ofFloat(ivLogo, "translationY", 1000f, 0f);
        Logo.setDuration(900);
        Logo.start();

        ObjectAnimator mightmeall = ObjectAnimator.ofFloat(mightymeals, "translationX", 1000f, 0f);
        mightmeall.setDuration(950);
        mightmeall.start();

        ObjectAnimator YourProfilee = ObjectAnimator.ofFloat(YourProfile, "translationY", -1000f, 0f);
        YourProfilee.setDuration(980);
        YourProfilee.start();

        ObjectAnimator DetailBarr = ObjectAnimator.ofFloat(DetailBar, "translationX", -1000f, 0f);
        DetailBarr.setDuration(1000);
        DetailBarr.start();

        ObjectAnimator EmailTextVieww = ObjectAnimator.ofFloat(EmailTextView, "translationX", -1000f, 0f);
        EmailTextVieww.setDuration(1030);
        EmailTextVieww.start();

        ObjectAnimator etEmailll = ObjectAnimator.ofFloat(etEmaill, "translationX", 1000f, 0f);
        etEmailll.setDuration(1000);
        etEmailll.start();

        ObjectAnimator FirstNameTextVieww = ObjectAnimator.ofFloat(FirstNameTextView, "translationX", -1000f, 0f);
        FirstNameTextVieww.setDuration(1030);
        FirstNameTextVieww.start();

        ObjectAnimator F_Namee = ObjectAnimator.ofFloat(F_Name, "translationX", 1000f, 0f);
        F_Namee.setDuration(1000);
        F_Namee.start();

        ObjectAnimator LastNameTextVieww = ObjectAnimator.ofFloat(LastNameTextView, "translationX", -1000f, 0f);
        LastNameTextVieww.setDuration(1030);
        LastNameTextVieww.start();

        ObjectAnimator L_Namee = ObjectAnimator.ofFloat(L_Name, "translationX", 1000f, 0f);
        L_Namee.setDuration(1000);
        L_Namee.start();
        //---------------------------------------------------------




        // Initialize TextViews
        tvFirstName = view.findViewById(R.id.F_Name);
        tvLastName = view.findViewById(R.id.L_Name);
        tvEmail = view.findViewById(R.id.etEmaill);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Profile", getActivity().MODE_PRIVATE);
        String firstName = sharedPreferences.getString("first_name", "N/A");
        String lastName = sharedPreferences.getString("last_name", "N/A");
        String email = sharedPreferences.getString("email", "N/A");

        // Set the values to the TextViews
        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvEmail.setText(email);

        return view;
    }
}
