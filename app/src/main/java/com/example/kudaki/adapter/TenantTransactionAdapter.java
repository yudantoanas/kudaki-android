package com.example.kudaki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.model.response.Order;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TenantTransactionAdapter extends RecyclerView.Adapter<TenantTransactionAdapter.ViewHolder> {
    Context context;
    ArrayList<Order> list;

    String token;

    public TenantTransactionAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
    }

    public void setToken(String token) {
        this.token = token;
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
        holder.number.setText(list.get(position).getOrderNum());
        holder.status.setText(list.get(position).getStatus());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.CustomDialogTheme);
                // Get the layout inflater
                LayoutInflater inflater = LayoutInflater.from(v.getContext());

                View view = inflater.inflate(R.layout.tenant_dialog_transaction, null);
                TextView amount = view.findViewById(R.id.tenantTransactionAmount);
                TextView price = view.findViewById(R.id.tenantTransactionPrice);
                RecyclerView recyclerView = view.findViewById(R.id.rvTenantItem);

                amount.setText(list.get(position).getTotalItem());
                price.setText(list.get(position).getTotalPrice());

                TransactionDetailAdapter adapter = new TransactionDetailAdapter(v.getContext(), list.get(position).getOwners());
                adapter.setToken(token);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(adapter);

                builder.setNegativeButton("Tutup", (dialog, which) -> dialog.dismiss());

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.transactionItem)
        CardView cardView;
        @BindView(R.id.transItemNumber)
        TextView number;
        @BindView(R.id.transItemStatus)
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
