package com.app.our.foodplanner.app_vp.view.search;

import androidx.annotation.Nullable;

import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Ingredient;

import java.util.ArrayList;

public interface FilterFragmentInterface {

    public void show_FilterData(@Nullable ArrayList<Ingredient> ingredients,
                                @Nullable ArrayList<Area>areas,int type);
    //type =0 -> filter by ingredients ,type =1 -> filter by Areas

}
