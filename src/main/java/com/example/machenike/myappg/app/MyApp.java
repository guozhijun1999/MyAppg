package com.example.machenike.myappg.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.machenike.myappg.http.api.GankServer;
import com.example.machenike.myappg.http.HttpManager;
import com.example.machenike.myappg.http.api.WechatServer;
import com.example.machenike.myappg.http.api.XituServer;
import com.example.machenike.myappg.http.api.ZhihuServer;
import com.example.machenike.myappg.utils.ImplPreferencesHelper;

public class MyApp extends Application {
    public static MyApp sMyApp;
    private static ImplPreferencesHelper implPreferencesHelper;
    private static ZhihuServer zhiHuServer;
    private static WechatServer wechatServer;
    private static GankServer gankServer;
    private static XituServer xituServer;


    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp=this;

        //初始化屏幕高度
        getScreenSize();
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

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
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

    /**
     * 获取掘土稀金（数据智慧）网络请求Api
     * @return
     */
    public static XituServer getXituServer(){
        if (xituServer == null){
            synchronized (XituServer.class){
                if (xituServer ==null){
                    xituServer=HttpManager.getInstance().getServer(XituServer.HOST,XituServer.class);
                }
            }
        }
        return xituServer;
    }
}
