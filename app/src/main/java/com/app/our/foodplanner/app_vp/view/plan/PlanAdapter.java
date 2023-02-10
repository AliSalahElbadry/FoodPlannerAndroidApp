package com.app.our.foodplanner.app_vp.view.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter  extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
        List<Meal> meals;

    MainActivityContainerInterface mainActivityContainerInterface;
       PlanAdapter(ArrayList<Meal>meals,MainActivityContainerInterface activityContainerInterface)
        {
            this.meals=new ArrayList<>();
            mainActivityContainerInterface=activityContainerInterface;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_meal_in_plan, parent, false);
            return new MyViewHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.imageViewMealImage.setImageBitmap(meals.get(position).getImageBitmap());
            holder.textViewMealName.setText(meals.get(position).getStrMeal());
            holder.textViewMealCountry.setText(meals.get(position).getStrCategory()+","+meals.get(position).getStrArea());
            holder.deleteMealInPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mainActivityContainerInterface.showMeal();
                }
            });
        }
       public void setData(List<Meal> data)
       {
           meals=data;
           notifyDataSetChanged();
       }
        @Override
        public int getItemCount() {
            return meals.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageViewMealImage;
            public TextView textViewMealName;

            public TextView textViewMealCountry;
            public  ImageView deleteMealInPlan;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
               textViewMealName=itemView.findViewById(R.id.txtViewNameMealInPlan);
               textViewMealCountry= itemView.findViewById(R.id.textViewAreaCategoryMealInPlan);
               deleteMealInPlan= itemView.findViewById(R.id.imgViewDeleteMealInPlan);
               imageViewMealImage= itemView.findViewById(R.id.imageViewMealInPlan);
            }
        }
}
