package com.example.momkitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkitchen.R;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private Context context;
    private final String[] instructions;
    public ViewPagerAdapter(Context context, String[] instruction) {
        this.context = context;
        this.instructions = instruction;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_item, parent, false);
        return new ViewPagerAdapter.ViewPagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewPagerViewHolder holder, int position) {
        int numOfStep = position+1;
        String step = "Step " + numOfStep + "/" + instructions.length;
        String instruction = instructions[position];

        holder.tvStep.setText(step);
        holder.tvIns.setText(instruction);
    }

    @Override
    public int getItemCount() {
        return instructions == null? 0 : instructions.length;
    }

    public static class ViewPagerViewHolder extends RecyclerView.ViewHolder{
        private TextView tvStep;
        private TextView tvIns;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStep = (TextView) itemView.findViewById(R.id.tvStep);
            tvIns = (TextView) itemView.findViewById(R.id.tvIns);
        }
    }
}
