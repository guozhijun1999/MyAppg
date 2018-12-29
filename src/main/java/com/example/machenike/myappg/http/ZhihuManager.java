package com.example.machenike.myappg.http;

import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.http.api.ZhihuServer;

import io.reactivex.Observable;

public class ZhihuManager {
    private static ZhihuServer myServer;
    public static ZhihuServer getMyServer(){
        if (myServer ==null){
            synchronized (ZhihuServer.class){
                if (myServer == null){
                    myServer=HttpManager.getInstance().getServer(ZhihuServer.HOST,ZhihuServer.class);
                }
            }
        }
        return myServer;
    }

    public Observable<DailyListBean> getDailyList(){
        return myServer.getDailyList();
    }
}
