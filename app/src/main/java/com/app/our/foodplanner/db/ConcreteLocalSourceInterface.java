package com.app.our.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface ConcreteLocalSourceInterface {

    Single<Meal> getMeal(String id);
    Single<PlanOfWeek> getPlan(int id);
    List<Meal> getAllFavMeals(boolean isFav);
    Single<List<Meal>> getAllMealsInPlan(String week, String month, String year);
    Single<List<PlanOfWeek>> getPlans();
    LiveData<List<PlanOfWeek>> getPlansLive();
    LiveData<List<Meal>> getAllFavMealsLive(boolean isFav);
    LiveData<List<Meal>> getAllMealsInPlanLive(String week, String month, String year);
    Completable updateFavoriteInMeal(boolean isFav, String idMeal);
    Completable updateDateInMeal(String time, String day, String week, String month, String year, String idMeal);
    Completable insertMeal(Meal meal);
    Completable insertPlan(PlanOfWeek plan);
    Completable deleteMeal(Meal meal);
    Completable deletePlan(PlanOfWeek plan);

}
