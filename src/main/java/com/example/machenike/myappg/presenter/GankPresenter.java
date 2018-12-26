package com.example.machenike.myappg.presenter;

import com.example.machenike.myappg.base.presenter.IBasePresenter;
import com.example.machenike.myappg.beans.gank.GankItemBean;
import com.example.machenike.myappg.moudle.GankMoudle;
import com.example.machenike.myappg.view.GankView;

import java.util.List;

public class GankPresenter<V extends GankView> extends IBasePresenter<V> implements GankMoudle.GankCallback {
    private GankMoudle mGankMoudle=new GankMoudle();

    public void getGank(String tech,int num,int page) {
        if (mView!=null){
            mGankMoudle.getGank(tech,num,page,this);
        }
    }
    public void getGankRandom(int num) {
        if (mView!=null){
            mGankMoudle.getGankRandom(num,this);
        }
    }

    public void getGankGril(int num,int page) {
        if (mView!=null){
            mGankMoudle.getGankGirl(num,page,this);
        }
    }

    @Override
    public void setGank(List<GankItemBean> list) {
        if (mView!=null){
            mView.showGank(list);
        }
    }

    @Override
    public void setGankGirl(List<GankItemBean> list) {
        if (mView!=null){
            mView.showGankGirl(list);
        }
    }

    @Override
    public void setGankRandom(List<GankItemBean> list) {
        if (mView!=null){
            mView.showGankRandom(list);
        }
    }

    @Override
    public void setShowProgressbar() {
        if(mView!=null){
            mView.showProgressbar();
        }
    }

    @Override
    public void setHideProgressbar() {
        if(mView!=null){
            mView.hideProgressbar();
        }
    }

    @Override
    public void setError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }
}
