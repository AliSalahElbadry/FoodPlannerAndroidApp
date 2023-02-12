package com.app.our.foodplanner.app_vp.view.search;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.our.foodplanner.R;
import com.app.our.foodplanner.model.Meal;

import java.util.List;

public class AdapterFilterArea extends RecyclerView.Adapter<AdapterFilterArea.ViewHolder>{

    private final Context context;
    private List<Meal> filter;
    FilterFragmentInterface filterFragmentInterface;

    private static final String TAG="RecyclerView";

    public AdapterFilterArea(Context context, FilterFragmentInterface filterFragmentInterface, List<Meal> values) {
        this.context = context;
        this.filter =values;
        this.filterFragmentInterface=filterFragmentInterface;
    }

    @NonNull
    @Override
    public AdapterFilterArea.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_filter,recyclerView,false);
        AdapterFilterArea.ViewHolder viewHolder=new AdapterFilterArea.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFilterArea.ViewHolder holder, int position) {

        holder.txtViewNameArea.setText(filter.get(position).getStrArea());

       // holder.imageViewMealFilterArea.setImageBitmap(BitmapFactory.decodeByteArray(filter.get(position).getImage(),0,filter.get(position).getImage().length));

        holder.rowFiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // filterFragmentInterface.getConainer().getPresenter().filterByAreaOnSuccessResults(filter<>);//getMealsByCategory(filter.get(holder.getAdapterPosition()).getStrArea());
            }
        });


    }

    @Override
    public int getItemCount() {
        return filter.size();
    }

    public void setData(List<Meal> values){
        this.filter = values;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewMealFilterArea;
        TextView txtViewNameArea;
        public View layout;
        public CardView rowFiter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            imageViewMealFilterArea=itemView.findViewById(R.id.imageViewMealFilterArea);
            txtViewNameArea=itemView.findViewById(R.id.txtViewNameArea);

            rowFiter=itemView.findViewById(R.id.rowFiter);

        }
    }

}
