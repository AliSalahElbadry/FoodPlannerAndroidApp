package com.app.our.foodplanner.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

public class Shared_Pref implements Shared_Pref_Interface{

    private static Shared_Pref shared_pref;
    private Context context;
    private Shared_Pref(Context context)
    {
     this.context=context;
    }
    public static Shared_Pref getInstance(Context context)
    {
        if(shared_pref==null)
        {
            shared_pref=new Shared_Pref(context);
        }
        return shared_pref;
    }
    @Override
    public void setUserData(String uname, String email, String pass)
    {
       SharedPreferences.Editor preferences=context.getSharedPreferences("uData", Context.MODE_PRIVATE).edit();
       preferences.putString("uName",uname);
       preferences.putString("email",email);
       preferences.putString("pass",pass);
       preferences.commit();
    }

    @Override
    public String[] getUserData()
    {
        String[]data=new String[3];
        SharedPreferences preferences=context.getSharedPreferences("uData",Context.MODE_PRIVATE);
        data[0]=preferences.getString("uName","");
        data[1]=preferences.getString("email","");
        data[2]=preferences.getString("pass","");
        if(data[1].isEmpty()||data[2].isEmpty())
        {
           return  null;
        }
        return  data;
    }

    @Override
    public void deleteUserData() {
        SharedPreferences preferences=context.getSharedPreferences("uData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.commit();

    }

}
