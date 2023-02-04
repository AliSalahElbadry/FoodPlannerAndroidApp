package com.app.our.foodplanner.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName="PlanOfWeek")

public class PlanOfWeek {
    @ColumnInfo(name="idPlan")
    @PrimaryKey
    private int idPlan;
    @ColumnInfo (name="week")
    private String week;//(year,month,week)
    public PlanOfWeek(int idPlan, String week) {
        this.idPlan = idPlan;
        this.week = week;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getweek() {
        return week;
    }

    public void setweek(String week) {
        this.week = week;
    }
}
