package com.app.our.foodplanner.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository implements RepositoryInterface {

    private static  Repository repo;
    private  Repository()
    {

    }
    public static   Repository getInstance()
    {
        if(repo==null)
        {

        }
        return  repo;
    }
}