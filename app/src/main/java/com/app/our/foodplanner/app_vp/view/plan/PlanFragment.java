package com.app.our.foodplanner.app_vp.view.plan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PlanFragment extends Fragment  implements PlanFragmentInterface{

    ArrayList<Meal>mealsBreak;
    ArrayList<Meal>mealsLunch;
    ArrayList<Meal>mealsDinner;
    RecyclerView recyclerViewBreak,recyclerViewLunch,recyclerViewDinner;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewBreak = view.findViewById(R.id.recyclerViewBreakFast);
        recyclerViewLunch = view.findViewById(R.id.recyclerViewLunch);
        recyclerViewDinner = view.findViewById(R.id.recyclerViewDinner);
        tabLayout = view.findViewById(R.id.tabLayoutDaysContainer);
        ((TextView) view.findViewById(R.id.txtViewPlanTime)).setText(plan.getYear() + "/" + plan.getMonth());
        fDay = Integer.parseInt(plan.getWeek());
        abreak = new PlanAdapter(mealsBreak, (MainActivityContainerInterface) getActivity());
        alunch = new PlanAdapter(mealsLunch, (MainActivityContainerInterface) getActivity());
        adinner = new PlanAdapter(mealsDinner, (MainActivityContainerInterface) getActivity());

        recyclerViewBreak.setAdapter(abreak);
        recyclerViewLunch.setAdapter(alunch);
        recyclerViewDinner.setAdapter(adinner);
        recyclerViewBreak.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLunch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDinner.setLayoutManager(new LinearLayoutManager(getContext()));
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
        this.mealsBreak=breakfast;
        this.mealsLunch=lunch;
        this.mealsDinner=dinner;
        abreak.setData(mealsBreak);
        alunch.setData(mealsLunch);
        adinner.setData(mealsDinner);
        abreak.notifyDataSetChanged();
        alunch.notifyDataSetChanged();
        adinner.notifyDataSetChanged();
    }
}