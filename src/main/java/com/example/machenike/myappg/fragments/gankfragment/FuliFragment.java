package com.example.machenike.myappg.fragments.gankfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.adapter.gankadapter.FuliAdapter;
import com.example.machenike.myappg.base.fragment.BaseFragment;
import com.example.machenike.myappg.beans.gank.GankHttpResponse;
import com.example.machenike.myappg.beans.gank.GankItemBean;
import com.example.machenike.myappg.presenter.GankPresenter;
import com.example.machenike.myappg.view.GankView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuliFragment extends BaseFragment<GankView, GankPresenter<GankView>> implements GankView, XRecyclerView.LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<GankHttpResponse.ResultsBean> mList=new ArrayList<>();
    private FuliAdapter mFuliAdapter;
    private int page = 1;


    public FuliFragment() {
        // Required empty public constructor
    }


    @Override
    protected GankPresenter<GankView> createPresemter() {
        return new GankPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_fuli;
    }

    @Override
    protected void initData() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        viewMain.setLayoutManager(staggeredGridLayoutManager);
        mFuliAdapter = new FuliAdapter(mActivity, mList);
        viewMain.setAdapter(mFuliAdapter);
        viewMain.setLoadingListener(this);
        presenter.getGankGril(10,page);
    }

    @Override
    public void showGank(List<GankItemBean.ResultsBean> list) {

    }

    @Override
    public void showGankRandom(List<GankHttpResponse.ResultsBean> list) {

    }

    @Override
    public void showGankGirl(List<GankHttpResponse.ResultsBean> list) {
        mFuliAdapter.addData(list,page);
        viewMain.loadMoreComplete();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onRefresh() {
        page=1;
        presenter.getGankGril(10,page);
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getGankGril(10,page);
    }
}
