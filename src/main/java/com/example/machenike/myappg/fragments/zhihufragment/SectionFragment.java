package com.example.machenike.myappg.fragments.zhihufragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.adapter.zhihuadapter.SectionAdapter;
import com.example.machenike.myappg.base.fragment.BaseFragment;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.presenter.ZhihuPresenter;
import com.example.machenike.myappg.view.ZhihuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends BaseFragment<ZhihuView, ZhihuPresenter<ZhihuView>> implements ZhihuView, XRecyclerView.LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<SectionListBean.DataBean> mBeanList = new ArrayList<>();
    private SectionAdapter mSectionAdapter;

    public SectionFragment() {
        // Required empty public constructor
    }


    @Override
    protected ZhihuPresenter<ZhihuView> createPresemter() {
        return new ZhihuPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    protected void initData() {
        viewMain.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mSectionAdapter = new SectionAdapter(mActivity, mBeanList);
        viewMain.setAdapter(mSectionAdapter);
        viewMain.setLoadingListener(this);
        presenter.getSectionListBean();
    }

    @Override
    public void show(DailyListBean dailyListBean) {

    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {

    }

    @Override
    public void showSection(SectionListBean sectionListBean) {
        mSectionAdapter.addData(sectionListBean);
        Log.e("123123", sectionListBean.getData().toString());
    }

    @Override
    public void showHot(HotListBean hotListBean) {

    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void showError(String error) {
        Log.e("123123", error.toString());
    }


    @Override
    public void onRefresh() {
        presenter.getSectionListBean();
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        viewMain.loadMoreComplete();
    }
}
