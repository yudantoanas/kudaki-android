package com.example.kudaki.transaction;

public class TransactionPresenter implements TransactionContract.Presenter {
    String token;
    TransactionContract.View view;

    public TransactionPresenter(TransactionContract.View view, String token) {
        this.token = token;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadTransaction() {

    }
}
