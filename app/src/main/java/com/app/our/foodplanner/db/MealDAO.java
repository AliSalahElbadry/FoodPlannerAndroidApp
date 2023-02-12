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
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {

    @Query("SELECT * From Meals where idMeal Like:id and userId like:uId limit 1")
    Single<Meal> getMeal(String id,String uId);
    @Query("SELECT * From PlanOfWeek where idPlan Like:id and userId like:uId limit 1")
    Single<PlanOfWeek> getPlan(int id,String uId);

    @Query("SELECT * From Meals where isFavorite Like:isFav and userId like:uId")
    List<Meal> getAllFavMeals(boolean isFav,String uId);
    @Query("SELECT * From Meals where meal_Week Like:week and meal_Month like :month and meal_Year like:year and userId like:uId")
    Single<List<Meal>> getAllMealsInPlan(String week,String month,String year,String uId);

    @Query("SELECT * From PlanOfWeek where userId like:uId")
    Single<List<PlanOfWeek>> getPlans(String uId);

    @Query("delete from Meals where idMeal like:mealid and meal_Week like:week and userId like:uId")
    Completable removeMealFromPlan(String mealid,String week,String uId);
    @Query("SELECT * From Meals where isFavorite Like:isFav and userId like:uId")
    Observable<List<Meal>> getAllFavMealsLive(boolean isFav,String uId);
    @Query("select * from meals where idMeal like:id and userId like:uId and isFavorite like:isfav")
    Single<List<Meal>>getAllFavLikeMeal(String id,String uId,boolean isfav);
    @Query("Delete from Meals Where Meals.meal_Day=NULL and meal_Time=NULL and meal_Week=NULL and meal_Month=NULL and meal_Year=NULL and isFavorite=0")
    Completable removeUnneeded();

    @Query("select * from meals where  userId like:uId")
    Single<List<Meal>>getAllMeals(String uId);

    @Query("select count(idMeal) from Meals where idMeal like:id and userId like:uId")
    Single<Integer> isMealExists(String id,String uId);
    @Query("Update Meals set isFavorite=:isFav where idMeal like:idMeal and userId like:uId")
    Completable updateFavoriteInMeal(boolean isFav, String idMeal,String uId);

    @Query("Update Meals set meal_Year=:year , meal_Month=:month ,meal_Week=:week,meal_Day=:day,meal_Time=:time where idMeal like:idMeal and userId like:uId")
    Completable updateDateInMeal(String time,String day,String week,String month,String year,String idMeal,String uId);
    @Query("select * from Meals where idMeal like:mealId and meal_Year like:year and meal_Month like:month and meal_Week like:week and meal_Day like:day and meal_Time like:time and userId like:uId")
    Single<Meal> MealInPlan(String mealId,String year,String month,String week,String day,String time,String uId);
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    Completable insertMeal(Meal meal);
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    Completable insertPlan(PlanOfWeek plan);
    @Query("delete from Meals Where userId like:uId and idMeal like:mealId")
    Completable deleteMeal(String mealId,String uId);

    @Query("delete from PlanOfWeek where userId like:uId and idPlan like:planid")
    Completable deletePlan(int planid,String uId);


}
