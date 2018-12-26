package com.example.machenike.myappg.presenter;

import com.example.machenike.myappg.base.presenter.IBasePresenter;
import com.example.machenike.myappg.beans.wechat.WXItemBean;
import com.example.machenike.myappg.moudle.WechatMoudle;
import com.example.machenike.myappg.view.WechatView;

import java.util.List;

public class WechatPresenter<V extends WechatView> extends IBasePresenter<V> implements WechatMoudle.WechatCallback {
    private WechatMoudle mWechatMoudle=new WechatMoudle();

    public void getWechat(String key,int num,int page) {
        if (mView!=null){
            mWechatMoudle.getWechat(key,num,page,this);
        }
    }

    @Override
    public void setWechat(List<WXItemBean> list) {
        if (mView!=null){
            mView.showWechat(list);
        }
    }

    @Override
    public void setMoewWechat(List<WXItemBean> list) {

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
        if(mView!=null){
            mView.showError(error);
        }
    }
}
