package com.app.our.foodplanner.app_vp.view.add_meal_to_plan;

import android.app.Dialog;
import android.os.Bundle;
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

public class AddMealToPlanFragment extends DialogFragment {

    RadioGroup radioGroupDay;
    RadioGroup radioGroupMealTime;
    Button done;
    String mealDay;
    String mealTime;
    public AddMealToPlanFragment() {
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
        return inflater.inflate(R.layout.add_meal_to_plan_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroupDay=view.findViewById(R.id.radioGroupDayMeal);
        radioGroupMealTime=view.findViewById(R.id.radioGroupTimeMeal);
        radioGroupDay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               mealDay=((RadioButton)group.findViewById(checkedId)).getText().toString();
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
            if(mealDay!=null&&mealTime!=null)
            {
                ((MainActivityContainer)getActivity()).getPresenter().setTargetMealDayAndTime(mealDay,mealTime);
                dismiss();
            }else{
                Toast.makeText(getContext(), "Please Select Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}