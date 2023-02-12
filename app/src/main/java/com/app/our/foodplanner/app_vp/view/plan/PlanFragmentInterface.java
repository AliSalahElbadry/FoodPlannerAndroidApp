package com.app.our.foodplanner.app_vp.view.plan;

import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public interface PlanFragmentInterface {

    public void setData(ArrayList<Meal>breakfast,ArrayList<Meal>lunch,ArrayList<Meal>dinner);
    public void deleteMealInPlan(Meal meal);
    public void onClickBack();
}
