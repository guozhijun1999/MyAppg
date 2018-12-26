package com.example.machenike.myappg.base.presenter;

public interface BasePresenter<V> {
    void attachView(V v);
    void detachView();
}
