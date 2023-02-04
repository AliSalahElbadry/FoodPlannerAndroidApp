package com.app.our.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootIngredient {
    @SerializedName("meals")
    public ArrayList<Ingredient> ingredients;
}
