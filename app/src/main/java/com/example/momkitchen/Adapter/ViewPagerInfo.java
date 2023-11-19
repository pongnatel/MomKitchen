package com.example.momkitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkitchen.Model.InfoModel;
import com.example.momkitchen.R;

import java.util.List;

public class ViewPagerInfo extends RecyclerView.Adapter<ViewPagerInfo.ViewPagerInfoViewHolder>{
    private final Context context;
    private final List<InfoModel> allInfo;

    public ViewPagerInfo(Context context, List<InfoModel> allInfo) {
        this.context = context;
        this.allInfo = allInfo;
    }

    @NonNull
    @Override
    public ViewPagerInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_section, parent, false);
        return new ViewPagerInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerInfoViewHolder holder, int position) {
        String title = allInfo.get(position).getTitle();
        String desc = allInfo.get(position).getDesc();

        holder.title.setText(title);
        holder.desc.setText(desc);
    }

    @Override
    public int getItemCount() {
        return allInfo == null ? 0 : allInfo.size();
    }

    public static class ViewPagerInfoViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView desc;
        public ViewPagerInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.info_title);
            desc = itemView.findViewById(R.id.info_desc);
        }
    }
}
