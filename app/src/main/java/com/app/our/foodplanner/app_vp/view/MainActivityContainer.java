package com.app.our.foodplanner.app_vp.view;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.favorite.FavoriteFragment;
import com.app.our.foodplanner.app_vp.view.home.HomeFragment;
import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.app_vp.view.login.LogInFragment;
import com.app.our.foodplanner.app_vp.view.meal.MealFragment;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragment;
import com.app.our.foodplanner.app_vp.view.presenter.Presenter;
import com.app.our.foodplanner.app_vp.view.profile.ProfileFragment;
import com.app.our.foodplanner.app_vp.view.signup.SignupFragment;
import com.app.our.foodplanner.app_vp.view.signup_login.Signup_Login_Fragment;
import com.app.our.foodplanner.model.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityContainer extends AppCompatActivity implements MainActivityContainerInterface {

    Presenter presenter;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;
    PlansFragment plansFragment;
    FavoriteFragment favoriteFragment;
    MealFragment mealFragment;
    FragmentManager manager;
    FragmentTransaction transaction;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
             getSupportActionBar().hide();
         homeFragment=new HomeFragment();
         profileFragment=new ProfileFragment();
         plansFragment=new PlansFragment();
         favoriteFragment=new FavoriteFragment();
         manager=getSupportFragmentManager();
         navigationView=findViewById(R.id.bottonNavigationView);
         transaction=manager.beginTransaction();
         transaction.add(R.id.nav_host_fragment,homeFragment);
         transaction.commit();
        presenter=new Presenter(this);
        presenter.setHomeFragment(homeFragment);
        presenter.getAllCategories();
        presenter.getRandomMeal();
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 if(item.getItemId()==R.id.homeMenu)
                 {
                     showHome();
                 }else if(item.getItemId()==R.id.profileMenu)
                 {
                     showProfilePage();
                 }
                 else if(item.getItemId()==R.id.favouriteMenu)
                 {
                     showFavPage();
                 }else if(item.getItemId()==R.id.planMenu)
                 {
                     showPlansPage();
                 }
                return false;
            }
        });
    }
    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showFavPage() {
       if(presenter.isLogedIn()){
           if(manager.getFragments().get(manager.getFragments().size()-1)!=favoriteFragment) {
               transaction = manager.beginTransaction();
               transaction.replace(R.id.nav_host_fragment, favoriteFragment);
               transaction.commit();
           }
       }else{
            Signup_Login_Fragment fragment=new Signup_Login_Fragment();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment,fragment);
            transaction.commit();
       }
    }

    @Override
    public void showProfilePage() {
        if(presenter.isLogedIn()){
            if(manager.getFragments().get(manager.getFragments().size()-1)!=profileFragment) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, profileFragment);
                transaction.commit();
            }
        }else{
            Signup_Login_Fragment fragment=new Signup_Login_Fragment();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment,fragment);
            transaction.commit();
        }
    }

    @Override
    public void showPlansPage() {
        if(presenter.isLogedIn()){
            if(manager.getFragments().get(manager.getFragments().size()-1)!=plansFragment) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, plansFragment);
                transaction.commit();
            }
        }else{
            Signup_Login_Fragment fragment=new Signup_Login_Fragment();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment,fragment);
            transaction.commit();
        }
    }

    @Override
    public void showLogIn() {
        LogInFragment fragment=new LogInFragment();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment,fragment);
        transaction.commit();
    }

    @Override
    public void showSignUp() {
        SignupFragment fragment=new SignupFragment();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment,fragment);
        transaction.commit();
    }

    @Override
    public void showHome() {
        if(manager.getFragments().get(manager.getFragments().size()-1)!=homeFragment) {
            presenter.getAllCategories();
            presenter.getRandomMeal();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment, homeFragment);
            transaction.commit();
        }
    }

    @Override
    public void showMeal(Meal meal, Bitmap image) {
        mealFragment=new MealFragment();
        mealFragment.setCancelable(true);
        manager=getSupportFragmentManager();
        mealFragment.show(getSupportFragmentManager(),"Meal");
        mealFragment.showMeal(image);
        presenter.getMealByName(meal.getStrMeal());
        presenter.setMealFragmentInterface(mealFragment);
    }
}
