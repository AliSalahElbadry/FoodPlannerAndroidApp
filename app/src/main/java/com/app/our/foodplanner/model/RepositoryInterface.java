package com.app.our.foodplanner.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.app.our.foodplanner.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    Meal getMeal(String id);
    PlanOfWeek getPlan(int id);
    List<Meal> getAllFavMeals(boolean isFav);
    List<Meal> getAllMealsInPlan(String week, String month, String year);
    List<PlanOfWeek> getPlans();
    LiveData<List<PlanOfWeek>> getPlansLive();
    LiveData<List<Meal>> getAllFavMealsLive(boolean isFav);
    LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year);
    void updateFavoriteInMeal(boolean isFav, String idMeal);
    void updateDateInMeal(String time, String day, String week, String month, String year, String idMeal);
    void insertMeal(Meal meal);
    void insertPlan(PlanOfWeek plan);
    void deleteMeal(Meal meal);
    void deletePlan(PlanOfWeek plan);

    public void enqueueCallGetMealByName(NetworkDelegate networkDelegate, Context context, String data);
    public void enqueueCallListAllMealsByFirstLetter(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallLookupFullMealDetailsById(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallLookupASingleRandomMeal(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallListAllCategories_Just_Names(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallListAllArea_Just_Names(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallListAllIngredients_Just_Names(NetworkDelegate networkDelegate, Context context);
    public void enqueueCallFilterByMainIngredient(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, Context context,String data);
    public void enqueueCallFilterByArea(NetworkDelegate networkDelegate, Context context,String data);

    public void setUserData(@NonNull Context context, String uname, String email, String pass);
    public String[] getUserData(@NonNull Context context);
}
