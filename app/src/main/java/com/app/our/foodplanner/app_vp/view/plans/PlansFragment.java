package com.app.our.foodplanner.app_vp.view.plans;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
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
import com.app.our.foodplanner.app_vp.view.add_new_plan.AddNewPlanFragment;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;
import java.util.List;

public class PlansFragment extends Fragment implements PlansFragmentInterface {

    RecyclerView listPlans;
    String target;
    PlansAdapter adapter;
    ImageButton imageButtonAddPlan;
    List<PlanOfWeek> plans;
    public PlansFragment() {
        // Required empty public constructor
        plans=new ArrayList<>();
        adapter=new PlansAdapter("",this);
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
            DatePickerDialog dialog=new DatePickerDialog(getContext());
            dialog.setCancelable(true);
            TextView textView=new TextView(dialog.getContext());
            textView.setText("Please Select First Day In Your Week");
            textView.setTextSize(24);
            textView.setTextColor(Color.BLACK);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setBackgroundColor(Color.CYAN);
            dialog.setCustomTitle(textView);
            CalendarView calendarView=new CalendarView(getContext());
            dialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
                ((MainActivityContainerInterface)getActivity()).getPresenter().addPlan(new PlanOfWeek(""+dayOfMonth,""+(month+1),""+year,""));
                Toast.makeText(getContext(), "Done Adding Plan", Toast.LENGTH_SHORT).show();
              dialog.dismiss();
            });
            dialog.show();
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
        ((MainActivityContainerInterface)getActivity()).getPresenter().removePlan(plan);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Done Removing Plan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTarget(String target) {
        adapter.target=target;
        this.target=target;
    }

    @Override
    public void setPlansData(List<PlanOfWeek> plans) {
        if(plans!=null) {
            this.plans=plans;
            adapter.setData(this.plans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAddMeal(PlanOfWeek plan) {

        ((MainActivityContainerInterface)getActivity()).getPresenter().setTargetAddMealToPlanPlanData(plan);
        AddMealToPlanFragment addMealToPlanFragment=new AddMealToPlanFragment(plan);
        addMealToPlanFragment.show(getParentFragmentManager(),"");
    }
    @Override
    public void showAddPlan(PlanOfWeek plan) {
        ((MainActivityContainerInterface)getActivity()).showPlanPage(plan);
    }
}