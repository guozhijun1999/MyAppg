package com.example.machenike.myappg.presenter;

import com.example.machenike.myappg.base.presenter.IBasePresenter;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.moudle.ZhihuMoudle;
import com.example.machenike.myappg.utils.DateUtil;
import com.example.machenike.myappg.view.ZhihuView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class ZhihuPresenter<V extends ZhihuView> extends IBasePresenter<V> implements ZhihuMoudle.ZhihuCallback{

    private ZhihuMoudle mZhihuMoudle=new ZhihuMoudle();
    public void getSectionListBean() {
        if (mView!=null){
            mZhihuMoudle.getSectionListBean(this);
        }
    }
    public void getDailyListBean() {
        if (mView!=null){
            mZhihuMoudle.getDailyListBean(this);
        }
    }
    public void getHotListBean() {
        if (mView!=null){
            mZhihuMoudle.getHotListBean(this);
        }
    }

    public void getBeforeDailyData(CalendarDay calendarDay){
        Observable.just(calendarDay)
                .map(new Function<CalendarDay, String>() {
                    @Override
                    public String apply(CalendarDay calendarDay) throws Exception {
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth() + 1);
                        String day = String.valueOf(calendarDay.getDay() + 1);
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                if(s.equals(DateUtil.getTomorrowDate())) {
                    getDailyListBean();
                    return false;
                }
                return true;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mZhihuMoudle.getBeforeData(s,ZhihuPresenter.this);
            }
        });
    }

    @Override
    public void setShowProgressbar() {
        if(mView!=null){
            mView.showProgressbar();
        }
    }

    @Override
    public void setHideProgressbar() {
        if(mView!=null){
            mView.hideProgressbar();
        }
    }

    @Override
    public void setMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        if(mView!=null){
            mView.showMoreContent(dailyBeforeListBean);
        }
    }

    @Override
    public void setSectionListBean(SectionListBean section) {
        if (mView!=null){
            mView.showSection(section);
        }
    }

    @Override
    public void setHotListBean(HotListBean hotListBean) {
        if (mView!=null){
            mView.showHot(hotListBean);
        }
    }

    @Override
    public void setDailyListBean(DailyListBean data) {
        if (mView!=null){
            mView.show(data);
        }
    }

    @Override
    public void setError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }
}
