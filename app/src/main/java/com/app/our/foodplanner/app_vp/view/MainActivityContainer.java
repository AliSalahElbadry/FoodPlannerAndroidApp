package com.app.our.foodplanner.app_vp.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
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
import androidx.fragment.app.FragmentContainerView;
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
import com.app.our.foodplanner.app_vp.view.plan.PlanFragment;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragment;
import com.app.our.foodplanner.app_vp.view.presenter.Presenter;
import com.app.our.foodplanner.app_vp.view.profile.ProfileFragment;
import com.app.our.foodplanner.app_vp.view.search.FilterFragment;
import com.app.our.foodplanner.app_vp.view.search.FilterFragmentInterface;
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
   public BottomNavigationView navigationView;
    FilterFragment filterFragment;
   int index=0;String planTarget="showPlan";


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
         presenter=new Presenter(getApplicationContext());
         presenter.setHomeFragment(homeFragment);
         intiateUi(savedInstanceState);
         navigationView=findViewById(R.id.bottonNavigationView);
         presenter.setProfileFragmentInterface(profileFragment);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 item.setChecked(true);

                 if(item.getItemId()==R.id.homeMenu)
                 {
                     plansFragment.setTarget(planTarget);
                     showHome();
                 }else if(item.getItemId()==R.id.profileMenu)
                 {
                     plansFragment.setTarget(planTarget);
                     showProfilePage();
                 }
                 else if(item.getItemId()==R.id.favouriteMenu)
                 {
                     plansFragment.setTarget(planTarget);
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
               presenter.getAllFav();
               presenter.setfavouriteFragmentInterface(favoriteFragment);
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
        Log.e("",""+presenter.isLogedIn());
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
        if(checkConnectionState()) {
            LogInFragment fragment = new LogInFragment();
            presenter.setLogInFragmentInterface(fragment);
            transaction = manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment, fragment)
                    .commit();
        }else{
            Toast.makeText(this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSignUp() {
        if(checkConnectionState()) {
            SignupFragment fragment = new SignupFragment();
            presenter.setSignupFragmentInterface(fragment);
            transaction = manager.beginTransaction();

            transaction.replace(R.id.nav_host_fragment, fragment)
                    .commit();
        }else{
            Toast.makeText(this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showHome() {
          if(checkConnectionState()) {
                  presenter.getAllCategories();
                  presenter.getRandomMeal();
          }else{
              Toast.makeText(this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
          }
               transaction = manager.beginTransaction();
               transaction.replace(R.id.nav_host_fragment, homeFragment)
                       .commit();
    }

    @Override
    public void showMeal(Meal meal, Bitmap image,int mode) {
        if(mode==0) {
            if (image == null) {
                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
            } else {
                mealFragment = new MealFragment();
                mealFragment.setMode(mode);
                mealFragment.setCancelable(true);
                manager = getSupportFragmentManager();
                mealFragment.show(getSupportFragmentManager(), "Meal");
                mealFragment.showMeal(image);
                presenter.getMealByName(meal.getStrMeal());
                presenter.setMealFragmentInterface(mealFragment);
            }
        }else if(mode>0)
        {
            mealFragment=new MealFragment(meal,presenter.getIngredinetsInMeal(meal));
            mealFragment.setMode(mode);
            mealFragment.setCancelable(true);
            manager = getSupportFragmentManager();
            mealFragment.show(getSupportFragmentManager(), "Meal");
        }
    }
    public boolean checkConnectionState() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
    }
    @Override
    public void showPlansAddMeal() {
        navigationView.setSelectedItemId(R.id.planMenu);

        plansFragment.setTarget(new String("AddMeal"));
    }

    @Override
    public void ReStart() {
        presenter=new Presenter(this);
        presenter.setHomeFragment(homeFragment);
        presenter.setuData(new String[]{"","",""});
        plansFragment=new PlansFragment();
        presenter.setPlansInterface(plansFragment);
        favoriteFragment=new FavoriteFragment();
        presenter.setfavouriteFragmentInterface(favoriteFragment);
        profileFragment=new ProfileFragment();
        presenter.setProfileFragmentInterface(profileFragment);
        presenter.setIsLogedIn(false);

        navigationView.setSelectedItemId(R.id.homeMenu);
    }

    @Override
    public void showPlanPage(PlanOfWeek plan) {
        PlanFragment planFragment=new PlanFragment(plan);
        presenter.setPlanInterface(planFragment);
        presenter.getMealsInPlan(plan);
        transaction=manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment,planFragment);
        transaction.commit();
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
    public void showVideo(String url)
    {

    }

    @Override
    public void showFilter() {
        filterFragment = new FilterFragment();
        presenter.setFilterFragmentInterface(filterFragment);
        transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, filterFragment)
                .commit();

    }


}
