package com.example.kudaki.renting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.example.kudaki.model.equipment.Equipment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
    private Context context;
    private List<Equipment> equipmentList;

    public EquipmentAdapter(Context context, List<Equipment> equipmentList) {
        this.context = context;
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipment_item, parent, false);
        return new EquipmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.equipmentTitle.setText(equipmentList.get(position).getEquipmentTitle());
        holder.equipmentPrice.setText("Rp " + equipmentList.get(position).getEquipmentPrice() + "/hari");
        holder.rentalName.setText(equipmentList.get(position).getUserName());

        // Glide
        Glide.with(context)
                .load(equipmentList.get(position).getImagePath())
                .into(holder.equipmentImage);

        holder.equipmentCard.setOnClickListener(view -> context.startActivity(
                new Intent(context, EquipmentActivity.class))
        );
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipmentCard)
        CardView equipmentCard;
        @BindView(R.id.equipmentTitle)
        TextView equipmentTitle;
        @BindView(R.id.equipmentPrice)
        TextView equipmentPrice;
        @BindView(R.id.rentalName)
        TextView rentalName;
        @BindView(R.id.equipmentImage)
        ImageView equipmentImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
