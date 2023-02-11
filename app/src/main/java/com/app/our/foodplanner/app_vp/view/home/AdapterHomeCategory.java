package com.app.our.foodplanner.app_vp.view.home;

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
import com.app.our.foodplanner.app_vp.view.presenter.PresenterInterface;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AdapterHomeCategory  extends RecyclerView.Adapter<AdapterHomeCategory.ViewHolder>{

    private final Context context;
    private List<Category> categories;
    HomeFragmentInterface homeFragment;
    private static final String TAG="RecyclerView";


    public AdapterHomeCategory(Context context, HomeFragmentInterface home, List<Meal> values) {
        this.context = context;
        // this.values = values;
        this.categories =new ArrayList<>();
        this.homeFragment=home;
    }

    @NonNull
    @Override
    public AdapterHomeCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_category,recyclerView,false);
        ViewHolder viewHolder=new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: new row createeeeeeeeee");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeCategory.ViewHolder holder, int position) {

        holder.txtTitle.setText(categories.get(position).strCategory);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.getConainer().getPresenter().getMealsByCategory(categories.get(holder.getAdapterPosition()).strCategory);
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
