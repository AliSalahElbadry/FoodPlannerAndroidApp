package com.app.our.foodplanner.app_vp.view.favorite;

import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface FavouriteFragmentInterface {


    public void showData(List<Meal> meal);
    public void onClickDelete(Boolean isFav,String meal);
    public MainActivityContainerInterface getConainer();

}
