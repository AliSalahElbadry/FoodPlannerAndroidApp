package com.app.our.foodplanner.network;

import com.app.our.foodplanner.model.RootArea;
import com.app.our.foodplanner.model.RootCategory;
import com.app.our.foodplanner.model.RootIngredient;
import com.app.our.foodplanner.model.RootMeal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

    @GET("search.php/meals")
    Call<RootMeal> getMealByName(@Query("s") String Mealname);//https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
    @GET("search.php/meals")
    Call<RootMeal>listAllMealsByFirstLetter(@Query("f") char letter);//www.themealdb.com/api/json/v1/1/search.php?f=a

    @GET("lookup.php/meals")
    Call<RootMeal>lookupFullMealDetailsById(@Query("i") String MealId);//www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("random.php/meals")
    Call<RootMeal>lookupASingleRandomMeal();//www.themealdb.com/api/json/v1/1/random.php
    @GET("list.php/meals")
    Call<RootCategory>listAllCategories_Just_Names(@Query("c") String list);//www.themealdb.com/api/json/v1/1/list.php?c=list
    @GET("list.php/meals")
    Call<RootArea>listAllArea_Just_Names(@Query("a")String list);//www.themealdb.com/api/json/v1/1/list.php?a=list
    @GET("list.php/meals")
    Call<RootIngredient>listAllIngredients_Just_Names(@Query("i")String List);//www.themealdb.com/api/json/v1/1/list.php?i=list
    @GET("filter.php/meals")
    Call<RootMeal>filterByMainIngredient(@Query("i") String ingredient);//www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast
    @GET("filter.php/meals")
    Call<RootMeal>filterByCategory(@Query("c") String category );//www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
    @GET("filter.php/meals")
    Call<RootMeal>filterByArea(@Query("a") String category );//www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
}