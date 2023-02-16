package com.app.our.foodplanner.app_vp.view.home;

import android.content.Context;
import android.graphics.Color;
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
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AdapterHomeCategory  extends RecyclerView.Adapter<AdapterHomeCategory.ViewHolder>{

    private final Context context;
    private List<Category> categories;
    HomeFragmentInterface homeFragment;
    private static int lastClicked=-1;


    public AdapterHomeCategory(Context context, HomeFragmentInterface home, List<Meal> values) {
        this.context = context;
        this.categories =new ArrayList<>();
        this.homeFragment=home;
        lastClicked =-1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterHomeCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_category,recyclerView,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeCategory.ViewHolder holder, int position) {

        holder.txtTitle.setText(categories.get(position).strCategory);
        if(lastClicked==holder.getLayoutPosition())
        {

            holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.teal_200));

        }else{
            holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.white));

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeFragment.getConainer().getPresenter().getMealsByCategory(categories.get(holder.getAdapterPosition()).strCategory);
                if(lastClicked!=-1)
                  notifyItemChanged(lastClicked);
                lastClicked=holder.getLayoutPosition();
                notifyItemChanged(lastClicked);

            }
        });
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setData(List<Category> values){
        this.categories = new ArrayList<>();
        categories.addAll(values);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
           txtTitle=itemView.findViewById(R.id.txtViewTitleCategory);

             cardView =itemView.findViewById(R.id.myCatItem);
        }
    }
}
