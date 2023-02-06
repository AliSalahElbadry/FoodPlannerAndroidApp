package com.app.our.foodplanner.app_vp.view.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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


    private static final String TAG="RecyclerView";


    public AdapterHomeMealCategory(Context context, List<Meal> values) {
        this.context = context;
        // this.values = values;
        this.categoriesMeles =new ArrayList<>();

    }

    @NonNull
    @Override
    public AdapterHomeMealCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_home_layout,recyclerView,false);
        AdapterHomeMealCategory.ViewHolder viewHolder=new AdapterHomeMealCategory.ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: new row createeeeeeeeee");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeMealCategory.ViewHolder holder, int position) {
        holder.txtTitle.setText(categoriesMeles.get(position).getStrMeal());
      //  holder.txtCountry.setText(categoriesMeles.get(position).getStrArea());
        Glide.with(context).load(categoriesMeles.get(position).getStrMealThumb())
                .into(holder.imageView);

        // Picasso.get().load(values.get(position).getThumbnail()).into(holder.imageView);

//        holder.btnFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //onProductClickLesener.onClick(values.get(position));
//                //  holder.btnFav.setEnabled(false);
//            }
//        });

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


        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txtTitle=itemView.findViewById(R.id.txtViewTitleListCategoryHome);
            txtCountry=itemView.findViewById(R.id.textViewAreaCategoryHome);
            imageView=itemView.findViewById(R.id.imageViewMealHome);

            constraintLayout=itemView.findViewById(R.id.rowMealHome);

        }
    }
}
