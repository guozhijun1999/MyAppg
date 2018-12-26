package com.example.machenike.myappg.http;

import android.database.DatabaseErrorHandler;

import com.example.machenike.myappg.beans.zhihu.CommentBean;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.DetailExtraBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionChildListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.beans.zhihu.ThemeChildListBean;
import com.example.machenike.myappg.beans.zhihu.ThemeListBean;
import com.example.machenike.myappg.beans.zhihu.ZhihuDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuServer {
    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
//    @GET("start-image/{res}")
//    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报DailyListBean
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();

    /**
     * 往期日报DailyBeforeListBean
     */
    @GET("news/before/{date}")
    Observable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 主题日报ThemeListBean
     */
    @GET("themes")
    Observable<ThemeListBean> getThemeList();

    /**
     * 主题日报详情ThemeChildListBean
     */
    @GET("theme/{id}")
    Observable<ThemeChildListBean> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报SectionListBean
     */
    @GET("sections")
    Observable<SectionListBean> getSectionList();

    /**
     * 专栏日报详情SectionChildListBean
     */
    @GET("section/{id}")
    Observable<SectionChildListBean> getSectionChildList(@Path("id") int id);

    /**
     * 热门日报HotListBean
     */
    @GET("news/hot")
    Observable<HotListBean> getHotList();

    /**
     * 日报详情ZhihuDetailBean
     */
    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息DetailExtraBean
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报的长评论CommentBean
     */
    @GET("story/{id}/long-comments")
    Observable<CommentBean> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论CommentBean
     */
    @GET("story/{id}/short-comments")
    Observable<CommentBean> getShortCommentInfo(@Path("id") int id);
}