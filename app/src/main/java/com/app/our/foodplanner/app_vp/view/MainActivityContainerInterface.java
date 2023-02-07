package com.app.our.foodplanner.app_vp.view;

import android.graphics.Bitmap;

import com.app.our.foodplanner.app_vp.view.presenter.Presenter;
import com.app.our.foodplanner.model.Meal;

public interface MainActivityContainerInterface {
    public Presenter getPresenter();
    public void showFavPage();

    public void showProfilePage();

    public void showPlansPage();
    public  void showLogIn();
    public void showSignUp();
    public void showHome();
    public void showMeal(Meal meal, Bitmap image);// show meal page

}
