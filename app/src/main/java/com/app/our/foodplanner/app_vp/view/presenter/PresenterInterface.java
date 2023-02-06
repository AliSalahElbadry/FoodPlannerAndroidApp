package com.app.our.foodplanner.app_vp.view.presenter;

import android.graphics.Bitmap;

import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public interface PresenterInterface {

 //home functions
 public void searchMealByName(ArrayList<Meal>meals, String search);
 public void showFilter();

 public void showMeal(Meal meal, Bitmap image);// show meal page
 public void getMealsByCategory(String category);
 public void showFavPage();

 public void showProfilePage();

 public void showPlansPage();
 //end home

 // Meal Page
 public void addToFav(Meal meal);

 public void addToPlan(Meal meal);
 public void showVideo(String url);
 //end Meal

}
