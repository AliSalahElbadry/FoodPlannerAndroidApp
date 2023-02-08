package com.app.our.foodplanner.app_vp.view.login;

import static android.content.ContentValues.TAG;

import android.content.Context;
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

        //
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_layout, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();

        Log.i(TAG, "///////////onViewCreated: 85462625321652");



        editTxtUNameLogIn=view.findViewById(R.id.editTxtUNameLogInPage);
        editTxtPassLogIn=view.findViewById(R.id.editTxtPassLogInPage);
        btnLogIn=view.findViewById(R.id.btnLogInPage);
        imageViewGoogleLog=view.findViewById(R.id.imageViewGoogleLogPage);
        textViewHaveAccountLogIn=view.findViewById(R.id.textViewHaveAccountLogInPage);

        Log.i(TAG, "onViewCreated: 85462625321652");
       btnLogIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(editTxtUNameLogIn.getText().toString().isEmpty()||editTxtPassLogIn.getText().toString().isEmpty()){
                   Toast.makeText(getContext(), "Please, enter your mobile or password", Toast.LENGTH_SHORT).show();
                   Log.i(TAG, "onClickLogin://///////////////////////////// ");
                   Log.d(TAG, "onClick: ");
               }
               else{
                  // PresenterInterface.onClickLogin(getContext(),editTxtPassLogIn.getText().toString(),editTxtPassLogIn.getText().toString());
                   presenterInterface.doLogin(editTxtUNameLogIn.getText().toString(),editTxtPassLogIn.getText().toString());

                   Toast.makeText(getContext(), "eeeeeelse", Toast.LENGTH_SHORT).show();

               }

           }
       });


    }

//    @Override
//    public void onClearText() {
//        editTxtUNameLogIn.setText(" ");
//        editTxtPassLogIn.setText(" ");
//    }

    @Override
    public void onLoginResult(Boolean result) {

        if(result){
           // Toast.makeText(this.getContext(), "Login succes", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "ssssssssssssonLoginResult: "+result);

        }
        else{
           // Toast.makeText(LogInFragment.this.getContext(), "Login Fail", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "ffffffffffffffffffffonLoginResult: "+result);
        }
    }
}