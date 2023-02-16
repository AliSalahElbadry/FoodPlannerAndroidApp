package com.app.our.foodplanner.app_vp.view.filter;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment implements FilterFragmentInterface{

    RecyclerView recyclerViewItems ,recyclerViewFilter;
    public boolean firstShow=false;
    MainActivityContainerInterface mainActivityContainerInterface;
    ImageView backbtnFilter;
    AdapterFilterArea adapterFilter;
    AdabterFilterIngredient adabterFilterIngredient;
    Button buttonAreaFilter,buttonIngredientFilter;

    AdapterMealsFilter mealsAdapter;
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
        mainActivityContainerInterface=((MainActivityContainerInterface) getActivity());
        presenterInterface=((MainActivityContainerInterface)getActivity()).getPresenter();
        buttonAreaFilter=view.findViewById(R.id.buttonAreaFilter);
        backbtnFilter=view.findViewById(R.id.backbtnFilter);
        buttonIngredientFilter=view.findViewById(R.id.buttonIngredientFilter);
        recyclerViewFilter=view.findViewById(R.id.recyclerViewFilter);
        adapterFilter=new AdapterFilterArea(getContext(),this,new ArrayList<>());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewFilter.setLayoutManager(linearLayoutManager);
        recyclerViewFilter.setAdapter(adapterFilter);
        recyclerViewItems=view.findViewById(R.id.filterMealsContainer);
        recyclerViewItems.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));mealsAdapter=new AdapterMealsFilter(getContext(),this,new ArrayList<>());
        recyclerViewItems.setAdapter(mealsAdapter);
        adabterFilterIngredient=new AdabterFilterIngredient(getContext(),this,new ArrayList<>());
        backbtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityContainerInterface.closeFilter();
            }
        });
        buttonIngredientFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainActivityContainerInterface.checkConnectionState()) {
                    firstShow=false;
                    recyclerViewFilter.setAdapter(adabterFilterIngredient);
                    mainActivityContainerInterface.getPresenter().getAllIngredientsForFilter();
                }
                else {
                    Toast.makeText(getContext(), "OPPS! Connection !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonAreaFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(mainActivityContainerInterface.checkConnectionState()) {
                   firstShow=false;
                   recyclerViewFilter.setAdapter(adapterFilter);
                   mainActivityContainerInterface.getPresenter().getAllAreasForFilter();
               }
               else {
                   Toast.makeText(getContext(), "OPPS! Connection !", Toast.LENGTH_SHORT).show();
               }
            }
        });

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
    public boolean getShowFirst() {
        return firstShow;
    }

    @Override
    public void setShowFirst(boolean first) {
     firstShow=first;
    }

    @Override
    public void showArea(ArrayList<Area> res) {

        adapterFilter.setData(res);
        adapterFilter.notifyDataSetChanged();
    }

    @Override
    public void showFilterByArea(ArrayList<Meal> res) {
        if(res!=null) {
            mealsAdapter.setData(res);
            mealsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showIngradient(ArrayList<Ingredient> ingredients) {

        if(ingredients!=null) {
            adabterFilterIngredient.setData(ingredients);
            adabterFilterIngredient.notifyDataSetChanged();
        }
    }
}
