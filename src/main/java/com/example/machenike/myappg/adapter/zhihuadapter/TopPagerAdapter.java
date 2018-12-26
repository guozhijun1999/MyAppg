package com.example.machenike.myappg.adapter.zhihuadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TopPagerAdapter extends PagerAdapter {
    private Context context;
    private List<DailyListBean.TopStoriesBean> mList=new ArrayList<>();

    public TopPagerAdapter(Context context, List<DailyListBean.TopStoriesBean> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.itme_top_pager,container,false);
        ImageView ivImge = view.findViewById(R.id.iv_top_image);
        TextView tvTitle = view.findViewById(R.id.tv_top_title);
        ImageLoader.load(context,mList.get(position).getImage(),ivImge);
        tvTitle.setText(mList.get(position).getTitle());
        int id=mList.get(position).getId();
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
