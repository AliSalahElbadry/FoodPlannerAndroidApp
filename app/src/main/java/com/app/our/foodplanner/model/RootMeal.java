package com.app.our.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootMeal {
    @SerializedName("meals")
    public ArrayList<Meal> meals;
}
