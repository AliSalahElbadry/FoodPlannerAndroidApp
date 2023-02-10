package com.app.our.foodplanner.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.app.our.foodplanner.db.ConcreteLocalSource;
import com.app.our.foodplanner.network.ConcreteRemoteSource;
import com.app.our.foodplanner.network.NetworkDelegate;
import com.app.our.foodplanner.shared_pref.Shared_Pref;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class Repository implements RepositoryInterface {

    private static  Repository repo;
    private ConcreteRemoteSource remoteSource;
    private ConcreteLocalSource localSource;
    private Shared_Pref shared_pref;
    private  Repository(Context context)
    {
        remoteSource=ConcreteRemoteSource.getInstance(context);
        localSource=ConcreteLocalSource.getInstance(context);
        shared_pref=Shared_Pref.getInstance(context);
    }
    public static   Repository getInstance(Context context)
    {
        if(repo==null)
        {
         repo=new Repository(context);
        }
        return  repo;
    }

    @Override
    public Single<Meal> getMeal(String id) {
        return localSource.getMeal(id);
    }

    @Override
    public Single<PlanOfWeek> getPlan(int id) {
        return localSource.getPlan(id);
    }

    @Override
    public List<Meal> getAllFavMeals(boolean isFav) {
        return localSource.getAllFavMeals(true);
    }

    @Override
    public Single<List<Meal>> getAllMealsInPlan(String week, String month, String year) {
        return localSource.getAllMealsInPlan(week,month,year);
    }

    @Override
    public Single<List<PlanOfWeek>> getPlans() {
        return localSource.getPlans();
    }

    @Override
    public LiveData<List<PlanOfWeek>> getPlansLive() {
        return localSource.getPlansLive();
    }

    @Override
    public Observable<List<Meal>> getAllFavMealsLive(boolean isFav) {
        return localSource.getAllFavMealsLive(isFav);
    }

    @Override
    public LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year) {
        return localSource.getAllMealsInPlanLive(week,month,year);
    }

    @Override
    public Completable updateFavoriteInMeal(boolean isFav, String idMeal) {
        return localSource.updateFavoriteInMeal(isFav,idMeal);
    }

    @Override
    public Completable updateDateInMeal(String time, String day, String week, String month, String year, String idMeal) {
        return localSource.updateDateInMeal(time,day,week,month,year,idMeal);
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return localSource.insertMeal(meal);
    }

    @Override
    public Completable insertPlan(PlanOfWeek plan) {
        return localSource.insertPlan(plan);
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return localSource.deleteMeal(meal);
    }

    @Override
    public Completable deletePlan(PlanOfWeek plan) {
        return localSource.deletePlan(plan);
    }

    @Override
    public void enqueueCallGetMealByName(NetworkDelegate networkDelegate, Context context, String data) {
        remoteSource.enqueueCallGetMealByName(networkDelegate,context,data);
    }

    @Override
    public void enqueueCallListAllMealsByFirstLetter(NetworkDelegate networkDelegate, Context context, String data) {
        remoteSource.enqueueCallListAllMealsByFirstLetter(networkDelegate,context,data);
    }

    @Override
    public void enqueueCallLookupFullMealDetailsById(NetworkDelegate networkDelegate, Context context, String data) {
        remoteSource.enqueueCallLookupFullMealDetailsById(networkDelegate,context,data);
    }

    @Override
    public void enqueueCallLookupASingleRandomMeal(NetworkDelegate networkDelegate, Context context) {
        remoteSource.enqueueCallLookupASingleRandomMeal(networkDelegate,context);
    }

    @Override
    public void enqueueCallListAllCategories_Just_Names(NetworkDelegate networkDelegate, Context context) {
        remoteSource.enqueueCallListAllCategories_Just_Names(networkDelegate,context);
    }

    @Override
    public void enqueueCallListAllArea_Just_Names(NetworkDelegate networkDelegate, Context context) {
        remoteSource.enqueueCallListAllArea_Just_Names(networkDelegate,context);
    }

    @Override
    public void enqueueCallListAllIngredients_Just_Names(NetworkDelegate networkDelegate, Context context) {
        remoteSource.enqueueCallListAllIngredients_Just_Names(networkDelegate,context);
    }

    @Override
    public void enqueueCallFilterByMainIngredient(NetworkDelegate networkDelegate, Context context, String data) {
        remoteSource.enqueueCallFilterByMainIngredient(networkDelegate,context,data);
    }

    @Override
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, Context context, String data) {
        remoteSource.enqueueCallFilterByCategory(networkDelegate,context,data);
    }

    @Override
    public void enqueueCallFilterByArea(NetworkDelegate networkDelegate, Context context, String data) {
        remoteSource.enqueueCallFilterByArea(networkDelegate,context,data);
    }

    @Override
    public void setUserData(String uname, String email, String pass) {
        shared_pref.setUserData(uname,email,pass);
    }

    @Override
    public String[] getUserData() {
        return shared_pref.getUserData();
    }
}