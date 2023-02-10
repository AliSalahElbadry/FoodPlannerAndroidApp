package com.app.our.foodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
public class ConcreteLocalSource implements ConcreteLocalSourceInterface{

    MealDAO mealDAO;

    private static ConcreteLocalSource localSource = null;

    LiveData<List<PlanOfWeek>> getPlansLive;
    Observable<List<Meal>> getAllFavMealsLive;
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
    public Single<Meal> getMeal(String id) {
        return mealDAO.getMeal(id);
    }

    @Override
    public Single<List<Meal>> getAllFavLikeMeal(String id) {
        return mealDAO.getAllFavLikeMeal(id);
    }

    @Override
    public Single<Integer> isMealExists(String id) {
        return mealDAO.isMealExists(id);
    }

    @Override
    public Completable removeMealFromPlan(String mealid, String week) {
        return mealDAO.removeMealFromPlan(mealid,week);
    }

    @Override
    public Single<PlanOfWeek> getPlan(int id) {
        return mealDAO.getPlan(id);
    }

    @Override
    public List<Meal> getAllFavMeals(boolean isFav) {
        return mealDAO.getAllFavMeals(isFav);
    }

    @Override
    public Single<List<Meal>> getAllMealsInPlan(String week, String month, String year) {
        return mealDAO.getAllMealsInPlan(week,month,year);
    }

    @Override
    public Single<List<PlanOfWeek>> getPlans() {
       return mealDAO.getPlans();
    }

    @Override
    public LiveData<List<PlanOfWeek>> getPlansLive() {
        return getPlansLive;
    }

    @Override
    public Observable<List<Meal>> getAllFavMealsLive(boolean isFav) {

        return getAllFavMealsLive;
    }

    @Override
    public LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year) {
        return getAllMealsInPlanLive;
    }

    @Override
    public Completable updateFavoriteInMeal(boolean isFav, String idMeal) {
      return  mealDAO.updateFavoriteInMeal(isFav,idMeal);
    }

    @Override
    public Completable updateDateInMeal(String time, String day, String week, String month, String year, String idMeal) {
        return mealDAO.updateDateInMeal(time,day,week,month,year,idMeal);
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
    public Completable deleteMeal(Meal meal) {
        return mealDAO.deleteMeal(meal);
    }

    @Override
    public Completable deletePlan(PlanOfWeek plan) {
        return mealDAO.deletePlan(plan);
    }


}
