package com.example.kudaki.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.example.kudaki.model.response.DefaultResponse;
import com.example.kudaki.model.response.StoreItem;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.ViewHolder> {
    private Context context;
    private List<StoreItem> list;

    public RentalAdapter(Context context, List<StoreItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rental_item, parent, false);
        return new RentalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        holder.price.setText("Rp " + list.get(position).getPrice() + "/hari");
        holder.rating.setText(String.valueOf(list.get(position).getRating()));

        Glide.with(context)
                .load(list.get(position).getPhoto())
                .into(holder.image);

        holder.btnAdd.setOnClickListener(v -> {
            Long time = System.currentTimeMillis() / 1000L;

            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMax(100);
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Loading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            PostData service = RetrofitClient.getRetrofit().create(PostData.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("item_uuid", list.get(position).getUuid())
                    .addFormDataPart("item_amount", "1")
                    .addFormDataPart("duration_from", String.valueOf(time))
                    .addFormDataPart("duration", "1")
                    .build();

            SharedPreferences sharedPreferences = context.getSharedPreferences("LoginToken", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");

            Call<DefaultResponse> call = service.addToCart(token, requestBody);

            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    Log.d("RENTAL", "onResponse: " + response.code());
                    if (response.code() == 200) {
                        Toast.makeText(context, "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {

                }
            });

            Toast.makeText(context, "Berhasil Ditambah ke Keranjang", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rentalItemRating)
        TextView rating;
        @BindView(R.id.rentalItemTitle)
        TextView title;
        @BindView(R.id.rentalItemPrice)
        TextView price;
        @BindView(R.id.rentalItemAdd)
        Button btnAdd;
        @BindView(R.id.rentalItemImage)
        ImageView image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
