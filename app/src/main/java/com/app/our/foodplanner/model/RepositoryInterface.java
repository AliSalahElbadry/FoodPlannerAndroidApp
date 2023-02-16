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
    Single<Meal> getMeal(String id,String uId);
    Single<PlanOfWeek> getPlan(int id,String uId);

    List<Meal> getAllFavMeals(boolean isFav,String uId);
    Single<List<Meal>> getAllMealsInPlan(String week,String month,String year,String uId);
    Single<List<PlanOfWeek>> getPlans(String uId);

    Completable removeMealFromPlan(String mealid,String week,String uId);
    Observable<List<Meal>> getAllFavMealsLive(boolean isFav,String uId);
    Single<List<Meal>>getAllFavLikeMeal(String id,String uId,boolean isfav);

    Single<List<Meal>>getAllMeals(String uId);

    Single<Integer> isMealExists(String id,String uId);
    Completable updateFavoriteInMeal(boolean isFav, String idMeal,String uId);

    Completable updateDateInMeal(String time,String day,String week,String month,String year,String idMeal,String uId);
    Single<Meal> MealInPlan(String mealId,String year,String month,String week,String day,String time,String uId);
    Completable insertMeal(Meal meal);
    Completable insertPlan(PlanOfWeek plan);
    Completable removeUnneeded();
    Completable DeleteMeal(int id);
    Completable deleteMeal(String mealId,String uId);

    Completable deletePlan(int planid,String uId);
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
    public void deleteUserData();


}
