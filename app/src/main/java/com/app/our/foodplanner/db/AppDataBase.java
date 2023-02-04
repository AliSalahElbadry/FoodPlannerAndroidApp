package com.app.our.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

@Database(entities = {Meal.class, PlanOfWeek.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance=null;
    public abstract MealDAO MealDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"FoodPlanner").build();
        }
        return instance;
    }


}
