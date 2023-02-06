package com.app.our.foodplanner.app_vp.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeCategory;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeMealCategory;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.Repository;
import com.app.our.foodplanner.network.ConcreteRemoteSource;
import com.app.our.foodplanner.network.NetworkDelegate;
import com.app.our.foodplanner.network.RemoteSource;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivityContainer extends AppCompatActivity implements NetworkDelegate {

    Repository repository;
    AdapterHomeMealCategory adapterHomeMealCategory;
    AdapterHomeCategory adapterHomeCategory;
    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewCategoryMeal;

    ImageView imageView;
    TextView txtNameMeal,txtNameArea;

//    List<Category> categoryMeal ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository=Repository.getInstance(this);
        repository.enqueueCallListAllCategories_Just_Names(this,this);
        repository.enqueueCallLookupASingleRandomMeal(this,this);
        recyclerViewCategory=findViewById(R.id.recyclerViewCategory);
        recyclerViewCategoryMeal=findViewById(R.id.recyclerViewRandumMeals);

        imageView=findViewById(R.id.imageViewSuggestMeal);
        txtNameMeal=findViewById(R.id.txtViewTitleHome);
        txtNameArea=findViewById(R.id.textViewCountryHome);
        ////////////////////

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(layoutManager);

        adapterHomeCategory=new AdapterHomeCategory(this, new ArrayList<>());
        recyclerViewCategory.setAdapter(adapterHomeCategory);
        recyclerViewCategory.setAdapter(adapterHomeCategory);

        /////////////////////

//        LinearLayoutManager layoutManagerMeal=new LinearLayoutManager(this);
//        layoutManagerMeal.setOrientation(RecyclerView.VERTICAL);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewCategoryMeal.setLayoutManager(gridLayoutManager);

        adapterHomeMealCategory=new AdapterHomeMealCategory(this, new ArrayList<>());
        recyclerViewCategoryMeal.setAdapter(adapterHomeMealCategory);



    }

        @Override
        public void getMealByNameOnSuccessResults(ArrayList< Meal > Res) {
            Log.i("","getpyName 0000000000000000000000000000000000000000000000000");

        }

        @Override
        public void listAllMealsByFirstLetterOnSuccessResults(ArrayList<Meal> Res) {

        }

        @Override
        public void lookupFullMealDetailsByIdOnSuccessResults(ArrayList<Meal> Res) {

        }

        @Override
        public void lookupASingleRandomMealOnSuccessResults(ArrayList<Meal> Res) {

                   // imageView.setImageResource(R.drawable.picfood);
                    txtNameMeal.setText(Res.get(0).getStrMeal());
                    txtNameArea.setText(Res.get(0).getStrArea());
            Glide.with(this).load(Res.get(0).getStrMealThumb())
                    .into(imageView);


        }

        @Override
        public void listAllCategories_Just_NamesOnSuccessResults(ArrayList< Category > Res) {

                adapterHomeCategory.setData(Res);
                adapterHomeCategory.notifyDataSetChanged();
                repository.enqueueCallFilterByCategory(this,this,Res.get(0).strCategory);

            Log.i(TAG, "////listAllCategories_Just_NamesOnSuccessResults: "+Res.get(0).strCategory);
        }

        @Override
        public void listAllArea_Just_NamesOnSuccessResults(ArrayList< Area > Res) {

        }

        @Override
        public void listAllIngredients_Just_NamesOnSuccessResults(ArrayList< Ingredient > Res) {

        }

        @Override
        public void filterByMainIngredientOnSuccessResults(ArrayList<Meal> Res) {

        }

        @Override
        public void filterByCategoryOnSuccessResults(ArrayList<Meal> Res) {
                    adapterHomeMealCategory.setData(Res);
                    adapterHomeMealCategory.notifyDataSetChanged();
            Log.i(TAG, "filterByCategoryOnSuccessResults: "+Res.get(0).getStrArea());


        }

        @Override
        public void filterByAreaOnSuccessResults(ArrayList<Meal> Res) {

        }

        @Override
        public void onFailureResults(String msg) {
            Log.i("","FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        }
}
