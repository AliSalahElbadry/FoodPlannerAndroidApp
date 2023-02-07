package com.app.our.foodplanner.app_vp.view.home;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.tv.TvView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public class HomeFragment extends Fragment  implements HomeFragmentInterface{
MainActivityContainerInterface mainActivityContainerInterface;
  RecyclerView recyclerViewCtegory;
  AdapterHomeCategory adapterHomeCategory;
  RecyclerView recyclerViewMeals;
  AdapterHomeMealCategory homeMeals;
  ImageView randomMealImage;
  TextView textViewTitle;
  TextView textViewCountry;
  View view;
  SearchView searchView;
  CardView randomMeal;
  Meal mealRandom;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;mainActivityContainerInterface=((MainActivityContainer)getActivity());
        randomMealImage=view.findViewById(R.id.imageViewSuggestMeal);
        textViewTitle=view.findViewById(R.id.txtViewTitleHome);
        textViewCountry=view.findViewById(R.id.textViewCountryHome);
        recyclerViewCtegory=view.findViewById(R.id.recyclerViewCategory);
        recyclerViewMeals=view.findViewById(R.id.recyclerViewRandumMeals);
        adapterHomeCategory=new AdapterHomeCategory(view.getContext(),this,new ArrayList<>());
        homeMeals=new AdapterHomeMealCategory(view.getContext(),this,new ArrayList<>());
        recyclerViewMeals.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewCtegory.setLayoutManager(linearLayoutManager);
        recyclerViewCtegory.setAdapter(adapterHomeCategory);
        recyclerViewMeals.setAdapter(homeMeals);
        searchView=view.findViewById(R.id.searchView);
        randomMeal=view.findViewById(R.id.cardViewSuggestMeal);
        randomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityContainerInterface.showMeal(mealRandom,(((BitmapDrawable)randomMealImage.getDrawable()).getBitmap()));
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                homeMeals.notifyDataSetChanged();
                 return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainActivityContainerInterface.getPresenter().searchMealByName(newText);
                return true;
            }
        });

    }

    @Override
    public void showCategories(ArrayList<Category> Res) {
        adapterHomeCategory.setData(Res);
        adapterHomeCategory.notifyDataSetChanged();
    }

    @Override
    public void showRandomMeal(ArrayList<Meal> Res) {
        mainActivityContainerInterface.getPresenter().getRandomMealImage(randomMealImage,view,Res.get(0).getStrMealThumb());
        textViewTitle.setText(Res.get(0).getStrMeal());
        textViewCountry.setText(Res.get(0).getStrArea());
        mealRandom=Res.get(0);
    }

    @Override
    public void showMeals(ArrayList<Meal> Res) {
        homeMeals.setData(Res);
        homeMeals.notifyDataSetChanged();
    }

    @Override
    public MainActivityContainerInterface getConainer() {
        return mainActivityContainerInterface;
    }

}