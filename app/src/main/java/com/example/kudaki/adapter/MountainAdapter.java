package com.example.kudaki.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.example.kudaki.explore.MountainActivity;
import com.example.kudaki.model.response.Mountain;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Mountain> mountainList;

    public MountainAdapter(Context context, ArrayList<Mountain> mountainList) {
        this.context = context;
        this.mountainList = mountainList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mountain_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mountainList.get(position).getName());
        holder.height.setText(String.valueOf(mountainList.get(position).getHeight()) + " Mdpl");

        Glide.with(context)
                .load(mountainList.get(position).getPhotos().get(0).getFilePath())
                .into(holder.image);

        holder.detail.setOnClickListener(v -> {
            Intent intent = new Intent(context, MountainActivity.class);
            intent.putExtra("name", mountainList.get(position).getName());
            intent.putExtra("photo", mountainList.get(position).getPhotos().get(0).getFilePath());
            intent.putExtra("height", mountainList.get(position).getHeight());
            intent.putExtra("latitude", mountainList.get(position).getLatitude());
            intent.putExtra("longitude", mountainList.get(position).getLongitude());
            intent.putExtra("difficulty", mountainList.get(position).getDifficulty());
            intent.putExtra("description", mountainList.get(position).getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemMountDetail)
        Button detail;
        @BindView(R.id.itemMountName)
        TextView name;
        @BindView(R.id.itemMountHeight)
        TextView height;
        @BindView(R.id.itemMountImage)
        ImageView image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
