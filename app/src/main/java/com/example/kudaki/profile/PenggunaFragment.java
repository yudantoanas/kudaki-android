package com.example.kudaki.profile;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kudaki.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenggunaFragment extends Fragment {
    @BindView(R.id.rvPengguna)
    RecyclerView rvPengguna;

    private List<MenuProfile> menuList;
    private MenuAdapter menuAdapter;

    public PenggunaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengguna, container, false);
        ButterKnife.bind(this, view);

        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(getActivity(), menuList);

        rvPengguna.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPengguna.setAdapter(menuAdapter);
        loadMenu();

        return view;
    }

    private void loadMenu() {
        menuList.clear();
        menuList.add(new MenuProfile(R.drawable.ic_wait_black, "Menunggu Konfirmasi"));
        menuList.add(new MenuProfile(R.drawable.ic_process_black, "Sedang Diproses"));
        menuList.add(new MenuProfile(R.drawable.ic_rent_black, "Sedang Menyewa"));
        menuList.add(new MenuProfile(R.drawable.ic_done_black, "Selesai"));
        menuList.add(new MenuProfile(R.drawable.ic_cancel_black, "Dibatalkan"));

        menuAdapter.notifyDataSetChanged();
    }

    class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
        private Context context;
        private List<MenuProfile> menuProfileList;

        public MenuAdapter(Context context, List<MenuProfile> menuProfileList) {
            this.context = context;
            this.menuProfileList = menuProfileList;
        }

        @NonNull
        @Override
        public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_profile_item, parent, false);
            return new MenuAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
            holder.title.setText(menuProfileList.get(position).getMenuName());
            holder.icon.setImageResource(menuProfileList.get(position).getIcon());
            holder.menu.setOnClickListener(v -> {
                Toast.makeText(context, menuProfileList.get(position).getMenuName(), Toast.LENGTH_SHORT).show();
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

    class MenuProfile {
        int icon;
        String menuName;

        public MenuProfile(int icon, String menuName) {
            this.icon = icon;
            this.menuName = menuName;
        }

        public int getIcon() {
            return icon;
        }

        public String getMenuName() {
            return menuName;
        }
    }
}
