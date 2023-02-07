package com.app.our.foodplanner.app_vp.view.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;

public class LogInFragment extends Fragment {



    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //((MainActivityContainer)getActivity()).showSignUp();
        //
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_layout, container, false);

    }

}