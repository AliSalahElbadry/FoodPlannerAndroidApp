package com.app.our.foodplanner.app_vp.view.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;
import com.app.our.foodplanner.model.Repository;
import com.app.our.foodplanner.network.NetworkDelegate;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Presenter implements NetworkDelegate , PresenterInterface {
    //Data Holders
    private Repository repository;
    private ArrayList<Meal>meals;
    private ArrayList<Category>categories;
    private ArrayList<Area>areas;
    private ArrayList<Ingredient>ingredients;
    private ArrayList<PlanOfWeek>plans;
    private String[]uData;

    private Context context;

    public void setHomeFragment(HomeFragmentInterface homeFragment) {
        this.homeFragment = homeFragment;
    }

    //end Data Holders
    private HomeFragmentInterface homeFragment;
    public Presenter(Context context)
    {
        this.context=context;
        repository=Repository.getInstance(context);
        meals=new ArrayList<>();
        categories=new ArrayList<>();
        ingredients=new ArrayList<>();
        areas=new ArrayList<>();
        plans=new ArrayList<>();
        uData=repository.getUserData();
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<PlanOfWeek> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<PlanOfWeek> plans) {
        this.plans = plans;
    }

    public String[] getuData() {
        return uData;
    }

    public void setuData(String[] uData) {
        this.uData = uData;
    }

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

        homeFragment.showRandomMeal(Res);
    }

    @Override
    public void listAllCategories_Just_NamesOnSuccessResults(ArrayList< Category > Res) {
        homeFragment.showCategories(Res);
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
        meals=Res;
        homeFragment.showMeals(Res);
    }

    @Override
    public void filterByAreaOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void onFailureResults(String msg) {
    }

   //home functions
    @Override
    public void searchMealByName(String search) {
        Stream<Meal> stream= meals.stream().filter(i->i.getStrMeal().toLowerCase().startsWith(search.toLowerCase()));
        ArrayList<Meal> mealsAfter = new ArrayList<>(stream.collect(Collectors.toList()));
        homeFragment.showMeals(mealsAfter);
    }

    @Override
    public void showFilter() {

    }

    @Override
    public void showMeal(Meal meal, Bitmap image) {

    }

    @Override
    public void getMealsByCategory(String category) {
        repository.enqueueCallFilterByCategory(this,context,category);
    }

    @Override
    public void getAllCategories() {

            repository.enqueueCallListAllCategories_Just_Names(this,context);
            getMealsByCategory("Beef");
    }

    @Override
    public void getRandomMeal() {
        repository.enqueueCallLookupASingleRandomMeal(this,context);
    }

    @Override
    public void getRandomMealImage(ImageView imageView, View view, String url) {
        Glide.with(view).load(url).into(imageView);
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
