package com.example.machenike.myappg.http;

import com.example.machenike.myappg.beans.wechat.WXHttpResponse;
import com.example.machenike.myappg.beans.wechat.WXItemBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WechatServer {
    //http://api.tianapi.com/weixin/home/?key=APIKEY&src=ID&num=10&page=1
    //    String HOST = "http://apis.baidu.com/txapi/weixin/";
    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
