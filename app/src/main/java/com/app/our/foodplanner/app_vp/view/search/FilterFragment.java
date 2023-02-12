package com.app.our.foodplanner.app_vp.view.search;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SearchView;

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
import com.app.our.foodplanner.model.RootIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class FilterFragment extends Fragment implements FilterFragmentInterface{

    CheckBox checkBoxArea,checkBoxIngredient;
    RecyclerView recyclerViewArea ,recyclerViewMealsFilter;
    MainActivityContainerInterface mainActivityContainerInterface;

    View view;
    AdapterFilterArea adapterFilterArea;
    PresenterInterface presenterInterface;
    SearchView searchViewFilter;

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
        checkBoxIngredient=view.findViewById(R.id.checkBoxIngredient);
        searchViewFilter=view.findViewById(R.id.searchViewFilter);

        recyclerViewArea=view.findViewById(R.id.recyclerViewFilterArea);
        adapterFilterArea=new AdapterFilterArea(view.getContext(),this,new ArrayList<>());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewArea.setLayoutManager(linearLayoutManager);
        recyclerViewArea.setAdapter(adapterFilterArea);


        searchViewFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterFilterArea.notifyDataSetChanged();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(mainActivityContainerInterface.checkConnectionState())
                    if(!newText.isEmpty())
                        if (checkBoxIngredient.isChecked()) {
                            mainActivityContainerInterface.getPresenter().searchForIngredient(newText);
                        }
                if (checkBoxArea.isChecked()){
                    mainActivityContainerInterface.getPresenter().searchForArea(newText);
                }
                return true;
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
    public void showArea(ArrayList<Area> res) {

    }

    @Override
    public void showFilterByArea(ArrayList<Meal> res) {
        Log.i(TAG, "showFilterByArea: " + res.get(0).getStrArea());
        adapterFilterArea.setData(res);
        adapterFilterArea.notifyDataSetChanged();


    }

    @Override
    public void showFilterByIngradient(ArrayList<Ingredient> res) {

    }

    @Override
    public void showIngradient(ArrayList<Ingredient> mealsAfter) {

    }
}
