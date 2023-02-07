package com.app.our.foodplanner.app_vp.view.meal;

import android.content.Context;
import android.util.Log;
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
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeCategory;
import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealIngredientsAdapter   extends RecyclerView.Adapter<MealIngredientsAdapter.ViewHolder>{

    private final Context context;
    private ArrayList<String>data;
    int index=0;
    public MealIngredientsAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    @NonNull
    @Override
    public MealIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.ingredient_item_meal_page,recyclerView,false);
        return new MealIngredientsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealIngredientsAdapter.ViewHolder holder, int position) {
        if(index<data.size()) {
            holder.txtTitle.setText(data.get(index));
            index++;
            if(index<data.size()) {
                holder.textViewMusture.setText(data.get(index));
                index++;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<String> values){
        this.data = values;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public TextView textViewMusture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.textViewingredientItemtitle);
            textViewMusture=itemView.findViewById(R.id.textViewIngredientItemMusture);
        }
    }
}
