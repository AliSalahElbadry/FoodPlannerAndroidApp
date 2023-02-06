package com.app.our.foodplanner.app_vp.view.presenter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface PresenterInterface {

 //home functions
 public void searchMealByName(String search);
 public void showFilter();

 public void showMeal(Meal meal, Bitmap image);// show meal page
 public void getMealsByCategory(String category);
 public void getAllCategories();
 public void getRandomMeal();
 public void getRandomMealImage(ImageView imageView, View view,String url);

 //end home

 // Meal Page
 public void addToFav(Meal meal);

 public void addToPlan(Meal meal);
 public void showVideo(String url);
 //end Meal

}
