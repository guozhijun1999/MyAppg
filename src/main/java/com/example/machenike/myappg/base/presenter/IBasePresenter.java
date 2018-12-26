package com.example.machenike.myappg.base.presenter;

import java.lang.ref.WeakReference;

public class IBasePresenter<V> implements BasePresenter<V> {
    private WeakReference<V> mTWeakReference;
    public V mView;
    @Override
    public void attachView(V v) {
        mTWeakReference=new WeakReference<V>(v);
        mView=mTWeakReference.get();
    }

    @Override
    public void detachView() {
        if (mTWeakReference != null && mTWeakReference.get()!=null){
            mTWeakReference.clear();
            mTWeakReference=null;
        }
    }
}
