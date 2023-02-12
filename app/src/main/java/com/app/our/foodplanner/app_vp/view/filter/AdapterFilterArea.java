package com.app.our.foodplanner.app_vp.view.filter;

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
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdapterFilterArea extends RecyclerView.Adapter<AdapterFilterArea.ViewHolder> {

    private final Context context;
    private List<Area> areas;
    FilterFragmentInterface filterFragmentInterface;
    private static final String TAG = "RecyclerView";


    public AdapterFilterArea(Context context, FilterFragmentInterface filterFragmentInterface, List<Meal> values) {
        this.context = context;
        // this.values = values;
        this.areas = new ArrayList<>();
        this.filterFragmentInterface = filterFragmentInterface;
    }

    @NonNull
    @Override
    public AdapterFilterArea.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.row_filter, recyclerView, false);
        return new AdapterFilterArea.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFilterArea.ViewHolder holder, int position) {

        holder.txtTitle.setText(areas.get(position).strArea);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterFragmentInterface.getConainer().getPresenter().getMealByArea(areas.get(holder.getAdapterPosition()).strArea);
            }
        });


    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public void setData(List<Area> values) {
        this.areas = new ArrayList<>();
        areas.addAll(values);
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