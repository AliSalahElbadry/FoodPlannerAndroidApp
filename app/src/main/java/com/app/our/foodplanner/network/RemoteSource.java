package com.app.our.foodplanner.network;

import android.content.Context;

public interface RemoteSource {
    public void enqueueCall(NetworkDelegate networkDelegate, Context context);
}