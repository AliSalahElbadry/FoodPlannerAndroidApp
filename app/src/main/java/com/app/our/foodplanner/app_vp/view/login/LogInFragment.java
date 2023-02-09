package com.app.our.foodplanner.app_vp.view.login;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.Presenter;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LogInFragment extends Fragment implements LogInFragmentInterface {

    EditText editTxtUNameLogIn,editTxtPassLogIn;
    Button btnLogIn;
    ImageView imageViewGoogleLog;
    TextView textViewHaveAccountLogIn;

   // LogInFragmentInterface LogInFragmentInterface;
    PresenterInterface presenterInterface;
    Context context;

    public LogInFragment() {
        //this.presenterInterface=presenterInterface;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_layout, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();

        editTxtUNameLogIn=view.findViewById(R.id.editTxtUNameLogInPage);
        editTxtPassLogIn=view.findViewById(R.id.editTxtPassLogInPage);
        btnLogIn=view.findViewById(R.id.btnLogInPage);
        imageViewGoogleLog=view.findViewById(R.id.imageViewGoogleLogPage);
        textViewHaveAccountLogIn=view.findViewById(R.id.textViewHaveAccountLogInPage);
       btnLogIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onClickLogin();
           }
       });

//       imageViewGoogleLog.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions
//                       .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                       .requestIdToken(getString(R.string.default_web_client_id))
//                       .requestEmail().build();
//               GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
//
//           }
//       });


    }
    public  void onClickLogin(){
        if(editTxtUNameLogIn.getText().toString().isEmpty()&&editTxtPassLogIn.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Please, enter your email and password", Toast.LENGTH_SHORT).show();
        }
        else if(editTxtUNameLogIn.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Please, enter your email ", Toast.LENGTH_SHORT).show();
        }
        else if(editTxtPassLogIn.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Please, enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            presenterInterface.doLogin(editTxtUNameLogIn.getText().toString(),editTxtPassLogIn.getText().toString());

        }
    }

    @Override
    public void onLoginResult(Boolean result) {
        if(result){
            Log.i(TAG, "ssssssssssssonLoginResult: "+result);
            ((MainActivityContainer)getActivity()).navigationView.setSelectedItemId(R.id.homeMenu);
        }
        else{
            showDialog("Not match");
            Log.i(TAG, "ffffffffffffffffffffonLoginResult: "+result);
        }
    }

    public void showDialog(String msg)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setMessage(msg);
        alert.setTitle("Log in");
        alert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivityContainer)getActivity()).showLogIn();
                Log.i(TAG, "sure logout cancel: ");
            }
        });

        alert.show();
    }
}