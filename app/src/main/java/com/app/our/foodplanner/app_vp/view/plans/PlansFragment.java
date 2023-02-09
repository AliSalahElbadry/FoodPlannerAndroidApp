package com.app.our.foodplanner.app_vp.view.plans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.add_new_plan.AddNewPlanFragment;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;
import java.util.List;

public class PlansFragment extends Fragment implements PlansFragmentInterface {

    RecyclerView listPlans;
    String target;
    PlansAdapter adapter;
    ImageButton imageButtonAddPlan;

    public PlansFragment() {
        // Required empty public constructor
        adapter=new PlansAdapter(target,this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.plan_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listPlans=view.findViewById(R.id.recyclerViewPlansContainer);

        listPlans.setAdapter(adapter);
        listPlans.setLayoutManager(new LinearLayoutManager(getContext()));
        imageButtonAddPlan=view.findViewById(R.id.imageButtonAddPlan);
        imageButtonAddPlan.setOnClickListener(v->{
            AddNewPlanFragment fragment=new AddNewPlanFragment(this);
            fragment.show(getChildFragmentManager(),"");
        });
    }



    @Override
    public void updateList_AddPlan(PlanOfWeek plan) {
       adapter.plansOfWeek.add(plan);
       adapter.notifyDataSetChanged();
    }

    @Override
    public void updateListRemPlan(PlanOfWeek plan) {
        adapter.plansOfWeek.remove(plan);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTarget(String target) {
        this.target=target;
    }

    @Override
    public void setPlansData(List<PlanOfWeek> plans) {
        if(plans!=null) {
            adapter.setData(plans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAddMeal(PlanOfWeek plan) {
        //show dialog to set meal time
        ((MainActivityContainerInterface)getActivity()).getPresenter().setTargetAddMealToPlanPlanData(plan);
    }

    @Override
    public void showAddPlan(PlanOfWeek plan) {
        ((MainActivityContainerInterface)getActivity()).showPlanPage(plan);

    }
}