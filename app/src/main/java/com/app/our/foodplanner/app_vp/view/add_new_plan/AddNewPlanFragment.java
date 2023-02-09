package com.app.our.foodplanner.app_vp.view.add_new_plan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragment;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragmentInterface;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.Calendar;
import java.util.Date;

public class AddNewPlanFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    PlansFragmentInterface plansFragmentInterface;
    public AddNewPlanFragment(PlansFragmentInterface plansFragmentInterface)
    {
        this.plansFragmentInterface=plansFragmentInterface;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int dayOfWeek= c.getFirstDayOfWeek();
        int weekOfYear=c.getWeekYear();
        Date date=new Date();
        return new DatePickerDialog(getContext(),this,date.getYear(),date.getMonth(),date.getDay());
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfWeek) {
        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
       // PlanOfWeek plan=new PlanOfWeek(0,""+""+month,""+dayOfWeek,""+year);
        //plansFragmentInterface.updateList_AddPlan(plan);
    }
}
