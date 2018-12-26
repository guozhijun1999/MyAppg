package com.example.machenike.myappg.view;

import com.example.machenike.myappg.base.view.BaseView;
import com.example.machenike.myappg.beans.gank.GankItemBean;

import java.util.List;

public interface GankView extends BaseView {
    void showGank(List<GankItemBean> list);
    void showGankRandom(List<GankItemBean> list);
    void showGankGirl(List<GankItemBean> list);
}
