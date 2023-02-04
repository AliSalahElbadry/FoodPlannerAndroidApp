package com.app.our.foodplanner.app_vp.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.network.ConcreteRemoteSource;
import com.app.our.foodplanner.network.NetworkDelegate;
import com.app.our.foodplanner.network.RemoteSource;

import java.util.ArrayList;

public class MainActivityContainer extends AppCompatActivity implements NetworkDelegate {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        }

        @Override
        public void listAllCategories_Just_NamesOnSuccessResults(ArrayList< Category > Res) {

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

        }

        @Override
        public void filterByAreaOnSuccessResults(ArrayList<Meal> Res) {

        }

        @Override
        public void onFailureResults(String msg) {
            Log.i("","FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        }
}
