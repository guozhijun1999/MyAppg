package com.example.machenike.myappg.view;

import com.example.machenike.myappg.base.view.BaseView;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;

public interface ZhihuView extends BaseView {
    void show(DailyListBean dailyListBean);
    void showMoreContent(DailyBeforeListBean dailyBeforeListBean);
    void showSection(SectionListBean sectionListBean);
    void showHot(HotListBean hotListBean);
}
