package com.app.our.foodplanner.shared_pref;

import android.content.Context;

import androidx.annotation.NonNull;

public interface Shared_Pref_Interface {
    public void setUserData(@NonNull Context context, String uname, String email, String pass);
    public String[] getUserData(@NonNull Context context);
}
