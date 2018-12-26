package com.example.machenike.myappg.fragments.zhihufragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.activitys.CalendarActivity;
import com.example.machenike.myappg.adapter.zhihuadapter.DailyAdapter;
import com.example.machenike.myappg.base.fragment.BaseFragment;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.presenter.ZhihuPresenter;
import com.example.machenike.myappg.utils.CircularAnimUtil;
import com.example.machenike.myappg.utils.DateUtil;
import com.example.machenike.myappg.view.ZhihuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends BaseFragment<ZhihuView, ZhihuPresenter<ZhihuView>> implements ZhihuView, XRecyclerView.LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    List<DailyListBean.StoriesBean> mList=new ArrayList<>();
    private String mTomorrowDate;
    private DailyAdapter mDailyAdapter;
    //用于刷新的时候判断日期
    String currentDate;

    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    protected ZhihuPresenter<ZhihuView> createPresemter() {
        return new ZhihuPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initData() {
        currentDate = DateUtil.getTomorrowDate();
        viewMain.setLayoutManager(new LinearLayoutManager(mActivity));
        mDailyAdapter = new DailyAdapter(mActivity,mList);
        viewMain.setAdapter(mDailyAdapter);
        viewMain.setLoadingListener(this);
        presenter.getDailyListBean();
    }

    @Override
    public void show(DailyListBean dailyListBean) {
        mDailyAdapter.addDailuDate(dailyListBean);
    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        currentDate=String.valueOf(Integer.valueOf(dailyBeforeListBean.getDate()));
        mDailyAdapter.addDailyBeforeDate(dailyBeforeListBean);
    }

    @Override
    public void showSection(SectionListBean sectionListBean) {

    }

    @Override
    public void showHot(HotListBean hotListBean) {

    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onRefresh() {
        if (currentDate.equals(DateUtil.getTomorrowDate())) {
            presenter.getDailyListBean();
        } else {
            int year = Integer.valueOf(currentDate.substring(0, 4));
            int month = Integer.valueOf(currentDate.substring(4, 6));
            int day = Integer.valueOf(currentDate.substring(6, 8));
            CalendarDay date = CalendarDay.from(year, month - 1, day);
            EventBus.getDefault().post(date);
        }
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        viewMain.loadMoreComplete();
    }
    /**
     * 接收日期重新发起请求
     * @param date
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(CalendarDay date) {
        if (presenter != null) {
            presenter.getBeforeDailyData(date);
        }
    }


    @OnClick(R.id.fab_calender)
    public void onViewClicked() {
        Intent it = new Intent();
        it.setClass(mActivity, CalendarActivity.class);
        CircularAnimUtil.startActivity(mActivity, it, fabCalender, R.color.fab_bg);
    }
}
