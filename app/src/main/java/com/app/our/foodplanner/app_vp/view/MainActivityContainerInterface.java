package com.app.our.foodplanner.app_vp.view;

import com.app.our.foodplanner.app_vp.view.presenter.Presenter;

public interface MainActivityContainerInterface {
    public Presenter getPresenter();
    public void showFavPage();

    public void showProfilePage();

    public void showPlansPage();
}
