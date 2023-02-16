package com.app.our.foodplanner.app_vp.view.meal;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
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
    PresenterInterface presenterInterface;
    ArrayList<String>ingHolder;
    ImageButton backBtn,addToCalBtn;
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
    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int)(size.x*0.959),(int)(size.y*0.9));
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(R.drawable.cat_bcak_item);
        super.onResume();
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
        backBtn=view.findViewById(R.id.imageButtonbackBtn);
        addToCalBtn=view.findViewById(R.id.imageButtonaddToCalender);
        if(presenterInterface.isLogedIn()&&mode>0)
        {
            btnAddFav.setVisibility(View.GONE);
            btnAddToPlan.setVisibility(View.GONE);
            addToCalBtn.setVisibility(View.GONE);
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
            mealSteps.setText(meal.getStrInstructions());
        }else {
            btnAddFav.setOnClickListener(l -> {
                if(presenterInterface.isLogedIn()) {
                    meal.setImageBitmap(bitmap);
                    presenterInterface.addToFav(meal);
                }else
                {

                    Toast.makeText(getContext(),"Please Login First ",Toast.LENGTH_SHORT).show();
                }
            });
            btnAddToPlan.setOnClickListener(l -> {
                meal.setImageBitmap(bitmap);
                presenterInterface.setTargetAddMealToPlan(meal);
                ((MainActivityContainer) getActivity()).showPlansAddMeal();
                dismiss();
            });
            addToCalBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE,"Meal "+meal.getStrMeal());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION,"Food Planner Meal");
                    intent.putExtra(CalendarContract.Events.DISPLAY_COLOR, Color.GREEN);
                    intent.putExtra(CalendarContract.Events.ALL_DAY,true);
                    try {
                        startActivity(intent);
                        Toast.makeText(getContext(), "Moving You To Calender", Toast.LENGTH_SHORT).show();
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(getContext(), " Cannot found Calender", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        backBtn.setOnClickListener(l->{
            dismiss();
        });
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