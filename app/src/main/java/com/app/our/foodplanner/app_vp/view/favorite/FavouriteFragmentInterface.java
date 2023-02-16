package com.app.our.foodplanner.app_vp.view.favorite;

import android.util.Log;

import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface FavouriteFragmentInterface {


    public void showData(List<Meal> meal);
    public MainActivityContainerInterface getConainer();
    public void delete(Meal meal);

}
