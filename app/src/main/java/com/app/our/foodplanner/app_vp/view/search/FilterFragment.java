package com.app.our.foodplanner.app_vp.view.search;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.favorite.AdapterFavouriteList;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeMealCategory;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment implements FilterFragmentInterface{

    CheckBox checkBoxArea;
    RecyclerView recyclerViewArea ,recyclerViewMealsFilter;
    MainActivityContainerInterface mainActivityContainerInterface;

    View view;
    AdapterFilterArea adapterFilterArea;
    PresenterInterface presenterInterface;

    public FilterFragment() {
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
        return inflater.inflate(R.layout.filter_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        mainActivityContainerInterface=((MainActivityContainerInterface) getActivity());
        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();
        checkBoxArea=view.findViewById(R.id.checkBoxArea);

        recyclerViewArea=view.findViewById(R.id.recyclerViewFilterArea);
        adapterFilterArea=new AdapterFilterArea(view.getContext(),this,new ArrayList<>());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewArea.setLayoutManager(linearLayoutManager);
        recyclerViewArea.setAdapter(adapterFilterArea);




        recyclerViewArea=view.findViewById(R.id.recyclerViewMealsFilter);
    }

    @Override
    public void show_FilterData(@Nullable ArrayList<Ingredient> ingredients, @Nullable ArrayList<Area> areas, int type) {

    }

    @Override
    public void showData(List<Meal> meal) {

    }

    @Override
    public void onClickDelete(Boolean isFav, String meal) {

    }

    @Override
    public MainActivityContainerInterface getConainer() {
        return mainActivityContainerInterface;
    }

    @Override
    public void showArea(ArrayList<Area> res) {

    }

    @Override
    public void showFilterByArea(ArrayList<Meal> res) {
                Log.i(TAG, "showFilterByArea: "+res.get(0).getStrArea());

                adapterFilterArea.setData(res);
                adapterFilterArea.notifyDataSetChanged();

    }
}