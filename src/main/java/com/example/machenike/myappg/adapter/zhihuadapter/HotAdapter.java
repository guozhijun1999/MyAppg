package com.example.machenike.myappg.adapter.zhihuadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.beans.zhihu.HotListBean;
import com.example.machenike.myappg.utils.ImageLoader;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private Context context;
    private List<HotListBean.RecentBean> mList;
    private LayoutInflater layoutInflater;

    public HotAdapter(Context context, List<HotListBean.RecentBean> list) {
        this.context = context;
        mList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.itme_hot,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotListBean.RecentBean recentBean = mList.get(position);
        ImageLoader.load(context,recentBean.getThumbnail(),holder.mImage);
        holder.mTitle.setText(recentBean.getTitle());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(HotListBean hotListBean) {
        mList.clear();
        mList.addAll(hotListBean.getRecent());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.iv_daily_item_image);
            mTitle = itemView.findViewById(R.id.tv_daily_item_title);
        }
    }
}
