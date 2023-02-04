package com.app.our.foodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.List;

public class ConcreteLocalSource implements ConcreteLocalSourceInterface{

    MealDAO mealDAO;

    private static ConcreteLocalSource localSource = null;

    LiveData<List<PlanOfWeek>> getPlansLive;
    LiveData<List<Meal>> getAllFavMealsLive;
    LiveData<List<Meal>> getAllMealsInPlanLive;

    private ConcreteLocalSource(Context context){
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=db.MealDAO();
        getPlansLive=mealDAO.getPlansLive();
        getAllFavMealsLive=mealDAO.getAllFavMealsLive( true);
        getAllMealsInPlanLive=mealDAO.getAllMealsInPlanLive("week","month","year");

    }

    public static ConcreteLocalSource getInstance(Context context){
        if(localSource == null){
            localSource=new ConcreteLocalSource(context);
        }
        return localSource;
    }
    @Override
    public Meal getMeal(String id) {
        return mealDAO.getMeal(id);
    }

    @Override
    public PlanOfWeek getPlan(int id) {
        return mealDAO.getPlan(id);
    }

    @Override
    public List<Meal> getAllFavMeals(boolean isFav) {
        return mealDAO.getAllFavMeals(isFav);
    }

    @Override
    public List<Meal> getAllMealsInPlan(String week, String month, String year) {
        return mealDAO.getAllMealsInPlan(week,month,year);
    }

    @Override
    public List<PlanOfWeek> getPlans() {
       return mealDAO.getPlans();
    }

    @Override
    public LiveData<List<PlanOfWeek>> getPlansLive() {
        return getPlansLive;
    }

    @Override
    public LiveData<List<Meal>> getAllFavMealsLive(boolean isFav) {
        return getAllFavMealsLive;
    }

    @Override
    public LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year) {
        return getAllMealsInPlanLive;
    }

    @Override
    public void updateFavoriteInMeal(boolean isFav, String idMeal) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.updateFavoriteInMeal(isFav,idMeal);
            }
        }).start();

    }

    @Override
    public void updateDateInMeal(String time, String day, String week, String month, String year, String idMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.updateDateInMeal(time,day,week,month,year,idMeal);
            }
        }).start();

    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();

    }

    @Override
    public void insertPlan(PlanOfWeek plan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertPlan(plan);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public void deletePlan(PlanOfWeek plan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deletePlan(plan);
            }
        }).start();
    }


}
