package com.example.kudaki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.example.kudaki.model.mountain.Mountain;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private Context context;
    private List<Mountain> mountainList;

    public PopularAdapter(Context context, List<Mountain> mountainList) {
        this.context = context;
        this.mountainList = mountainList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mountainList.get(position).getName());

        // Glide
        Glide.with(context)
                .load(mountainList.get(position).getPhotoPath())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.popularImage)
        ImageView image;
        @BindView(R.id.popularName)
        TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
