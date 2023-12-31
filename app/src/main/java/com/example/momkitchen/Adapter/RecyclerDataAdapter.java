package com.example.momkitchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.momkitchen.Interfaces.ItemClickListener;
import com.example.momkitchen.Model.MealModel;
import com.example.momkitchen.R;
import com.example.momkitchen.Recipe;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder>{

    private List<MealModel> meals;
    private Context context;

    public RecyclerDataAdapter(Context context, List<MealModel> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerDataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerDataAdapter.DataViewHolder holder, int position) {
        String name = meals.get(position).getStrMeal();
        String desc = meals.get(position).getStrCategory()  + " | " + meals.get(position).getStrArea();
        holder.mealName.setText(name);
        holder.mealDesc.setText(desc);
        String id = "i" + meals.get(position).getIdMeal();
        int resourceId = context.getResources().getIdentifier(id, "drawable", context.getPackageName());
        if (resourceId != 0) {
            // Resource found, you can use resourceId
            Glide.with(this.context)
                    .load(resourceId)
                    .placeholder(R.drawable.beef)
                    .into(holder.mealImage);
        } else {
            // Resource not found, handle accordingly
            Glide.with(this.context)
                    .load(R.drawable.chicken)  // Provide a placeholder image
                    .into(holder.mealImage);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    Toast.makeText(context, "Long Click: " + meals.get(position), Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(context, Recipe.class);
                    // Pass necessary data to the next activity, e.g., meal ID
                    intent.putExtra("mealModel", meals.get(position));

                    // Start the new activity
                    context.startActivity(intent);
                }

//                    Toast.makeText(context, meals.get(position).getStrMeal(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals == null ? 0 : meals.size();
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mealName;
        private ImageView mealImage;
        private TextView mealDesc;
        private ItemClickListener itemClickListener;

        public DataViewHolder(View itemView) {
            super(itemView);

            mealName = (TextView) itemView.findViewById(R.id.meal_name);
            mealImage = (ImageView) itemView.findViewById(R.id.meal_image);
            mealDesc = (TextView) itemView.findViewById(R.id.meal_desc);

            itemView.setOnClickListener((View.OnClickListener) this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

    }
}
