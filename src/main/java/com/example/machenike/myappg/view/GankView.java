package com.example.machenike.myappg.view;

import com.example.machenike.myappg.base.view.BaseView;
import com.example.machenike.myappg.beans.gank.GankHttpResponse;
import com.example.machenike.myappg.beans.gank.GankItemBean;

import java.util.List;

public interface GankView extends BaseView {
    void showGank(List<GankItemBean.ResultsBean> list);
    void showGankRandom(List<GankHttpResponse.ResultsBean> list);
    void showGankGirl(List<GankHttpResponse.ResultsBean> list);
}
