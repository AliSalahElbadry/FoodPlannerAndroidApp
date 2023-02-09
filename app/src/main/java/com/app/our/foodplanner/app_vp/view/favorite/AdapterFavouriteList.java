package com.app.our.foodplanner.app_vp.view.favorite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.app_vp.view.home.AdapterHomeCategory;
import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavouriteList extends RecyclerView.Adapter<AdapterFavouriteList.ViewHolder>{

    private final Context context;
    private List<Meal> favorite;
    FavouriteFragmentInterface favoriteFragment;

    private static final String TAG="RecyclerView";
    public List<Meal> getallFavouriteList() {
        return favorite;
    }



    public AdapterFavouriteList(Context context, FavouriteFragmentInterface favouriteFragmentInterface, List<Meal> values) {
        this.context = context;
        // this.values = values;
        this.favorite =new ArrayList<>();
        this.favoriteFragment=favouriteFragmentInterface;
    }

    @NonNull
    @Override
    public AdapterFavouriteList.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_favourite_list,recyclerView,false);
        AdapterFavouriteList.ViewHolder viewHolder=new AdapterFavouriteList.ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: new row createeeeeeeeee");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavouriteList.ViewHolder holder, int position) {

        holder.txtViewNameFavouriteList.setText(favorite.get(position).getStrMeal());
        holder.textViewAreaFavouriteList.setText(favorite.get(position).getStrArea());
        holder.imgViewDeleteFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteFragment.onClickDelete(favorite.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return favorite.size();
    }

    public void setData(List<Meal> values){
        this.favorite = new ArrayList<>();
        for(int i=0;i<values.size();i++){
            favorite.add(values.get(i));
        }
        // notifyDataSetChanged();
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
            txtViewNameFavouriteList=itemView.findViewById(R.id.imgViewDeleteFavorite);
            textViewAreaFavouriteList=itemView.findViewById(R.id.textViewAreaFavouriteList);
            rowFavouriteList=itemView.findViewById(R.id.rowFavouriteList);

        }
    }
}
