package com.app.our.foodplanner.app_vp.view.plan;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainer;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter  extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
        List<Meal> meals;
        PlanFragmentInterface planFragmentInterface;
    MainActivityContainerInterface mainActivityContainerInterface;
       PlanAdapter(ArrayList<Meal>meals,MainActivityContainerInterface activityContainerInterface,PlanFragmentInterface fragmentInterface)
        {
            this.meals=new ArrayList<>();
            mainActivityContainerInterface=activityContainerInterface;
            planFragmentInterface=fragmentInterface;
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
                   showDialog("Are You Sure ?\nYou Want To Delete The Meal ?",v.getContext(),holder.getAdapterPosition());
                }
            });
            holder.item.setOnClickListener(v->{
                mainActivityContainerInterface.showMeal(meals.get(holder.getAdapterPosition()),meals.get(holder.getAdapterPosition()).getImageBitmap(),1);
            });
        }
       public void setData(List<Meal> data)
       {
           meals=data;
           notifyDataSetChanged();
       }

    public void showDialog(String msg, Context context,int pos)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(context);
        alert.setMessage(msg);
        alert.setTitle("Alert");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                planFragmentInterface.deleteMealInPlan(meals.get(pos));
                meals.remove(meals.get(pos));
                setData(meals);
                notifyDataSetChanged();
                Toast.makeText(context, "Done Deleting Meal", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
        });
        alert.show();
    }
        @Override
        public int getItemCount() {
            return meals.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageViewMealImage;
            public TextView textViewMealName;
            public CardView item;
            public TextView textViewMealCountry;
            public  ImageView deleteMealInPlan;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
               textViewMealName=itemView.findViewById(R.id.txtViewNameMealInPlan);
               textViewMealCountry= itemView.findViewById(R.id.textViewAreaCategoryMealInPlan);
               deleteMealInPlan= itemView.findViewById(R.id.imgViewDeleteMealInPlan);
               imageViewMealImage= itemView.findViewById(R.id.imageViewMealInPlan);
               item=itemView.findViewById(R.id.cardViewMealInPlanItem);
            }
        }
}
