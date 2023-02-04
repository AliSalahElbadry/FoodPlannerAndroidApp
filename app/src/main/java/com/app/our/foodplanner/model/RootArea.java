package com.app.our.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootArea {
    @SerializedName("meals")
    public ArrayList<Area> areas;

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }
}
