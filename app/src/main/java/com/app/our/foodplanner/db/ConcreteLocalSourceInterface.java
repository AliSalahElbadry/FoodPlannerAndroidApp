package com.app.our.foodplanner.db;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface ConcreteLocalSourceInterface {

    Single<Meal> getMeal(String id,String uId);
     Single<PlanOfWeek> getPlan(int id,String uId);

    List<Meal> getAllFavMeals(boolean isFav,String uId);
    Single<List<Meal>> getAllMealsInPlan(String week,String month,String year,String uId);
     Single<List<PlanOfWeek>> getPlans(String uId);

    Completable removeMealFromPlan(String mealid,String week,String uId);
     Observable<List<Meal>> getAllFavMealsLive(boolean isFav,String uId);
    Single<List<Meal>>getAllFavLikeMeal(String id,String uId,boolean isfav);

    Single<List<Meal>>getAllMeals(String uId);

    Single<Integer> isMealExists(String id,String uId);
    Completable updateFavoriteInMeal(boolean isFav, String idMeal,String uId);
    Completable removeUnneeded();
    Completable DeleteMeal(int id);
    Completable updateDateInMeal(String time,String day,String week,String month,String year,String idMeal,String uId);
    Single<Meal> MealInPlan(String mealId,String year,String month,String week,String day,String time,String uId);
    Completable insertMeal(Meal meal);
    Completable insertPlan(PlanOfWeek plan);
    Completable deleteMeal(String mealId,String uId);

    Completable deletePlan(int planid,String uId);

}
