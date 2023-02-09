package com.app.our.foodplanner.app_vp.view.plans;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.util.ArrayList;
import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.ViewHolder>{
    List<PlanOfWeek>plansOfWeek;
    PlansFragmentInterface fragmentInterface;
    String target;
    public PlansAdapter(String target,PlansFragmentInterface plansFragmentInterface) {
        plansOfWeek=new ArrayList<>();
        this.target=target;
        fragmentInterface=plansFragmentInterface;
    }

    @NonNull
    @Override
    public PlansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_plans,recyclerView,false);
        return new PlansAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAdapter.ViewHolder holder, int position) {
        holder.textViewWeek.setText(plansOfWeek.get(position).getYear()+"/"+plansOfWeek.get(position).getMonth()+"/"+plansOfWeek.get(position).getWeek());
        holder.deletePlan.setOnClickListener(v -> fragmentInterface.updateListRemPlan(plansOfWeek.get(holder.getAdapterPosition())));
        holder.item.setOnClickListener(v->{
            if(target.equals("AddMeal"))
            {
                fragmentInterface.showAddMeal(plansOfWeek.get(holder.getAdapterPosition()));
            }else if(target.equals("showPlan"))
            {
                fragmentInterface.showAddPlan(plansOfWeek.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return plansOfWeek.size();
    }

    public void setData(List<PlanOfWeek> values){
        this.plansOfWeek = new ArrayList<>();
        plansOfWeek.addAll(values);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewWeek;
        ImageView deletePlan;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWeek= itemView.findViewById(R.id.PlanWeekPlans);
            deletePlan=itemView.findViewById(R.id.deletePlanPlans);
            item=itemView.findViewById(R.id.cardViewPlansItem);
        }
    }
}
