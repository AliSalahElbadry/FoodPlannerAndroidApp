package com.app.our.foodplanner.app_vp.view.profile;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;

public class ProfileFragment extends Fragment implements ProfileFragmentInterface{

    TextView textViewNameProfile,textViewPassProfile,textViewEmailProfile;
    ImageView imageViewProfile;
    Button btnLogOut,btnGoToHome;

    PresenterInterface presenterInterface;

    public ProfileFragment() {
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
        return inflater.inflate(R.layout.profile_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();
        String []udata= ((MainActivityContainer)getActivity()).getPresenter().getUserData();
        Log.i(TAG, "Profile: "+udata[0]);
        textViewEmailProfile=view.findViewById(R.id.textViewEmailProfile);
        textViewNameProfile=view.findViewById(R.id.textViewNameProfile);
       // textViewPassProfile=view.findViewById(R.id.textViewShowPassProfile);
        imageViewProfile=view.findViewById(R.id.imageViewProfile);
        btnLogOut=view.findViewById(R.id.btnLogOut);


        showUserData(udata);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogOut();
            }
        });

    }

    @Override
    public void showUserData(String[] data) {
        textViewEmailProfile.setText(data[1]);
        textViewNameProfile.setText(data[0]);
        Log.i(TAG, "showUserData: 0  "+data[0]+" 1  "+data[1]+"  2  " +data[2]);
       // textViewPassProfile.setText(data[2]);
    }

//    @Override
//    public void onClickHome() {
//        ((MainActivityContainer)getActivity()).navigationView.setSelectedItemId(R.id.homeMenu);
//    }

    @Override
    public void onClickLogOut() {
        showDialog("you can sure logout");
//        presenterInterface.logout();
//        ((MainActivityContainer)getActivity()).navigationView.setSelectedItemId(R.id.homeMenu);
    }

    public void showDialog(String msg)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setMessage(msg);
        alert.setTitle("Logout");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenterInterface.logout();
                ((MainActivityContainer)getActivity()).navigationView.setSelectedItemId(R.id.homeMenu);
                Log.i(TAG, "sure logout ok: ");
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivityContainer)getActivity()).showProfilePage();
                Log.i(TAG, "sure logout cancel: ");
            }
        });

        alert.show();
    }
}