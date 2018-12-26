package com.example.machenike.myappg.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.machenike.myappg.base.presenter.BasePresenter;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends SimpleFragment {
    public P presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        presenter=createPresemter();
        if (presenter!=null){
            presenter.attachView((V)this);
        }
        super.onViewCreated(view, savedInstanceState);
//        EventBus.getDefault().register(this);

    }

    protected abstract P createPresemter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
            presenter=null;
        }
//        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
