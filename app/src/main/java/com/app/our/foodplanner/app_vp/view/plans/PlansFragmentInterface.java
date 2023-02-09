package com.app.our.foodplanner.app_vp.view.plans;

import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;
import java.util.List;

public interface PlansFragmentInterface {
    public void updateList_AddPlan(PlanOfWeek plan);
    public void updateListRemPlan(PlanOfWeek plan);
    public void setTarget(String target);
    public void setPlansData(List<PlanOfWeek> plans);
    public void showAddMeal(PlanOfWeek plan);
    public  void showAddPlan(PlanOfWeek plan);

}
