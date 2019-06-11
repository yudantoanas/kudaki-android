package com.example.kudaki.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.model.MenuProfile;
import com.example.kudaki.profile.TransactionActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileMenuAdapter extends RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuProfile> menuProfileList;

    public ProfileMenuAdapter(Context context, List<MenuProfile> menuProfileList) {
        this.context = context;
        this.menuProfileList = menuProfileList;
    }

    @NonNull
    @Override
    public ProfileMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_profile_item, parent, false);
        return new ProfileMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileMenuAdapter.ViewHolder holder, int position) {
        holder.title.setText(menuProfileList.get(position).getMenuName());
        holder.icon.setImageResource(menuProfileList.get(position).getIcon());
        holder.menu.setOnClickListener(v -> {
            Intent transaction = new Intent(context, TransactionActivity.class);
            transaction.putExtra("fragmentItem", position);
            context.startActivity(transaction);
        });
    }

    @Override
    public int getItemCount() {
        return menuProfileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mProfile)
        LinearLayout menu;
        @BindView(R.id.mProfileIcon)
        ImageView icon;
        @BindView(R.id.mProfileTitle)
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
