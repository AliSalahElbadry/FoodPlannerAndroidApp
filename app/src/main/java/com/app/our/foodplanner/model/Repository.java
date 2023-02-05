package com.app.our.foodplanner.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.app.our.foodplanner.network.NetworkDelegate;

import java.util.List;

public class Repository implements RepositoryInterface {

    private static  Repository repo;
    private  Repository()
    {

    }
    public static   Repository getInstance()
    {
        if(repo==null)
        {

        }
        return  repo;
    }

    @Override
    public Meal getMeal(String id) {
        return null;
    }

    @Override
    public PlanOfWeek getPlan(int id) {
        return null;
    }

    @Override
    public List<Meal> getAllFavMeals(boolean isFav) {
        return null;
    }

    @Override
    public List<Meal> getAllMealsInPlan(String week, String month, String year) {
        return null;
    }

    @Override
    public List<PlanOfWeek> getPlans() {
        return null;
    }

    @Override
    public LiveData<List<PlanOfWeek>> getPlansLive() {
        return null;
    }

    @Override
    public LiveData<List<Meal>> getAllFavMealsLive(boolean isFav) {
        return null;
    }

    @Override
    public LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year) {
        return null;
    }

    @Override
    public void updateFavoriteInMeal(boolean isFav, String idMeal) {

    }

    @Override
    public void updateDateInMeal(String time, String day, String week, String month, String year, String idMeal) {

    }

    @Override
    public void insertMeal(Meal meal) {

    }

    @Override
    public void insertPlan(PlanOfWeek plan) {

    }

    @Override
    public void deleteMeal(Meal meal) {

    }

    @Override
    public void deletePlan(PlanOfWeek plan) {

    }

    @Override
    public void enqueueCallGetMealByName(NetworkDelegate networkDelegate, Context context, String data) {

    }

    @Override
    public void enqueueCallListAllMealsByFirstLetter(NetworkDelegate networkDelegate, Context context, String data) {

    }

    @Override
    public void enqueueCallLookupFullMealDetailsById(NetworkDelegate networkDelegate, Context context, String data) {

    }

    @Override
    public void enqueueCallLookupASingleRandomMeal(NetworkDelegate networkDelegate, Context context) {

    }

    @Override
    public void enqueueCallListAllCategories_Just_Names(NetworkDelegate networkDelegate, Context context) {

    }

    @Override
    public void enqueueCallListAllArea_Just_Names(NetworkDelegate networkDelegate, Context context) {

    }

    @Override
    public void enqueueCallListAllIngredients_Just_Names(NetworkDelegate networkDelegate, Context context) {

    }

    @Override
    public void enqueueCallFilterByMainIngredient(NetworkDelegate networkDelegate, Context context, String data) {

    }

    @Override
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, Context context, String data) {

    }

    @Override
    public void enqueueCallFilterByArea(NetworkDelegate networkDelegate, Context context, String data) {

    }

    @Override
    public void setUserData(@NonNull Context context, String uname, String email, String pass) {

    }

    @Override
    public String[] getUserData(@NonNull Context context) {
        return new String[0];
    }
}