package com.app.our.foodplanner.app_vp.view.home;

import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public interface HomeFragmentInterface {

    public void showCategories(ArrayList<Category> Res);

    public void showRandomMeal(ArrayList<Meal> Res);

    public void showMealsByCategory(ArrayList<Meal> Res);

    public void updateMealsAfterFilter(String[]meals);// just let this meals in the list and remove others

    public void updateMealsAfterSearch(String[]meals);

}
