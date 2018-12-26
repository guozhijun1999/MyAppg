package com.example.machenike.myappg.fragments.weichatfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.machenike.myappg.MainActivity;
import com.example.machenike.myappg.R;
import com.example.machenike.myappg.adapter.weichatadapter.WechatAdapter;
import com.example.machenike.myappg.base.fragment.BaseFragment;
import com.example.machenike.myappg.beans.wechat.WXItemBean;
import com.example.machenike.myappg.presenter.WechatPresenter;
import com.example.machenike.myappg.view.WechatView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiChatFragment extends BaseFragment<WechatView, WechatPresenter<WechatView>> implements WechatView, XRecyclerView.LoadingListener {

    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    private List<WXItemBean> mList = new ArrayList<>();
    private int page = 1;
    private String word;
    private WechatAdapter mWechatAdapter;

    public WeiChatFragment() {
        // Required empty public constructor
    }


    @Override
    protected WechatPresenter<WechatView> createPresemter() {
        return new WechatPresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_wei_chat;
    }

    @Override
    protected void initData() {
        viewMain.setLayoutManager(new LinearLayoutManager(mActivity));
        mWechatAdapter = new WechatAdapter(mActivity, mList);
        viewMain.setAdapter(mWechatAdapter);
        viewMain.setLoadingListener(this);
        presenter.getWechat("52b7ec3471ac3bec6846577e79f20e4c", 10, page);
        presenter.getMoewWechat("52b7ec3471ac3bec6846577e79f20e4c", 10, page,word);

        MainActivity.mViewSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("qwe123",query.toString());

                query=word;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void showWechat(List<WXItemBean> list) {
        mWechatAdapter.addData(page, list);
        viewMain.loadMoreComplete();
    }

    @Override
    public void showMoewWechat(List<WXItemBean> list,String word) {

        mWechatAdapter.addData2(list);
        Log.e("qwe456",list.toString());
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
        presenter.getWechat("52b7ec3471ac3bec6846577e79f20e4c", 10, page);
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getWechat("52b7ec3471ac3bec6846577e79f20e4c", 10, page);

    }

}
