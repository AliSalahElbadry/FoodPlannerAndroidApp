package com.app.our.foodplanner.app_vp.view;

import android.graphics.Bitmap;

import com.app.our.foodplanner.app_vp.view.presenter.Presenter;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

public interface MainActivityContainerInterface {
    public Presenter getPresenter();
    public void showFavPage();

    public void showProfilePage();

    public void showPlansPage();
    public  void showLogIn();
    public void showSignUp();
    public void showHome();
    public void showMeal(Meal meal, Bitmap image,int mode);// show meal page
    public void showPlansAddMeal();
    public void ReStart();
    public boolean checkConnectionState();
    public void showPlanPage(PlanOfWeek plan);
    public void showFilter();
    public void closeFilter();

}
