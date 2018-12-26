package com.example.machenike.myappg.fragments.gankfragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.adapter.gankadapter.GankAdapter;
import com.example.machenike.myappg.base.fragment.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends SimpleFragment {


    @BindView(R.id.tab_gank_main)
    TabLayout tabGankMain;
    @BindView(R.id.vp_gank_main)
    ViewPager vpGankMain;
    private List<Fragment> mList=new ArrayList<>();
    private AndroidFragment mAndroidFragment;
    private IosFragment mIosFragment;
    private QianFragment mQianFragment;
    private FuliFragment mFuliFragment;
    public static String[] tabTitle = new String[]{"Android","iOS","前端","福利"};
    public GankFragment() {
        // Required empty public constructor
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initData() {
        mAndroidFragment = new AndroidFragment();
        mIosFragment = new IosFragment();
        mQianFragment = new QianFragment();
        mFuliFragment = new FuliFragment();
        mList.add(mAndroidFragment);
        mList.add(mIosFragment);
        mList.add(mQianFragment);
        mList.add(mFuliFragment);

        tabGankMain.addTab(tabGankMain.newTab().setText(tabTitle[0]));
        tabGankMain.addTab(tabGankMain.newTab().setText(tabTitle[1]));
        tabGankMain.addTab(tabGankMain.newTab().setText(tabTitle[2]));
        tabGankMain.addTab(tabGankMain.newTab().setText(tabTitle[3]));
        tabGankMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpGankMain.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vpGankMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabGankMain));
        vpGankMain.setAdapter(new GankAdapter(getFragmentManager(),mList));
    }

}
