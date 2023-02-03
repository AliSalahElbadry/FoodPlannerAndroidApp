package com.app.our.foodplanner.model;

public class PlanOfWeek {

    private int idPlan;
    private String idMeal;

    public PlanOfWeek(int idPlan, String idMeal) {
        this.idPlan = idPlan;
        this.idMeal = idMeal;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
}
