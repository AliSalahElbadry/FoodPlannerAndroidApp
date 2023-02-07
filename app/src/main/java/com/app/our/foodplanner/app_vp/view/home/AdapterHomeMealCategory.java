package com.app.our.foodplanner.app_vp.view.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.model.Meal;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterHomeMealCategory extends RecyclerView.Adapter<AdapterHomeMealCategory.ViewHolder>{

    private final Context context;
    private List<Meal> categoriesMeles;

    public List<Meal> getCategoriesMeles() {
        return categoriesMeles;
    }

    private static final String TAG="RecyclerView";
    HomeFragmentInterface homeFragmentInterface;

    public AdapterHomeMealCategory(Context context,HomeFragmentInterface homeFragment, List<Meal> values) {
        this.context = context;
        // this.values = values;
        this.categoriesMeles =new ArrayList<>();
        homeFragmentInterface=homeFragment;
    }

    @NonNull
    @Override
    public AdapterHomeMealCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_home_layout,recyclerView,false);
        AdapterHomeMealCategory.ViewHolder viewHolder=new AdapterHomeMealCategory.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeMealCategory.ViewHolder holder, int position) {
        holder.txtTitle.setText(categoriesMeles.get(position).getStrMeal());
        homeFragmentInterface.getConainer().getPresenter().getRandomMealImage(holder.imageView,holder.cardView,categoriesMeles.get(position).getStrMealThumb());
        holder.cardView.setOnClickListener(l->{
            homeFragmentInterface.getConainer().showMeal(categoriesMeles.get(holder.getAdapterPosition()),((BitmapDrawable)holder.imageView.getDrawable()).getBitmap());
        });
    }
    @Override
    public int getItemCount() {
        return categoriesMeles.size();
    }

    public void setData(List<Meal> values){
        this.categoriesMeles = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle,txtCountry;

        public ImageView imageView;


        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txtTitle=itemView.findViewById(R.id.txtViewTitleListCategoryHome);
            txtCountry=itemView.findViewById(R.id.textViewAreaCategoryHome);
            imageView=itemView.findViewById(R.id.imageViewMealHome);
            cardView=itemView.findViewById(R.id.rowMealHome);

        }
    }
}
