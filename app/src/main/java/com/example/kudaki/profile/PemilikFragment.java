package com.example.kudaki.profile;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PemilikFragment extends Fragment implements PemilikContract.View {
    @BindView(R.id.rvPemilik)
    RecyclerView rvPemilik;
    @BindView(R.id.pemilikBtnAdd)
    Button btnAdd;

    private List<MenuProfile> menuList;
    private MenuAdapter menuAdapter;


    public PemilikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pemilik, container, false);
        ButterKnife.bind(this, view);

        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(getActivity(), menuList);

        rvPemilik.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPemilik.setAdapter(menuAdapter);
        loadMenu();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnAdd.setOnClickListener(v -> showDialog());
    }

    private void loadMenu() {
        menuList.clear();
        menuList.add(new MenuProfile(R.drawable.ic_new_order_black, "Pesanan Baru"));
        menuList.add(new MenuProfile(R.drawable.ic_process_black, "Proses Barang"));
        menuList.add(new MenuProfile(R.drawable.ic_rent_black, "Sedang Disewa"));
        menuList.add(new MenuProfile(R.drawable.ic_done_black, "Selesai"));

        menuAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog() {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.add_equipment_dialog, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        ImageView image = dialogView.findViewById(R.id.dialogImage);
        EditText name = dialogView.findViewById(R.id.dialogName);
        EditText price = dialogView.findViewById(R.id.dialogPrice);
        EditText desc = dialogView.findViewById(R.id.dialogDesc);
        EditText weight = dialogView.findViewById(R.id.dialogWeight);
        Button submit = dialogView.findViewById(R.id.dialogBtnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // submit alat
            }
        });
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void setPresenter(PemilikContract.Presenter presenter) {

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
