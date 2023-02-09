package com.app.our.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface ConcreteLocalSourceInterface {

    Meal getMeal(String id);
    PlanOfWeek getPlan(int id);
    List<Meal> getAllFavMeals(boolean isFav);
    List<Meal> getAllMealsInPlan(String week, String month, String year);
    List<PlanOfWeek> getPlans();
    LiveData<List<PlanOfWeek>> getPlansLive();
    Observable<List<Meal>> getAllFavMealsLive(boolean isFav);
    LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year);
    void updateFavoriteInMeal(boolean isFav, String idMeal);
    void updateDateInMeal(String time, String day, String week, String month, String year, String idMeal);
    void insertMeal(Meal meal);
    void insertPlan(PlanOfWeek plan);
    void deleteMeal(Meal meal);
    void deletePlan(PlanOfWeek plan);

}
