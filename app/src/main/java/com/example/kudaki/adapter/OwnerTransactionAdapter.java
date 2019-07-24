package com.example.kudaki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.model.response.DefaultResponse;
import com.example.kudaki.model.response.OrderOwner;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerTransactionAdapter extends RecyclerView.Adapter<OwnerTransactionAdapter.ViewHolder> {
    Context context;
    ArrayList<OrderOwner> list;

    String token;

    public OwnerTransactionAdapter(Context context, ArrayList<OrderOwner> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number.setText(list.get(position).getTenant().getFullName());
        holder.status.setText(list.get(position).getStatus());

        holder.cardView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.CustomDialogTheme);

            LayoutInflater inflater = LayoutInflater.from(v.getContext());

            View view = inflater.inflate(R.layout.owner_dialog_transaction, null);
            TextView name = view.findViewById(R.id.ownerTransactionTenant);

            name.setText(list.get(position).getTenant().getFullName());

            switch (list.get(position).getStatus()) {
                case "PENDING":
                    builder.setPositiveButton("Terima", (dialog, which) -> {
                        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("owner_order_uuid", list.get(position).getUuid())
                                .build();
                        Call<DefaultResponse> call = service.approveOrder(token, requestBody);

                        call.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(context, "Status diubah", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });
                    });
                    builder.setNegativeButton("Tolak", (dialog, which) -> {
                        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("owner_order_uuid", list.get(position).getUuid())
                                .build();
                        Call<DefaultResponse> call = service.dissaproveOrder(token, requestBody);

                        call.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(context, "Berhasil tolak", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });
                    });
                    builder.setNeutralButton("Tutup", (dialog, which) -> dialog.dismiss());
                    break;
                case "APPROVED":
                    builder.setPositiveButton("Konfirmasi Disewa", (dialog, which) -> {
                        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("owner_order_uuid", list.get(position).getUuid())
                                .build();
                        Call<DefaultResponse> call = service.approveOrder(token, requestBody);

                        call.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(context, "Status diubah", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });
                    });
                    builder.setNeutralButton("Tutup", (dialog, which) -> dialog.dismiss());
                    break;
                case "RENTED":
                    builder.setPositiveButton("Sudah Dikembalikan", (dialog, which) -> {
                        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("owner_order_uuid", list.get(position).getUuid())
                                .build();
                        Call<DefaultResponse> call = service.confirmReturn(token, requestBody);

                        call.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(context, "Status diubah", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });
                    });
                    builder.setNeutralButton("Tutup", (dialog, which) -> dialog.dismiss());
                    break;
                default:
                    builder.setNeutralButton("Tutup", (dialog, which) -> dialog.dismiss());
            }

            builder.setView(view);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.transItemNumber)
        TextView number;
        @BindView(R.id.transItemStatus)
        TextView status;
        @BindView(R.id.transactionItem)
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
