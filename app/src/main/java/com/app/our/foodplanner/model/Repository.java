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
    public Single<Meal> getMeal(String id,String uId) {
        return localSource.getMeal(id,uId);
    }

    @Override
    public Single<List<Meal>> getAllFavLikeMeal(String id,String uId,boolean isfav) {
        return localSource.getAllFavLikeMeal(id,uId,isfav);
    }

    @Override
    public Single<Meal> MealInPlan(String mealId,String uId, String year, String month, String week, String day, String time) {
        return localSource.MealInPlan(mealId,uId,year,month,week,day,time);
    }

    @Override
    public Single<Integer> isMealExists(String id,String uId) {
        return localSource.isMealExists(id,uId);
    }

    @Override
    public Completable removeMealFromPlan(String mealid, String week,String uId) {
        return localSource.removeMealFromPlan(mealid,week,uId);
    }

    @Override
    public Single<PlanOfWeek> getPlan(int id,String uId) {
        return localSource.getPlan(id,uId);
    }

    @Override
    public List<Meal> getAllFavMeals(boolean isFav,String uId) {
        return localSource.getAllFavMeals(isFav,uId);
    }

    @Override
    public Single<List<Meal>> getAllMealsInPlan(String week, String month, String year,String uId) {
        return localSource.getAllMealsInPlan(week,month,year,uId);
    }

    @Override
    public Single<List<PlanOfWeek>> getPlans(String uId) {
        return localSource.getPlans(uId);
    }

    @Override
    public Single<List<Meal>> getAllMeals(String uId) {
        return localSource.getAllMeals(uId);
    }


    @Override
    public Observable<List<Meal>> getAllFavMealsLive(boolean isFav,String uId) {

        return localSource.getAllFavMealsLive(true,uId);
    }


    @Override
    public Completable updateFavoriteInMeal(boolean isFav, String idMeal,String uId) {
        return  localSource.updateFavoriteInMeal(isFav,idMeal,uId);
    }

    @Override
    public Completable updateDateInMeal(String time,String uId, String day, String week, String month, String year, String idMeal) {
        return localSource.updateDateInMeal(time,uId,day,week,month,year,idMeal);
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return   localSource.insertMeal(meal);
    }

    @Override
    public Completable insertPlan(PlanOfWeek plan) {
        return  localSource.insertPlan(plan);
    }

    @Override
    public Completable removeUnneeded() {
        return localSource.removeUnneeded();
    }

    @Override
    public Completable deleteMeal(String meal,String uId) {
        return localSource.deleteMeal(uId,meal);
    }

    @Override
    public Completable deletePlan(int plan,String uId) {
        return localSource.deletePlan(plan,uId);
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

    @Override
    public void deleteUserData() {
        shared_pref.deleteUserData();
    }
}