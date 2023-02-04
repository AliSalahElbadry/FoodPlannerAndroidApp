package com.app.our.foodplanner.network;

import android.content.Context;

public interface RemoteSource {
    public void enqueueCallGetMealByName(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallListAllMealsByFirstLetter(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallLookupFullMealDetailsById(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallLookupASingleRandomMeal(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallListAllCategories_Just_Names(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallListAllArea_Just_Names(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallListAllIngredients_Just_Names(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallFilterByMainIngredient(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallFilterByArea(NetworkDelegate networkDelegate, Context context,String data);

}