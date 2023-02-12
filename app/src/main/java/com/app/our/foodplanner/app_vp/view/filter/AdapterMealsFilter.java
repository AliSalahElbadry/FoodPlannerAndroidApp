package com.app.our.foodplanner.app_vp.view.filter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AdapterMealsFilter extends RecyclerView.Adapter<AdapterMealsFilter.ViewHolder>{

    private final Context context;
    private List<Meal>meals;

    FilterFragmentInterface filterFragmentInterface;

    public AdapterMealsFilter(Context context,FilterFragmentInterface filterFragmentInterface,ArrayList<Meal>data)
    {
        this.context=context;this.filterFragmentInterface=filterFragmentInterface;
        this.meals=data;
    }
    @NonNull
    @Override
    public AdapterMealsFilter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_item_meal_filter,recyclerView,false);
        AdapterMealsFilter.ViewHolder viewHolder=new AdapterMealsFilter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMealsFilter.ViewHolder holder, int position) {
         holder.txtTitle.setText(meals.get(position).getStrMeal());
         if(filterFragmentInterface.getConainer().checkConnectionState())
            filterFragmentInterface.getConainer().getPresenter().getRandomMealImage(holder.imageView,holder.cardView,meals.get(position).getStrMealThumb());

        holder.cardView.setOnClickListener(l->{
            if(filterFragmentInterface.getConainer().checkConnectionState()) {
                filterFragmentInterface.getConainer().showMeal(meals.get(holder.getAdapterPosition()), ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap(), 0);
            }else{
                Toast.makeText(context, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setData(List<Meal> values){
        if(values!=null)
            this.meals = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;

        public ImageView imageView;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txtTitle=itemView.findViewById(R.id.txtViewTitleListAreaFilter);
            imageView=itemView.findViewById(R.id.imageViewMealFilter);
            cardView=itemView.findViewById(R.id.rowItemFilterMeal);

        }
    }
}
