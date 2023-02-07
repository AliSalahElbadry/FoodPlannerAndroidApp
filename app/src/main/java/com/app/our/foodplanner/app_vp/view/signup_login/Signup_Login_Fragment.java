package com.app.our.foodplanner.app_vp.view.signup_login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;

public class Signup_Login_Fragment extends Fragment {


Button buttonLogin,buttonSignUp;

    public Signup_Login_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup_login_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonLogin=view.findViewById(R.id.btnLoginMovePage);
        buttonSignUp=view.findViewById(R.id.btnSignUpMovePage);
        buttonLogin.setOnClickListener(l->{
            ((MainActivityContainer)getActivity()).showLogIn();
        });
        buttonSignUp.setOnClickListener(l->{
            ((MainActivityContainer)getActivity()).showSignUp();
        });
    }
}