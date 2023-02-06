package com.app.our.foodplanner.app_vp.view.plans;

import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;

public interface PlansFragmentInterface {
    public void showPlans(ArrayList<PlanOfWeek>plans,int mode);// 0:means normal show , 1 means add meal to plan show
    public void updateList_AddPlan(PlanOfWeek plan);

}
