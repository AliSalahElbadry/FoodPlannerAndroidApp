package com.app.our.foodplanner.app_vp.view.signup;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupFragment extends Fragment  implements SignupFragmentInterface{


    private static final int RC_SIGN_IN =0 ;
    EditText nameProfile;
   EditText emailSignup;
   EditText passSignup;
   EditText repassSignup;
   TextView goToLogIn;
   Button signUp;
   ImageView googleSignup;
   ProgressDialog progressDialog;
    public SignupFragment() {
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
        return inflater.inflate(R.layout.sign_up_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog=new ProgressDialog(getContext());
        emailSignup=view.findViewById(R.id.editTxtEmailSignUp);
        emailSignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        nameProfile=view.findViewById(R.id.editTxtUNameSignUp);
        passSignup=view.findViewById(R.id.editTxtPassSignUp);
        repassSignup=view.findViewById(R.id.editTxtRePassSignUp);
        goToLogIn=view.findViewById(R.id.textViewHaveAccountSignUp);
        signUp=view.findViewById(R.id.btnSignUps);
        googleSignup=view.findViewById(R.id.googleBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameProfile.getText().toString().isEmpty()) {
                    if (validateEmail(emailSignup.getText().toString())) {
                        if (validatePassword(passSignup.getText().toString())) {
                            if (passSignup.getText().toString().equals(repassSignup.getText().toString())) {

                                ((MainActivityContainerInterface)getActivity())
                                        .getPresenter()
                                        .putUserData(
                                                nameProfile.getText().toString(),
                                                emailSignup.getText().toString(),
                                                passSignup.getText().toString()
                                        );
                                progressDialog.setMessage("Please Wait SigningIn ....");
                                progressDialog.setCanceledOnTouchOutside(false);
                                progressDialog.show();
                            } else {
                                showDialog("Password Must Be Identical !");
                            }
                        } else {
                            showDialog("Password must have at least one numeric character ,one lowercase character,one lowercase character,one special symbol among @ # $ % and Password length should be between 8 and 20.");
                        }
                    } else {
                      showDialog("Invalid email \n" +
                              "should be like:example@gmail.com");
                    }
                }else{
                    showDialog("please Enter User Name");
                }
            }
        });
        googleSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .build();
                Intent signInIntent = GoogleSignIn.getClient(SignupFragment.this.getActivity().getApplicationContext(), gso).getSignInIntent();

                getActivity().startActivityForResult(signInIntent, 1);
                Log.e(TAG, "onClick: Fuuckkkkkkk" );
                ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                        new ActivityResultCallback<Uri>() {
                            @Override
                            public void onActivityResult(Uri uri) {

                            }
                        });
            }
        });


        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivityContainerInterface)getActivity()).showLogIn();
            }
        });

    }
    public void showDialog(String msg)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setMessage(msg);
        alert.setCancelable(true);
        alert.show();
    }

    public boolean validateEmail(String email)
    {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            Pattern pat = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pat.matcher(email).matches();
    }
    public  boolean validatePassword(String pass)
    {
        String regex= "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void getSignUpStatus(boolean status) {
        progressDialog.dismiss();
        if(status)
        {
            Toast.makeText(getContext(), "SignUp Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Error SignUp", Toast.LENGTH_SHORT).show();
        }
        ((MainActivityContainer)getActivity()).navigationView.setSelectedItemId(R.id.homeMenu);
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
            FirebaseAuth.getInstance().signInWithCredential(credential)
                   .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                         Log.e(".........................",",,,,,,,,,,,,,,,,,,,,,,,,,");
                                ((MainActivityContainer)getActivity()).navigationView.setSelectedItemId(R.id.homeMenu);
                                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                ((MainActivityContainerInterface)getActivity()).getPresenter().googleSignIn(account.getEmail(),account.getDisplayName());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                            Log.e( "","***********************" );
                        }
                    });


        } catch (ApiException e) {

            Log.w("Error", "signInResult:failed code " + e.getStatusCode());
        }
    }
}