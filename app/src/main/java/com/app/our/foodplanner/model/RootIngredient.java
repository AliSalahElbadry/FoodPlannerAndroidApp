package com.app.our.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootIngredient {
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @SerializedName("meals")
    public ArrayList<Ingredient> ingredients;
}
