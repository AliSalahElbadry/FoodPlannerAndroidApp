package com.app.our.foodplanner.app_vp.view.plan;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlanFragment extends Fragment  implements PlanFragmentInterface{

    ArrayList<Meal>mealsBreak;
    ArrayList<Meal>mealsLunch;
    ArrayList<Meal>mealsDinner;
    RecyclerView recyclerViewBreak,recyclerViewLunch,recyclerViewDinner;
    ImageView imageViewBackPlan;
    PlanAdapter abreak,alunch,adinner;
    PlanOfWeek plan;
    int fDay;
    TabLayout tabLayout;
    public PlanFragment(PlanOfWeek plan) {
        // Required empty public constructor
        this.plan=plan;
        mealsBreak=new ArrayList<>();
        mealsLunch=new ArrayList<>();
        mealsDinner=new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.plan_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewBackPlan=view.findViewById(R.id.imageViewBackPlan);
        imageViewBackPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBack();
            }
        });
        recyclerViewBreak = view.findViewById(R.id.recyclerViewBreakFast);
        recyclerViewLunch = view.findViewById(R.id.recyclerViewLunch);
        recyclerViewDinner = view.findViewById(R.id.recyclerViewDinner);
        tabLayout = view.findViewById(R.id.tabLayoutDaysContainer);
        ((TextView) view.findViewById(R.id.txtViewPlanTime)).setText(plan.getYear() + "/" + plan.getMonth()+"/"+plan.getWeek());
        fDay = Integer.parseInt(plan.getWeek());
        abreak = new PlanAdapter(mealsBreak, (MainActivityContainerInterface) getActivity(),this);
        alunch = new PlanAdapter(mealsLunch, (MainActivityContainerInterface) getActivity(),this);
        adinner = new PlanAdapter(mealsDinner, (MainActivityContainerInterface) getActivity(),this);

        recyclerViewBreak.setAdapter(abreak);
        recyclerViewLunch.setAdapter(alunch);
        recyclerViewDinner.setAdapter(adinner);
        recyclerViewBreak.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLunch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDinner.setLayoutManager(new LinearLayoutManager(getContext()));
        tabLayout.getTabAt(0).setText(""+validateDay(fDay,Integer.parseInt(plan.getMonth())));
        tabLayout.getTabAt(1).setText(""+validateDay(fDay+1,Integer.parseInt(plan.getMonth())));

        tabLayout.getTabAt(2).setText(""+validateDay(fDay+2,Integer.parseInt(plan.getMonth())));
        tabLayout.getTabAt(3).setText(""+validateDay(fDay+3,Integer.parseInt(plan.getMonth())));

        tabLayout.getTabAt(4).setText(""+validateDay(fDay+4,Integer.parseInt(plan.getMonth())));
        tabLayout.getTabAt(5).setText(""+validateDay(fDay+5,Integer.parseInt(plan.getMonth())));

        tabLayout.getTabAt(6).setText(""+validateDay(fDay+6,Integer.parseInt(plan.getMonth())));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((MainActivityContainerInterface) getActivity()).getPresenter().sendFirstDayInWeekMeals("" + (fDay + tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public void setData(ArrayList<Meal> breakfast, ArrayList<Meal> lunch, ArrayList<Meal> dinner) {

        if(breakfast!=null) {
            this.mealsBreak = breakfast;
            abreak.setData(mealsBreak);
            abreak.notifyDataSetChanged();
        }
        if(lunch!=null) {
            this.mealsLunch = lunch;
            alunch.setData(mealsLunch);
            alunch.notifyDataSetChanged();
        }
        if(dinner!=null) {
            this.mealsDinner = dinner;
            adinner.setData(mealsDinner);
            adinner.notifyDataSetChanged();
        }
    }
    public int validateDay(int d,int month)
    {

        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
        {
         if(d>31)
         {
             d=d-31;
         }
        }else if(month==4||month==6||month==11||month==9)
        {
            if(d>30)
            {
                d-=30;
            }
        }else if(month==2)
        {
            if(d>28)
            {
                d-=28;
            }
        }
        return  d;
    }
    @Override
    public void deleteMealInPlan(Meal meal) {
        ((MainActivityContainerInterface)getActivity()).getPresenter().deleteMealInPlan(meal.getIdMeal(),meal.getMeal_Day(),meal.getMeal_Time(),plan);
    }

    @Override
    public void onClickBack() {
        ((MainActivityContainer)getActivity()).showPlansPage();
    }
}