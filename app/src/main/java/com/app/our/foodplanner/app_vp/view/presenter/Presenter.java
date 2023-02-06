package com.app.our.foodplanner.app_vp.view.presenter;

import android.graphics.Bitmap;

import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.network.NetworkDelegate;

import java.util.ArrayList;

public class Presenter implements NetworkDelegate , PresenterInterface {
    @Override
    public void getMealByNameOnSuccessResults(ArrayList< Meal > Res) {
    }

    @Override
    public void listAllMealsByFirstLetterOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void lookupFullMealDetailsByIdOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void lookupASingleRandomMealOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void listAllCategories_Just_NamesOnSuccessResults(ArrayList< Category > Res) {

    }

    @Override
    public void listAllArea_Just_NamesOnSuccessResults(ArrayList< Area > Res) {

    }

    @Override
    public void listAllIngredients_Just_NamesOnSuccessResults(ArrayList< Ingredient > Res) {

    }

    @Override
    public void filterByMainIngredientOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void filterByCategoryOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void filterByAreaOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void onFailureResults(String msg) {
    }

   //home functions
    @Override
    public void searchMealByName(ArrayList<Meal> meals, String search) {

    }

    @Override
    public void showFilter() {

    }

    @Override
    public void showMeal(Meal meal, Bitmap image) {

    }

    @Override
    public void getMealsByCategory(String category) {

    }

    @Override
    public void showFavPage() {

    }

    @Override
    public void showProfilePage() {

    }

    @Override
    public void showPlansPage() {

    }

    //end home functions

    //Meal Page functions
    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void addToPlan(Meal meal) {

    }

    @Override
    public void showVideo(String url) {

    }
    //end Meal Page functions
}
