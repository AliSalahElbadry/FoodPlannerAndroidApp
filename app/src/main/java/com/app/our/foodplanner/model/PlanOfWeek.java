package com.app.our.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName="PlanOfWeek")

public class PlanOfWeek {
    public PlanOfWeek(String week, String month, String year) {
        this.week = week;
        this.month = month;
        this.year = year;
    }

    @ColumnInfo(name="idPlan")
    @PrimaryKey(autoGenerate = true)
    private int idPlan;

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @ColumnInfo (name="userId")
    @NonNull
    private String userId;
    @ColumnInfo (name="week")
    private String week;


    @ColumnInfo (name="month")
    private String month;

    @ColumnInfo (name="year")
    private String year;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
