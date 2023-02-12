package com.app.our.foodplanner.app_vp.view.presenter;

import android.view.View;
import android.widget.ImageView;

import com.app.our.foodplanner.app_vp.view.plan.PlanFragmentInterface;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;
import java.util.List;

public interface PresenterInterface {

 //home functions
 public void searchMealByName(String search);
 public void getMealByName(String name);
 public void showFilter();

 public void getMealsByCategory(String category);
 public void getAllCategories();
 public void getRandomMeal();
 public void getRandomMealImage(ImageView imageView, View view,String url);

 public boolean isLogedIn();
 //end home

 // Meal Page
 public void addToFav(Meal meal);
public  void getAllFav();
 public void setTargetAddMealToPlan(Meal meal);
 public void removePlan(PlanOfWeek plan);
 public void getMealsInPlan(PlanOfWeek plan);
 public void addPlan(PlanOfWeek plan);
 public String[]getUserData();
public ArrayList<String> getIngredinetsInMeal(Meal meal);
public void backupYourData();
public void  retriveData();
 public void doLogin(String email, String pass);
public String getuId();
 public  void logout();
 public  void  setIsLogedIn(boolean isLogedIn);
 public void deleteMealInPlan(String mealId,String mealDay,String mealTime, PlanOfWeek plan);

public void setPlanInterface(PlanFragmentInterface planInterface);
public void sendFirstDayInWeekMeals(String week);

 public void putUserData(String userName, String email, String password);
 public void getAllPlans();
 public void setTargetAddMealToPlanPlanData(PlanOfWeek plan);

 public void setTargetMealDayAndTime(String day,String time);


 public void UpdateMealOfFavouriteList(Boolean isFav,String Meal);
 public List<Meal> castListToSet(List<Meal>meals);

 public void googleSignIn(String email,String name,String pass);

 public void getMealByArea(String Area);
 public void getMealByIngredient(String ingredient);
 public void searchForIngredient(String search);
 public void searchForArea(String search);
 public void getAllAreasForFilter();
 public  void getAllIngredientsForFilter();
}
