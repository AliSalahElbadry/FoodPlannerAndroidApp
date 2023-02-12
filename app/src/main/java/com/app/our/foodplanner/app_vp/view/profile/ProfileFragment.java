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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment implements ProfileFragmentInterface{

    TextView textViewEmailProfile;
    ImageView imageViewProfile;
    Button btnLogOut,btnBackup,btnRetriveData;

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
        textViewEmailProfile=view.findViewById(R.id.textViewEmailProfile);
        if(udata!=null)
        {
            textViewEmailProfile.setText(udata[1]);
            showUserData(udata);
        }else{
            textViewEmailProfile.setText(presenterInterface.getuId());
        }
        btnBackup=view.findViewById(R.id.btnBackUpYourData);
        imageViewProfile=view.findViewById(R.id.imageViewProfile);

        btnLogOut=view.findViewById(R.id.btnLogOut);
        btnRetriveData=view.findViewById(R.id.btnretriveYourData);
        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenterInterface.isLogedIn()&&((MainActivityContainerInterface)getActivity()).checkConnectionState())
                {
                    presenterInterface.backupYourData();
                }else{
                    Toast.makeText(getContext(), "please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRetriveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterInterface.retriveData();
            }
        });
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
    }




    @Override
    public void onClickLogOut() {
        showDialog("Are you sure you want to log out?");
    }

    @Override
    public void setBackUpRes(boolean res) {
        if(res)
        {
            Toast.makeText(getContext(), "Backup Done", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Backup Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog(String msg)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setMessage(msg);
        alert.setTitle("Log out");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .build();
                GoogleSignIn.getClient(getContext(), gso).signOut();
                presenterInterface.logout();
                ((MainActivityContainerInterface)getActivity()).ReStart();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });

        alert.show();
    }

    public void signOut(GoogleSignInClient mGoogleSignInClient) {

        if(mGoogleSignInClient!=null){
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(getContext(), "Sign out suss", Toast.LENGTH_SHORT).show();
                            try {
                                finalize();
                            } catch (Throwable e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        }
        else{
            Log.i(TAG, "fail logout ");}

    }
}