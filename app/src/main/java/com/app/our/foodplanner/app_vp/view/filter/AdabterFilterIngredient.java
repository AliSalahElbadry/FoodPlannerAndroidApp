package com.app.our.foodplanner.app_vp.view.filter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AdabterFilterIngredient extends RecyclerView.Adapter<AdabterFilterIngredient.ViewHolder> {

    private final Context context;
    private List<Ingredient> ingredients;
    FilterFragmentInterface filterFragmentInterface;
    private static final String TAG = "RecyclerView";
    private static int lastClicked=-1;

    public AdabterFilterIngredient(Context context, FilterFragmentInterface filterFragmentInterface, List<Meal> values) {
        this.context = context;
        // this.values = values;
        this.ingredients = new ArrayList<>();
        this.filterFragmentInterface = filterFragmentInterface;
        lastClicked =-1;
    }

    @NonNull
    @Override
    public AdabterFilterIngredient.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.row_filter, recyclerView, false);
        return new AdabterFilterIngredient.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterFilterIngredient.ViewHolder holder, int position) {

        holder.txtTitle.setText(ingredients.get(position).strIngredient);
        if(lastClicked==holder.getLayoutPosition())
        {

            holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.teal_200));

        }else{
            holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.white));

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterFragmentInterface.getConainer().getPresenter().getMealByIngredient(ingredients.get(holder.getAdapterPosition()).strIngredient);

                if(lastClicked!=-1)
                    notifyItemChanged(lastClicked);
                lastClicked=holder.getLayoutPosition();
                notifyItemChanged(lastClicked);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setData(List<Ingredient> values) {
        this.ingredients = new ArrayList<>();
        ingredients.addAll(values);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.txtViewTitleArea);
            cardView = itemView.findViewById(R.id.cardItemFilterArea);
        }
    }
}
