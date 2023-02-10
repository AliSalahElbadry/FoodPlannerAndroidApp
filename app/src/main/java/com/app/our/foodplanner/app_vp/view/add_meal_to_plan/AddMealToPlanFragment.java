package com.app.our.foodplanner.app_vp.view.add_meal_to_plan;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.model.PlanOfWeek;

public class AddMealToPlanFragment extends DialogFragment {

    RadioGroup radioGroupDay;
    RadioGroup radioGroupMealTime;
    Button done;
    String mealDay;
    String mealTime;
    PlanOfWeek plan;

    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    RadioButton radioButton7;
    public AddMealToPlanFragment(PlanOfWeek plan) {
        this.plan=plan;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_meal_to_plan_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroupDay=view.findViewById(R.id.radioGroupDayMeal);
        radioGroupMealTime=view.findViewById(R.id.radioGroupTimeMeal);
                    radioButton=view.findViewById(R.id.radioButtonSunDay);
                    radioButton2=view.findViewById(R.id.radioButtonMonDay);
                     radioButton3=view.findViewById(R.id.radioButtonThuesDay);
                   radioButton4=view.findViewById(R.id.radioButtonWedDay);
                    radioButton5=view.findViewById(R.id.radioButtonThurthDay);
                    radioButton6=view.findViewById(R.id.radioButtonFriDay);
                   radioButton7=view.findViewById(R.id.radioButtonSaturDay);
                int firstDay = Integer.parseInt(plan.getWeek());
                radioButton.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
                      firstDay++;
                radioButton2.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
                firstDay++;
                radioButton3.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
                firstDay++;
                radioButton4.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
                firstDay++;
                radioButton5.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
                firstDay++;
                radioButton6.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
                firstDay++;
                radioButton7.setText(plan.getYear()+"/"+plan.getMonth()+"/"+firstDay);
        radioGroupDay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               mealDay=((RadioButton)group.findViewById(checkedId)).getText().toString().split("\\/")[2];
            }
        });
        radioGroupMealTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mealTime=((RadioButton)group.findViewById(checkedId)).getText().toString();
            }
        });
        done=view.findViewById(R.id.buttonDoneAddMeal);
        done.setOnClickListener(v->{
            if(mealDay!=null&&mealTime!=null&&!mealTime.isEmpty()&&!mealDay.isEmpty())
            {
                ((MainActivityContainer)getActivity()).getPresenter().setTargetMealDayAndTime(mealDay,mealTime);
                Toast.makeText(getContext(), "Done Saving Meal In Plan", Toast.LENGTH_SHORT).show();
                dismiss();
            }else{
                Toast.makeText(getContext(), "Please Select Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setPlan(PlanOfWeek plan)
    {
        this.plan=plan;
    }
}