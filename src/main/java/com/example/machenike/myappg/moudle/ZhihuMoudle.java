package com.example.machenike.myappg.moudle;

import android.util.Log;

import com.example.machenike.myappg.app.MyApp;
import com.example.machenike.myappg.base.moudle.HttpFinishCallback;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.http.BaseObserver;
import com.example.machenike.myappg.http.ZhihuManager;
import com.example.machenike.myappg.utils.RxUtils;

public class ZhihuMoudle {
    public interface ZhihuCallback extends HttpFinishCallback{
        void setDailyListBean(DailyListBean data);
        void setMoreContent(DailyBeforeListBean dailyBeforeListBean);
        void setSectionListBean(SectionListBean section);
        void setHotListBean(HotListBean hotListBean);
    }
    public void getDailyListBean(final ZhihuCallback zhihuCallback){
        zhihuCallback.setShowProgressbar();
        ZhihuManager.getMyServer().getDailyList().compose(RxUtils.<DailyListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<DailyListBean>(zhihuCallback) {
            @Override
            public void onNext(DailyListBean value) {
                zhihuCallback.setDailyListBean(value);
                Log.e("==111",value.toString());
            }
        });
    }

    public void getBeforeData(String date, final ZhihuCallback zhihuCallback) {
        zhihuCallback.setShowProgressbar();
        MyApp.getZhihuServer().getDailyBeforeList(date).compose(RxUtils.<DailyBeforeListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<DailyBeforeListBean>(zhihuCallback) {
            @Override
            public void onNext(DailyBeforeListBean value) {
                zhihuCallback.setMoreContent(value);
            }
        });
    }
    public void getSectionListBean(final ZhihuCallback zhihuCallback){
        zhihuCallback.setShowProgressbar();
        MyApp.getZhihuServer().getSectionList().compose(RxUtils.<SectionListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<SectionListBean>(zhihuCallback) {
            @Override
            public void onNext(SectionListBean value) {
                zhihuCallback.setSectionListBean(value);
                Log.e("111",value.toString());
            }
        });
    }

    public void getHotListBean(final ZhihuCallback zhihuCallback){
        zhihuCallback.setShowProgressbar();
        MyApp.getZhihuServer().getHotList().compose(RxUtils.<HotListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<HotListBean>(zhihuCallback) {
            @Override
            public void onNext(HotListBean value) {
                zhihuCallback.setHotListBean(value);
            }
        });
    }
}
