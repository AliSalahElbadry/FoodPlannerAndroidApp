package com.app.our.foodplanner.model;

import java.util.HashMap;

public class plan {

    private int idPlan;
    private String weekOfPlan;
    private String planName;

    public plan(int idPlan, String weekOfPlan, String planName) {
        this.idPlan = idPlan;
        this.weekOfPlan = weekOfPlan;
        this.planName = planName;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getWeekOfPlan() {
        return weekOfPlan;
    }

    public void setWeekOfPlan(String weekOfPlan) {
        this.weekOfPlan = weekOfPlan;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
// HashMap<Meal,String> melesOfWeek;
}
