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
        void setGank(List<GankItemBean.ResultsBean> list);
        void setGankRandom(List<GankHttpResponse.ResultsBean> list);
        void setGankGirl(List<GankHttpResponse.ResultsBean> list);
    }
    public void getGank(String tech, int num, int page, final GankCallback gankCallback){
        MyApp.getGankServer().getTechList(tech,num,page).compose(RxUtils.<GankItemBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<GankItemBean>(gankCallback) {
            @Override
            public void onNext(GankItemBean value) {
                List<GankItemBean.ResultsBean> results = value.getResults();
                gankCallback.setGank(results);
            }
        });
    }

    public void getGankRandom( int num ,final GankCallback gankCallback){
        MyApp.getGankServer().getRandomGirl(num).compose(RxUtils.<GankHttpResponse>rxObserableSchedulerHelper()).subscribe(new BaseObserver<GankHttpResponse>(gankCallback) {
            @Override
            public void onNext(GankHttpResponse value) {
                List<GankHttpResponse.ResultsBean> results = value.getResults();
                gankCallback.setGankRandom(results);
            }
        });
    }

    public void getGankGirl( int num ,int page,final GankCallback gankCallback){
        MyApp.getGankServer().getGirlList(num,page).compose(RxUtils.<GankHttpResponse>rxObserableSchedulerHelper()).subscribe(new BaseObserver<GankHttpResponse>(gankCallback) {
            @Override
            public void onNext(GankHttpResponse value) {
                List<GankHttpResponse.ResultsBean> results = value.getResults();
                gankCallback.setGankGirl(results);
            }
        });
    }
}
