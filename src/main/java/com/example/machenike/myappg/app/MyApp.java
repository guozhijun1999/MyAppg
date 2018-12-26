package com.example.machenike.myappg.app;

import android.app.Application;

import com.example.machenike.myappg.http.GankServer;
import com.example.machenike.myappg.http.HttpManager;
import com.example.machenike.myappg.http.WechatServer;
import com.example.machenike.myappg.http.ZhihuServer;
import com.example.machenike.myappg.utils.ImplPreferencesHelper;

public class MyApp extends Application {
    public static MyApp sMyApp;
    private static ImplPreferencesHelper implPreferencesHelper;
    private static ZhihuServer zhiHuServer;
    private static WechatServer wechatServer;
    private static GankServer gankServer;
    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp=this;
    }
    public static MyApp getMyApp(){
        return sMyApp;
    }
    public static ImplPreferencesHelper getImplPreferencesHelper(){
        if (implPreferencesHelper == null) {
            synchronized (ImplPreferencesHelper.class){
                if (implPreferencesHelper == null) {
                    implPreferencesHelper=new ImplPreferencesHelper();
                }
            }
        }
        return implPreferencesHelper;
    }

    /**
     * 获取知乎网络请求Api
     * @return
     */
    public static ZhihuServer getZhihuServer(){
        if (zhiHuServer == null) {
            synchronized (ZhihuServer.class){
                if (zhiHuServer == null) {
                    zhiHuServer= HttpManager.getInstance().getServer(ZhihuServer.HOST,ZhihuServer.class);
                }
            }
        }
        return zhiHuServer;
    }

    /**
     * 获取微信网络请求Api
     * @return
     */
    public static WechatServer getWechatServer(){
        if (wechatServer ==null){
            synchronized (WechatServer.class){
                if (wechatServer ==null){
                    wechatServer=HttpManager.getInstance().getServer(WechatServer.HOST,WechatServer.class);
                }
            }
        }
        return wechatServer;
    }

    /**
     * 获取干货集中营网络请求Api
     * @return
     */
    public static GankServer getGankServer(){
        if (gankServer ==null){
            synchronized (GankServer.class){
                if (gankServer ==null){
                    gankServer=HttpManager.getInstance().getServer(GankServer.HOST,GankServer.class);
                }
            }
        }
        return gankServer;
    }
}
