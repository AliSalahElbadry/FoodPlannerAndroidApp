package com.app.our.foodplanner.app_vp.view.meal;

import android.graphics.Bitmap;

import com.app.our.foodplanner.model.Meal;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MealFragmentInterface {

    public void showMeal(Bitmap bitmap);
    public void setMealData(Meal meal, ArrayList<String>res);
    public  void setAddFavRes(boolean isSet);
}
