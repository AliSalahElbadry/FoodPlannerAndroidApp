package com.app.our.foodplanner.app_vp.view;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
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
import com.app.our.foodplanner.app_vp.view.login.LogInFragmentInterface;
import com.app.our.foodplanner.app_vp.view.meal.MealFragment;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragment;
import com.app.our.foodplanner.app_vp.view.presenter.Presenter;
import com.app.our.foodplanner.app_vp.view.profile.ProfileFragment;
import com.app.our.foodplanner.app_vp.view.signup.SignupFragment;
import com.app.our.foodplanner.app_vp.view.signup_login.Signup_Login_Fragment;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;
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

    LogInFragment logInFragment;

   public BottomNavigationView navigationView;
   int index=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
             getSupportActionBar().hide();
         homeFragment=new HomeFragment();
         logInFragment=new LogInFragment();
         profileFragment=new ProfileFragment();
         plansFragment=new PlansFragment();
         favoriteFragment=new FavoriteFragment();
         manager=getSupportFragmentManager();
         presenter=new Presenter(getApplicationContext());
         presenter.setHomeFragment(homeFragment);
         intiateUi(savedInstanceState);
         navigationView=findViewById(R.id.bottonNavigationView);


        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 item.setChecked(true);
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
        navigationView.setOnItemReselectedListener(l->{});
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        manager.saveFragmentInstanceState(manager.getFragments().get(0));
        super.onSaveInstanceState(outState);
    }

    private void intiateUi(Bundle s)
    {
        if(s==null) {
            showHome();
        }else{
            int size=0;

            if(manager.getFragments().get(0) instanceof HomeFragment) {
                homeFragment=(HomeFragment) manager.getFragments().get(size);
                presenter.setHomeFragment(homeFragment);
                showHome();
            }else if(manager.getFragments().get(size) instanceof ProfileFragment)
            {
                profileFragment=(ProfileFragment) manager.getFragments().get(size);
                showProfilePage();
            }
            else if(manager.getFragments().get(size) instanceof FavoriteFragment)
            {
                favoriteFragment=(FavoriteFragment) manager.getFragments().get(size);
                showFavPage();
            }
            else if(manager.getFragments().get(size) instanceof PlansFragment)
            {
                plansFragment=(PlansFragment) manager.getFragments().get(size);
                showPlansPage();
            }
            else if(manager.getFragments().get(size) instanceof Signup_Login_Fragment)
            {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, manager.getFragments().get(size))
                           .commit();
            }else if(manager.getFragments().get(size) instanceof SignupFragment)
            {
                showSignUp();
            } else if (manager.getFragments().get(size) instanceof LogInFragment) {
                showLogIn();
            }

        }
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
                transaction.replace(R.id.nav_host_fragment, profileFragment)
                           .commit();
            }
        }else{
            Signup_Login_Fragment fragment=new Signup_Login_Fragment();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment,fragment)
                       .commit();
        }
    }

    @Override
    public void showPlansPage() {
        if(presenter.isLogedIn()){
            presenter.setPlansInterface(plansFragment);
            presenter.getAllPlans();
            if(manager.getFragments().get(manager.getFragments().size()-1)!=plansFragment) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, plansFragment)
                           .commit();
            }
        }else{
            Signup_Login_Fragment fragment=new Signup_Login_Fragment();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment,fragment)
                       .commit();
        }
    }

    @Override
    public void showLogIn() {

        LogInFragment fragment=new LogInFragment();
        presenter.setLogInFragmentInterface(fragment);
        transaction=manager.beginTransaction();

        transaction.replace(R.id.nav_host_fragment,fragment)
                   .commit();

    }

    @Override
    public void showSignUp() {
        SignupFragment fragment=new SignupFragment();
        presenter.setSignupFragmentInterface(fragment);
        transaction=manager.beginTransaction();

        transaction.replace(R.id.nav_host_fragment,fragment)
                   .commit();

    }

    @Override
    public void showHome() {

               presenter.getAllCategories();
               presenter.getRandomMeal();
               transaction = manager.beginTransaction();
               transaction.replace(R.id.nav_host_fragment, homeFragment)
                       .commit();
    }

    @Override
    public void showMeal(Meal meal, Bitmap image) {
        if(image==null)
        {
            Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
        }else {
            mealFragment = new MealFragment();
            mealFragment.setCancelable(true);
            manager = getSupportFragmentManager();
            mealFragment.show(getSupportFragmentManager(), "Meal");
            mealFragment.showMeal(image);
            presenter.getMealByName(meal.getStrMeal());
            presenter.setMealFragmentInterface(mealFragment);
        }
    }

    @Override
    public void showPlansAddMeal() {
        navigationView.setSelectedItemId(R.id.planMenu);
        plansFragment.setTarget("AddMeal");
    }

    @Override
    public void showPlanPage(PlanOfWeek plan) {
        //tell presenter to get all emeals in plan
    }

    @Override
    public void onBackPressed() {
        if(index==0)
        {
            Toast.makeText(this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
            index++;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    index=0;
                }
            },200);
        }else
        if(index==1)
            super.onBackPressed();
    }

}
