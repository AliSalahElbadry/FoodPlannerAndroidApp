package com.app.our.foodplanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {
    private List<Meal>meals;
    private List<PlanOfWeek> plans;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<PlanOfWeek> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanOfWeek> plans) {
        this.plans = plans;
    }


}
