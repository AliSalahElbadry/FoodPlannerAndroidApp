package com.app.our.foodplanner.db;

import android.content.Context;
import android.service.autofill.SaveInfo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.sql.SQLData;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
public class ConcreteLocalSource implements ConcreteLocalSourceInterface{

    MealDAO mealDAO;

    private static ConcreteLocalSource localSource = null;


    private ConcreteLocalSource(Context context){
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=db.MealDAO();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(localSource == null){
            localSource=new ConcreteLocalSource(context);
        }
        return localSource;
    }
    @Override
    public Single<Meal> getMeal(String id,String uId) {
        return mealDAO.getMeal(id,uId);
    }

    @Override
    public Single<List<Meal>> getAllFavLikeMeal(String id,String uId,boolean isfav) {
        return mealDAO.getAllFavLikeMeal(id,uId,isfav);
    }

    @Override
    public Single<Meal> MealInPlan(String mealId,String uId, String year, String month, String week, String day, String time) {
        return mealDAO.MealInPlan(mealId,uId,year,month,week,day,time);
    }

    @Override
    public Single<Integer> isMealExists(String id,String uId) {
        return mealDAO.isMealExists(id,uId);
    }

    @Override
    public Completable removeMealFromPlan(String mealid, String week,String uId) {
        return mealDAO.removeMealFromPlan(mealid,week,uId);
    }

    @Override
    public Single<PlanOfWeek> getPlan(int id,String uId) {
        return mealDAO.getPlan(id,uId);
    }

    @Override
    public List<Meal> getAllFavMeals(boolean isFav,String uId) {
        return mealDAO.getAllFavMeals(isFav,uId);
    }

    @Override
    public Single<List<Meal>> getAllMealsInPlan(String week, String month, String year,String uId) {
        return mealDAO.getAllMealsInPlan(week,month,year,uId);
    }

    @Override
    public Single<List<PlanOfWeek>> getPlans(String uId) {
       return mealDAO.getPlans(uId);
    }

    @Override
    public Single<List<Meal>> getAllMeals(String uId) {
        return mealDAO.getAllMeals(uId);
    }


    @Override
    public Observable<List<Meal>> getAllFavMealsLive(boolean isFav,String uId) {

        return mealDAO.getAllFavMealsLive(true,uId);
    }


    @Override
    public Completable updateFavoriteInMeal(boolean isFav, String idMeal,String uId) {
      return  mealDAO.updateFavoriteInMeal(isFav,idMeal,uId);
    }

    @Override
    public Completable removeUnneeded() {
        return mealDAO.removeUnneeded("",false);
    }

    @Override
    public Completable DeleteMeal(int id) {
        return mealDAO.DeleteMeal(id);
    }

    @Override
    public Completable updateDateInMeal(String time,String uId, String day, String week, String month, String year, String idMeal) {
        return mealDAO.updateDateInMeal(time,uId,day,week,month,year,idMeal);
    }

    @Override
    public Completable insertMeal(Meal meal) {
              return   mealDAO.insertMeal(meal);
    }

    @Override
    public Completable insertPlan(PlanOfWeek plan) {
             return  mealDAO.insertPlan(plan);
    }

    @Override
    public Completable deleteMeal(String meal,String uId) {
        return mealDAO.deleteMeal(uId,meal);
    }

    @Override
    public Completable deletePlan(int plan,String uId) {
        return mealDAO.deletePlan(plan,uId);
    }


}
