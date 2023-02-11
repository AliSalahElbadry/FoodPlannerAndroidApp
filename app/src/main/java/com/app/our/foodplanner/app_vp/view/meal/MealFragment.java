package com.app.our.foodplanner.app_vp.view.meal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public class MealFragment extends DialogFragment implements MealFragmentInterface{

    TextView textViewMealName;
    TextView textViewMealCategoryCountry;
    ImageView imageViewMealMeal;
    RecyclerView mealIngredients;
    Meal meal;
    Bitmap bitmap;
    TextView mealSteps;
    View view;
    ImageButton btnAddFav;
    ImageButton btnAddToPlan;
    ImageButton imageButtonVideoShow;
    PresenterInterface presenterInterface;
    ArrayList<String>ingHolder;
    int mode=0;
    public MealFragment() {
        // Required empty public constructor
    }
    public MealFragment(Meal meal,ArrayList<String>ingredients)
    {
        this.meal=meal;
        if(ingredients!=null) {
            ingHolder = ingredients;
        }else{
            ingHolder=new ArrayList<>();
        }
        this.bitmap=meal.getImageBitmap();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meal_data_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();
        imageViewMealMeal=view.findViewById(R.id.imageViewMealMeal);
        this.view=view;
        imageViewMealMeal.setImageBitmap(bitmap);
        btnAddFav=view.findViewById(R.id.btnAddToFav);
        btnAddToPlan=view.findViewById(R.id.btnAddToPlan);
        imageButtonVideoShow=view.findViewById(R.id.imageButtonVideoShow);
        if(presenterInterface.isLogedIn()&&mode>0)
        {
            btnAddFav.setVisibility(View.GONE);
            btnAddToPlan.setVisibility(View.GONE);
            imageButtonVideoShow.setVisibility(View.GONE);
            textViewMealName=view.findViewById(R.id.textViewMealName);
            textViewMealCategoryCountry=view.findViewById(R.id.textViewMealCategoryCountry);
            mealSteps=view.findViewById(R.id.textViewMealSteps);
            mealIngredients=view.findViewById(R.id.recyclerViewMealIngredents);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mealIngredients.setLayoutManager(linearLayoutManager);
            MealIngredientsAdapter adapter=new MealIngredientsAdapter(view.getContext());
            adapter.setData(ingHolder);
            mealIngredients.setAdapter(adapter);
            textViewMealName.setText(meal.getStrMeal());
            textViewMealCategoryCountry.setText(meal.getStrCategory()+" , "+meal.getStrArea());
            mealSteps.setText("\n"+meal.getStrInstructions()+"\n\n\n");
        }else {
            btnAddFav.setOnClickListener(l -> {
                meal.setImageBitmap(bitmap);
                presenterInterface.addToFav(meal);
            });
            btnAddToPlan.setOnClickListener(l -> {
                meal.setImageBitmap(bitmap);
                presenterInterface.setTargetAddMealToPlan(meal);
                ((MainActivityContainer) getActivity()).showPlansAddMeal();
                dismiss();
            });
            imageButtonVideoShow.setOnClickListener(l -> {
                ((MainActivityContainer) getActivity()).showVideo(meal.getStrYoutube());
            });
        }
    }
    public void setMode(int mode)
    {
        this.mode=mode;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void showMeal(Bitmap bitmap) {
        this.bitmap=bitmap;
    }

    @Override
    public void setMealData(Meal meal, ArrayList<String> res) {
        this.meal=meal;
        textViewMealName=view.findViewById(R.id.textViewMealName);
        textViewMealCategoryCountry=view.findViewById(R.id.textViewMealCategoryCountry);
        mealSteps=view.findViewById(R.id.textViewMealSteps);
        mealIngredients=view.findViewById(R.id.recyclerViewMealIngredents);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mealIngredients.setLayoutManager(linearLayoutManager);
        MealIngredientsAdapter adapter=new MealIngredientsAdapter(view.getContext());
        adapter.setData(res);
        mealIngredients.setAdapter(adapter);
        textViewMealName.setText(meal.getStrMeal());
        textViewMealCategoryCountry.setText(meal.getStrCategory()+" , "+meal.getStrArea());
        mealSteps.setText(meal.getStrInstructions()+"\n\n\n");
    }

    @Override
    public void setAddFavRes(boolean isSet) {
        if(isSet) {
            btnAddFav.setBackgroundResource(R.drawable.baseline_favorite_24);
            Toast.makeText(getContext(), "Added To Favorite", Toast.LENGTH_SHORT).show();
        }
    }
}