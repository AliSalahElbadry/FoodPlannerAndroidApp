package com.app.our.foodplanner.network;

import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.RootArea;
import com.app.our.foodplanner.model.RootCategory;
import com.app.our.foodplanner.model.RootIngredient;
import com.app.our.foodplanner.model.RootMeal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkDelegate {
    public void getMealByNameOnSuccessResults(ArrayList<Meal> Res);
    public void listAllMealsByFirstLetterOnSuccessResults(ArrayList<Meal> Res);

    public void lookupFullMealDetailsByIdOnSuccessResults(ArrayList<Meal> Res);
    public void lookupASingleRandomMealOnSuccessResults(ArrayList<Meal> Res);
    public void listAllCategories_Just_NamesOnSuccessResults(ArrayList<Category> Res);
    public void listAllArea_Just_NamesOnSuccessResults(ArrayList<Area> Res);
    public void listAllIngredients_Just_NamesOnSuccessResults(ArrayList<Ingredient> Res);
    public void filterByMainIngredientOnSuccessResults(ArrayList<Meal> Res);
    public void filterByCategoryOnSuccessResults(ArrayList<Meal> Res);
    public void filterByAreaOnSuccessResults(ArrayList<Meal> Res);

    public void onFailureResults(String msg);
}
