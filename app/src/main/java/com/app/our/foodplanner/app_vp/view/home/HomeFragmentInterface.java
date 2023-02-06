package com.app.our.foodplanner.app_vp.view.home;

import android.graphics.Bitmap;

import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public interface HomeFragmentInterface {

    public void showCategories(ArrayList<Category> Res);

    public void showRandomMeal(ArrayList<Meal> Res);

    public void showMeals(ArrayList<Meal>Res);
    public void showRandomImage(Bitmap bitmap);

}
