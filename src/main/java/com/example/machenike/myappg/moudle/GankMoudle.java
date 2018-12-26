package com.example.machenike.myappg.moudle;

import com.example.machenike.myappg.app.MyApp;
import com.example.machenike.myappg.base.moudle.HttpFinishCallback;
import com.example.machenike.myappg.beans.gank.GankHttpResponse;
import com.example.machenike.myappg.beans.gank.GankItemBean;
import com.example.machenike.myappg.http.BaseObserver;
import com.example.machenike.myappg.utils.RxUtils;

import java.util.List;

public class GankMoudle {
    public interface GankCallback extends HttpFinishCallback{
        void setGank(List<GankItemBean> list);
        void setGankRandom(List<GankItemBean> list);
        void setGankGirl(List<GankItemBean> list);
    }
    public void getGank(String tech, int num, int page, final GankCallback gankCallback){
        MyApp.getGankServer().getTechList(tech,num,page).compose(RxUtils.<GankHttpResponse<List<GankItemBean>>>rxObserableSchedulerHelper()).subscribe(new BaseObserver<GankHttpResponse<List<GankItemBean>>>(gankCallback) {
            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> value) {
                List<GankItemBean> results = value.getResults();
                gankCallback.setGank(results);
            }
        });
    }

    public void getGankRandom( int num ,final GankCallback gankCallback){
        MyApp.getGankServer().getRandomGirl(num).compose(RxUtils.<GankHttpResponse<List<GankItemBean>>>rxObserableSchedulerHelper()).subscribe(new BaseObserver<GankHttpResponse<List<GankItemBean>>>(gankCallback) {
            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> value) {
                List<GankItemBean> results = value.getResults();
                gankCallback.setGankRandom(results);
            }
        });
    }

    public void getGankGirl( int num ,int page,final GankCallback gankCallback){
        MyApp.getGankServer().getGirlList(num,page).compose(RxUtils.<GankHttpResponse<List<GankItemBean>>>rxObserableSchedulerHelper()).subscribe(new BaseObserver<GankHttpResponse<List<GankItemBean>>>(gankCallback) {
            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> value) {
                List<GankItemBean> results = value.getResults();
                gankCallback.setGankGirl(results);
            }
        });
    }
}
