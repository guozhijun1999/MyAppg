package com.example.machenike.myappg.fragments.gankfragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.adapter.gankadapter.AndroidAdapter;
import com.example.machenike.myappg.base.fragment.BaseFragment;
import com.example.machenike.myappg.beans.gank.GankItemBean;
import com.example.machenike.myappg.presenter.GankPresenter;
import com.example.machenike.myappg.utils.ImageLoader;
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
public class IosFragment extends BaseFragment<GankView, GankPresenter<GankView>> implements GankView, XRecyclerView.LoadingListener {


    @BindView(R.id.iv_tech_blur)
    ImageView ivTechBlur;
    @BindView(R.id.tv_tech_copyright)
    TextView tvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<GankItemBean> mList=new ArrayList<>();
    private AndroidAdapter mAndroidAdapter;
    private int page = 1;
    private int position = 0;

    public IosFragment() {
        // Required empty public constructor
    }


    @Override
    protected GankPresenter<GankView> createPresemter() {
        return new GankPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_ios;
    }

    @Override
    protected void initData() {
        viewMain.setLayoutManager(new LinearLayoutManager(mActivity));
        mAndroidAdapter = new AndroidAdapter(mActivity, mList);
        viewMain.setAdapter(mAndroidAdapter);
        viewMain.setLoadingListener(this);

        presenter.getGankRandom(10);
        presenter.getGank("iOS",10,page);
    }

    @Override
    public void showGank(List<GankItemBean> list) {
        mAndroidAdapter.addData(list,page);
        viewMain.loadMoreComplete();
    }

    @Override
    public void showGankRandom(List<GankItemBean> list) {
        ImageLoader.load(mActivity,list.get(position).getUrl().toString(),ivTechBlur);
        tvTechCopyright.setText("by:"+list.get(position).getWho());
    }

    @Override
    public void showGankGirl(List<GankItemBean> list) {

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
        presenter.getGank("iOS",10,page);
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getGank("iOS",10,page);
    }
}
