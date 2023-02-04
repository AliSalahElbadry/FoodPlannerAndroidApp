package com.app.our.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("SELECT * From Meals where idMeal Like:id limit 1")
    Meal getMeal(String id);
    @Query("SELECT * From PlanOfWeek where idPlan Like:id limit 1")
    PlanOfWeek getPlan(int id);

    @Query("SELECT * From Meals where isFavorite Like:isFav")
    List<Meal> getAllFavMeals(boolean isFav);
    @Query("SELECT * From Meals where meal_Week Like:week and meal_Month like :month and meal_Year like:year")
    List<Meal> getAllMealsInPlan(String week,String month,String year);

    @Query("SELECT * From PlanOfWeek")
    List<PlanOfWeek> getPlans();

    @Query("SELECT * From PlanOfWeek")
    LiveData<List<PlanOfWeek>> getPlansLive();

    @Query("SELECT * From Meals where isFavorite Like:isFav")
    LiveData<List<Meal>> getAllFavMealsLive(boolean isFav);

    @Query("SELECT * From Meals where meal_Week Like:week and meal_Month like :month and meal_Year like:year")
    LiveData<List<Meal>> getAllMealsInPlanLive(String week,String month,String year);

    @Query("Update Meals set isFavorite=:isFav where idMeal like:idMeal")
    void updateFavoriteInMeal(boolean isFav,String idMeal);

    @Query("Update Meals set meal_Year=:year , meal_Month=:month ,meal_Week=:week,meal_Day=:day,meal_Time=:time where idMeal like:idMeal")
    void updateDateInMeal(String time,String day,String week,String month,String year,String idMeal);

    @Insert(onConflict =OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    void insertPlan(PlanOfWeek plan);
    @Delete
    void deleteMeal(Meal meal);

    @Delete
    void deletePlan(PlanOfWeek plan);


}
