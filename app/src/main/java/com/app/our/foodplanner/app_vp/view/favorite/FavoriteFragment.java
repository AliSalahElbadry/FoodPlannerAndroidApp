package com.app.our.foodplanner.app_vp.view.favorite;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.add_meal_to_plan.AddMealToPlanFragment;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeCategory;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeMealCategory;
import com.app.our.foodplanner.app_vp.view.plans.PlansAdapter;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragmentInterface;
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteFragment extends Fragment implements FavouriteFragmentInterface{

    MainActivityContainerInterface mainActivityContainerInterface;
    RecyclerView recyclerViewFavouriteList;
    View view;
    AdapterFavouriteList adapterFavouriteList;
    ArrayList<String>exists;
   List<Meal> favs;

    public FavoriteFragment() {
        // Required empty public constructor
        favs=new ArrayList<>();
        adapterFavouriteList=new AdapterFavouriteList(getContext(),this,new ArrayList<>());
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
        mainActivityContainerInterface=((MainActivityContainerInterface) getActivity());
        recyclerViewFavouriteList=view.findViewById(R.id.recyclerViewFavouriteList);
        recyclerViewFavouriteList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFavouriteList.setAdapter(adapterFavouriteList);
    }

    @Override
    public void showData(List<Meal> meal) {
        exists=new ArrayList<>();
        favs=new ArrayList<>();
        if(meal!=null) {
            for (Meal m:meal) {
                if(isExists(m.getIdMeal()))
                {
                    exists.add(m.getIdMeal());
                    favs.add(m);
                }
            }
            adapterFavouriteList.setData(favs);
            adapterFavouriteList.notifyDataSetChanged();
        }
    }

  private boolean isExists(String id)
  {
      for (String i:exists) {
          if(i.equals(id))
          {
              return  false;
          }
      }
      return  true;
  }
    @Override
    public MainActivityContainerInterface getConainer() {
            return mainActivityContainerInterface;
    }
    public void delete(Meal meal)
    {
        adapterFavouriteList.favorite.remove(meal);
        mainActivityContainerInterface.getPresenter().UpdateMealOfFavouriteList(false, meal.getIdMeal());
        adapterFavouriteList.notifyDataSetChanged();
        Toast.makeText(getContext(), "Removed From Favorite", Toast.LENGTH_SHORT).show();
    }
    public void addItem(Meal m)
    {
        adapterFavouriteList.favorite.add(m);
        adapterFavouriteList.notifyDataSetChanged();
    }
}
