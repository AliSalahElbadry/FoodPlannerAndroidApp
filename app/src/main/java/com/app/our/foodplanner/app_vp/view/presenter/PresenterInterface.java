package com.app.our.foodplanner.app_vp.view.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.app.our.foodplanner.app_vp.view.signup.SignupFragmentInterface;
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

 public void addToPlan(Meal meal);
 public void showVideo(String url);
 public void setTargetAddMealToPlan(Meal meal);
 //end Meal

 //profile
 public String[]getUserData();

 public void doLogin(String email, String pass);
 public  void logout();


 public void putUserData(String userName, String email, String password);
 public void getAllPlans();
 public void setTargetAddMealToPlanPlanData(PlanOfWeek plan);

 public void setTargetMealDayAndTime(String day,String time);

 public void deleteToFav(Meal mealdelete);

}
