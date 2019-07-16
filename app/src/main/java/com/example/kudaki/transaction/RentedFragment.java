package com.example.kudaki.transaction;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.TransactionAdapter;
import com.example.kudaki.model.response.Order;
import com.example.kudaki.model.response.OrderHistoryData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RentedFragment extends Fragment implements TransactionContract.View {
    @BindView(R.id.rvDone)
    RecyclerView recyclerView;

    TransactionAdapter adapter;

    String token;
    ArrayList<Order> list;

    TransactionContract.Presenter contractPresenter;
    RentedPresenter presenter;

    ProgressDialog progressDialog;

    public RentedFragment(String token) {
        this.token = token;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        ButterKnife.bind(this, view);

        presenter = new RentedPresenter(this, token);

        list = new ArrayList<>();

        progressDialog = new ProgressDialog(view.getContext());

        return view;
    }

    @Override
    public void setPresenter(TransactionContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void showProgress() {
        progressDialog.setMax(100);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        contractPresenter.loadTransaction();
    }

    @Override
    public void closeProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void display(OrderHistoryData data) {
        if (data.getOrders() == null) {
            Toast.makeText(getActivity(), "Tidak ada transaksi", Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            for (int i = 0; i < data.getOrders().size(); i++) {
                list.add(new Order(
                        data.getOrders().get(i).getOrderNum(),
                        data.getOrders().get(i).getStatus(),
                        data.getOrders().get(i).getCreatedAt(),
                        data.getOrders().get(i).getTotalItem(),
                        data.getOrders().get(i).getTenant()
                ));
            }
            adapter = new TransactionAdapter(getActivity(), list);
            adapter.setToken(token);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
