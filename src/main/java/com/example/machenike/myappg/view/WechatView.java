package com.example.machenike.myappg.view;

import com.example.machenike.myappg.base.view.BaseView;
import com.example.machenike.myappg.beans.wechat.WXItemBean;

import java.util.List;

public interface WechatView extends BaseView {
    void showWechat(List<WXItemBean> list);
    void showMoewWechat(List<WXItemBean> list);
}
