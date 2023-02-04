package com.app.our.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootCategory {
    @SerializedName("meals")
    public ArrayList<Category> categories;
}
