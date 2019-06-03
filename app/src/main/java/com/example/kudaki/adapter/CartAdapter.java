package com.example.kudaki.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.model.cart.Cart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<Cart> list;

    boolean btnState = false;

    public CartAdapter(Context context, List<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btnCheckout.setOnClickListener(v -> {
            // send request then
            // change button text to "Hubungi Pemilik"
            if (btnState) {
                try {
                    String number = "628112326009"; // get owner number

                    Intent whatsApp = new Intent();
                    whatsApp.setAction(Intent.ACTION_SEND);
                    whatsApp.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    whatsApp.putExtra("jid", number + "@s.whatsapp.net");

                    context.startActivity(whatsApp);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Harap install WhatsApp terlebih dahulu", Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")));
                }
            } else {
                holder.btnCheckout.setText("Hubungi Pemilik Alat");
                holder.btnCheckout.setBackgroundColor(context.getResources().getColor(R.color.kudakiPrimary));
                btnState = true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cartCheckout)
        Button btnCheckout;
        @BindView(R.id.cartEquipmentList)
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
