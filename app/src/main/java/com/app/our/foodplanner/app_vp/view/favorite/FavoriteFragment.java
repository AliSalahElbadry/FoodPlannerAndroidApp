package com.app.our.foodplanner.app_vp.view.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeCategory;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment implements FavouriteFragmentInterface{

    MainActivityContainerInterface mainActivityContainerInterface;
    RecyclerView recyclerViewFavouriteList;
    View view;
    AdapterFavouriteList adapterFavouriteList;

    PresenterInterface presenterInterface;
    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favourite_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view=view;
       // mainActivityContainerInterface=((MainActivityContainer)getActivity());
        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();
        recyclerViewFavouriteList=view.findViewById(R.id.recyclerViewFavouriteList);
        adapterFavouriteList=new AdapterFavouriteList(view.getContext(),this,new ArrayList<>());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFavouriteList.setLayoutManager(linearLayoutManager);
        recyclerViewFavouriteList.setAdapter(adapterFavouriteList);
    }

    @Override
    public void deleteMealOfFavouriteList(Meal meal) {
        presenterInterface.deleteToFav(meal);

    }

    @Override
    public void onClickDelete(Meal meal) {
        deleteMealOfFavouriteList(meal);
    }
}