package com.example.machenike.myappg.fragments.zhihufragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.adapter.zhihuadapter.HotAdapter;
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
public class HotFragment extends BaseFragment<ZhihuView, ZhihuPresenter<ZhihuView>> implements ZhihuView, XRecyclerView.LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    List<HotListBean.RecentBean> mList=new ArrayList<>();
    private HotAdapter mHotAdapter;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    protected ZhihuPresenter<ZhihuView> createPresemter() {
        return new ZhihuPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        viewMain.setLayoutManager(new LinearLayoutManager(mActivity));
        mHotAdapter = new HotAdapter(mActivity,mList);
        viewMain.setAdapter(mHotAdapter);
        viewMain.setLoadingListener(this);
        presenter.getHotListBean();
    }

    @Override
    public void show(DailyListBean dailyListBean) {

    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {

    }

    @Override
    public void showSection(SectionListBean sectionListBean) {

    }

    @Override
    public void showHot(HotListBean hotListBean) {
        mHotAdapter.addData(hotListBean);
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
        presenter.getHotListBean();
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        viewMain.loadMoreComplete();
    }
}
