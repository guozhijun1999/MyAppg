package com.example.machenike.myappg.moudle;

import com.example.machenike.myappg.app.MyApp;
import com.example.machenike.myappg.base.moudle.HttpFinishCallback;
import com.example.machenike.myappg.beans.wechat.WXHttpResponse;
import com.example.machenike.myappg.beans.wechat.WXItemBean;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.http.BaseObserver;
import com.example.machenike.myappg.utils.RxUtils;

import java.util.List;

public class WechatMoudle {
    public interface WechatCallback extends HttpFinishCallback {
        void setWechat(List<WXItemBean> list);
        void setMoewWechat(List<WXItemBean> list,String wrd);
    }
    public void getWechat(String key, int num, int page, final WechatCallback wechatCallback){
        wechatCallback.setShowProgressbar();
        MyApp.getWechatServer().getWXHot(key,num,page).compose(RxUtils.<WXHttpResponse<List<WXItemBean>>>rxObserableSchedulerHelper()).subscribe(new BaseObserver<WXHttpResponse<List<WXItemBean>>>(wechatCallback) {
            @Override
            public void onNext(WXHttpResponse<List<WXItemBean>> value) {
                List<WXItemBean> newslist = value.getNewslist();
                wechatCallback.setWechat(newslist);
            }
        });
    }

    public void getMoewWechat(String key, int num, int page, final String word, final WechatCallback wechatCallback){
        wechatCallback.setShowProgressbar();
        MyApp.getWechatServer().getWXHotSearch(key,num,page,word).compose(RxUtils.<WXHttpResponse<List<WXItemBean>>>rxObserableSchedulerHelper()).subscribe(new BaseObserver<WXHttpResponse<List<WXItemBean>>>(wechatCallback) {
            @Override
            public void onNext(WXHttpResponse<List<WXItemBean>> value) {
                List<WXItemBean> newslist = value.getNewslist();
                wechatCallback.setMoewWechat(newslist,word);
            }
        });
    }
}
