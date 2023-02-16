package com.app.our.foodplanner.app_vp.view.favorite;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.add_meal_to_plan.AddMealToPlanFragment;
import com.app.our.foodplanner.app_vp.view.plans.PlansAdapter;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragmentInterface;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AdapterFavouriteList extends RecyclerView.Adapter<AdapterFavouriteList.ViewHolder>{

    private final Context context;
    public List<Meal> favorite;
    FavouriteFragmentInterface favouriteFragmentInterface;

    private static final String TAG="RecyclerView";

    public AdapterFavouriteList(Context context, FavouriteFragmentInterface favouriteFragmentInterface, List<Meal> values) {
        this.context = context;
        this.favorite =values;
        this.favouriteFragmentInterface=favouriteFragmentInterface;
    }

    @NonNull
    @Override
    public AdapterFavouriteList.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_favourite_list,recyclerView,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavouriteList.ViewHolder holder, int position) {

        holder.txtViewNameFavouriteList.setText(favorite.get(position).getStrMeal());
        holder.textViewAreaFavouriteList.setText(favorite.get(position).getStrArea());
        holder.rowFavouriteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteFragmentInterface.getConainer().showMeal(favorite.get(holder.getAdapterPosition()),favorite.get(holder.getAdapterPosition()).getImageBitmap(),1);
             }
        });
      holder.imageViewMealFavouriteList.setImageBitmap(BitmapFactory.decodeByteArray(favorite.get(position).getImage(),0,favorite.get(position).getImage().length));
        holder.imgViewDeleteFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteFragmentInterface.delete(favorite.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return favorite.size();
    }
    public void setData(List<Meal> newList) {
        favorite=new ArrayList<>();
        favorite.addAll(newList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewMealFavouriteList,imgViewDeleteFavorite;
        TextView txtViewNameFavouriteList,textViewAreaFavouriteList;
        public View layout;
        public CardView rowFavouriteList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            imageViewMealFavouriteList=itemView.findViewById(R.id.imageViewMealFavouriteList);
            imgViewDeleteFavorite=itemView.findViewById(R.id.imgViewDeleteFavorite);
            txtViewNameFavouriteList=itemView.findViewById(R.id.txtViewNameFavouriteList);
            textViewAreaFavouriteList=itemView.findViewById(R.id.textViewAreaFavouriteList);
            rowFavouriteList=itemView.findViewById(R.id.rowFiter);
        }
    }

}

