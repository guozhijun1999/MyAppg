package com.example.machenike.myappg.fragments.zhihufragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.base.fragment.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends SimpleFragment {


    @BindView(R.id.tab_zhihu_main)
    TabLayout tabZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager vpZhihuMain;

    String[] tabTitle = new String[]{"日报","专栏","热门"};
    List<Fragment> mFragments =new ArrayList<>();
    public ZhihuFragment() {
        // Required empty public constructor
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initData() {
        mFragments.add(new DailyFragment());
        mFragments.add(new SectionFragment());
        mFragments.add(new HotFragment());
        vpZhihuMain.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[0]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[1]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[2]));
        tabZhihuMain.setupWithViewPager(vpZhihuMain);
        tabZhihuMain.getTabAt(0).setText(tabTitle[0]);
        tabZhihuMain.getTabAt(1).setText(tabTitle[1]);
        tabZhihuMain.getTabAt(2).setText(tabTitle[2]);
//        tabZhihuMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                vpZhihuMain.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        vpZhihuMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabZhihuMain));
    }


}
