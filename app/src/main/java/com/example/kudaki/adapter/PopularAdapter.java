package com.example.kudaki.adapter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.example.kudaki.explore.MountainActivity;
import com.example.kudaki.model.mountain.Mountain;

import java.util.List;

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

        holder.btnDetail.setOnClickListener(v -> {
            Intent mountain = new Intent(context, MountainActivity.class);
            mountain.putExtra("id", 1);
            context.startActivity(mountain);
        });
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.popularDetail)
        Button btnDetail;
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
