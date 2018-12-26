package com.example.machenike.myappg.base.activity;

import com.example.machenike.myappg.base.presenter.BasePresenter;

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends SimpleActivity{
    public P presenter;

    @Override
    public void viewCreated() {
        presenter=createdPresenter();
        if (presenter!=null){
            presenter.attachView((V)this);
        }
    }

    protected abstract P createdPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
