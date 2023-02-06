package com.app.our.foodplanner.app_vp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.home.HomeFragment;
import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.app_vp.view.presenter.Presenter;

public class MainActivityContainer extends AppCompatActivity implements MainActivityContainerInterface {

    Presenter presenter;
    NavController navController;
    HomeFragment homeFragment;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         homeFragment=new HomeFragment();
         manager=getSupportFragmentManager();
         transaction=manager.beginTransaction();
         transaction.add(R.id.nav_host_fragment,homeFragment,"Home");
         transaction.commit();
        presenter=new Presenter(this);
        presenter.setHomeFragment(homeFragment);
        presenter.getAllCategories();
        presenter.getRandomMeal();

    }
    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showFavPage() {

    }

    @Override
    public void showProfilePage() {

    }

    @Override
    public void showPlansPage() {

    }

}
