package com.app.our.foodplanner.app_vp.view.filter;

import androidx.annotation.Nullable;

import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface FilterFragmentInterface {

    public void show_FilterData(@Nullable ArrayList<Ingredient> ingredients,
                                @Nullable ArrayList<Area>areas,int type);
    //type =0 -> filter by ingredients ,type =1 -> filter by Areas
    public void showData(List<Meal> meal);
    public void onClickDelete(Boolean isFav,String meal);
    public MainActivityContainerInterface getConainer();

    void showArea(ArrayList<Area> res);

    void showFilterByArea(ArrayList<Meal> res);

    void showIngradient(ArrayList<Ingredient> mealsAfter);
}
