package com.app.our.foodplanner.app_vp.view.home;

import android.graphics.Bitmap;

import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public interface HomeFragmentInterface {

    public void showCategories(ArrayList<Category> Res);
    public boolean getshowFirst();
    public void setShowFirst(boolean f);

    public void showRandomMeal(ArrayList<Meal> Res);

    public void showMeals(ArrayList<Meal>Res);
    public MainActivityContainerInterface getConainer();

}
