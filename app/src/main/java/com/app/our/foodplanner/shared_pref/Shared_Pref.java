package com.app.our.foodplanner.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class Shared_Pref implements Shared_Pref_Interface{

    @Override
    public void setUserData(@NonNull Context context, String uname, String email, String pass)
    {
       SharedPreferences.Editor preferences=context.getSharedPreferences("uData", Context.MODE_PRIVATE).edit();
       preferences.putString("uName",uname);
       preferences.putString("email",email);
       preferences.putString("pass",pass);
       preferences.commit();
    }

    @Override
    public String[] getUserData(@NonNull Context context)
    {
        String[]data=new String[3];
        SharedPreferences preferences=context.getSharedPreferences("uData",Context.MODE_PRIVATE);
        data[0]=preferences.getString("uName","");
        data[1]=preferences.getString("email","");
        data[2]=preferences.getString("pass","");
        return  data;
    }
}
