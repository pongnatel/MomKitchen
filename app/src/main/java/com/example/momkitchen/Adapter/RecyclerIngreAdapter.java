package com.example.momkitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkitchen.R;

public class RecyclerIngreAdapter extends RecyclerView.Adapter<RecyclerIngreAdapter.IngreViewHolder>{
    private Context context;
    private final String[] ingredients;
    private final String[] measurements;


    public RecyclerIngreAdapter(Context context, String[] ingredients, String[] measurements) {
        this.context = context;
        this.ingredients = ingredients;
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public IngreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new RecyclerIngreAdapter.IngreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngreViewHolder holder, int position) {
        String ingredientName = ingredients[position];
        String measurement = measurements[position];

        holder.ingreName.setText(ingredientName);
        holder.ingreMeasurement.setText(measurement);
    }

    @Override
    public int getItemCount() {
        return ingredients == null? 0 : ingredients.length;
    }

    public static class IngreViewHolder extends RecyclerView.ViewHolder{
        private TextView ingreName;
        private TextView ingreMeasurement;
        public IngreViewHolder(@NonNull View itemView) {
            super(itemView);

            ingreName = (TextView) itemView.findViewById(R.id.ingre_name);
            ingreMeasurement = (TextView) itemView.findViewById(R.id.ingre_measure);
        }
    }
}
