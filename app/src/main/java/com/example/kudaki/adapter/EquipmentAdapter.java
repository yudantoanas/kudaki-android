package com.example.kudaki.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.example.kudaki.model.equipment.Equipment;
import com.example.kudaki.renting.EquipmentActivity;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
        holder.equipmentTitle.setText(equipmentList.get(position).getName());
        holder.equipmentPrice.setText("Rp " + equipmentList.get(position).getPrice() + "/hari");

        // Glide
        Glide.with(context)
                .load(equipmentList.get(position).getPhoto())
                .into(holder.equipmentImage);

        holder.cardView.setOnClickListener(view -> context.startActivity(
                new Intent(context, EquipmentActivity.class))
        );

        holder.btnAddToCart.setOnClickListener(v -> {
            // retrofit add to cart
            PostData service = RetrofitClient.getRetrofit().create(PostData.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("item_uuid", equipmentList.get(position).getUuid())
                    .addFormDataPart("item_amount", "1")
                    .addFormDataPart("storefront_uuid", equipmentList.get(position).getStoreUuid())
                    .build();

            SharedPreferences sharedPreferences = context.getSharedPreferences("LoginToken", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");
//                Call<RentalResponse> call = service.addToCart(token, requestBody);
//
//                call.enqueue(new Callback<RentalResponse>() {
//                    @Override
//                    public void onResponse(Call<RentalResponse> call, Response<RentalResponse> response) {
//                        RentalResponse resp = response.body();
//
//                        RentalResponse.RentalData data = resp.getData();
//                        view.displayItems(data);
//                    }
//
//                    @Override
//                    public void onFailure(Call<RentalResponse> call, Throwable t) {
//
//                    }
//                });

            Toast.makeText(context, "Berhasil Ditambah ke Keranjang", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipmentCard)
        CardView cardView;
        @BindView(R.id.equipmentTitle)
        TextView equipmentTitle;
        @BindView(R.id.equipmentPrice)
        TextView equipmentPrice;
        @BindView(R.id.equipmentAddToCart)
        Button btnAddToCart;
        @BindView(R.id.equipmentImage)
        ImageView equipmentImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
