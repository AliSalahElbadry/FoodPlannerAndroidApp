package com.app.our.foodplanner.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.app.our.foodplanner.network.NetworkDelegate;

import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface RepositoryInterface {
    Single<Meal>getMeal(String id);
    Single<PlanOfWeek> getPlan(int id);
    List<Meal> getAllFavMeals(boolean isFav);
    Single<List<Meal>> getAllMealsInPlan(String week, String month, String year);
    Single<List<PlanOfWeek>> getPlans();
    LiveData<List<PlanOfWeek>> getPlansLive();
    Observable<List<Meal>> getAllFavMealsLive(boolean isFav);
    LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year);
    Completable updateFavoriteInMeal(boolean isFav, String idMeal);
    Completable updateDateInMeal(String time, String day, String week, String month, String year, String idMeal);
    Completable insertMeal(Meal meal);
    Completable insertPlan(PlanOfWeek plan);
    Completable deleteMeal(Meal meal);
    Completable deletePlan(PlanOfWeek plan);

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

    public void setUserData(String uname, String email, String pass);
    public String[] getUserData();


}
