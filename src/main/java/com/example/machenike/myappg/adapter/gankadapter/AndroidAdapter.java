package com.example.machenike.myappg.adapter.gankadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.beans.gank.GankItemBean;
import com.example.machenike.myappg.fragments.gankfragment.GankFragment;
import com.example.machenike.myappg.utils.DateUtil;
import com.example.machenike.myappg.utils.ImageLoader;

import java.util.List;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder> {
    private Context context;
    private List<GankItemBean> mList;
    private LayoutInflater mInflater;


    public AndroidAdapter(Context context, List<GankItemBean> list) {
        this.context = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.itme_android,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GankItemBean gankItemBean = mList.get(position);
//        if (tech.equals(GankFragment.tabTitle[0])){
//            Log.e("321321",GankFragment.tabTitle[0].toString());
//            holder.mImage.setImageResource(R.mipmap.ic_android);
//        }else if (tech.equals(GankFragment.tabTitle[1])){
//            holder.mImage.setImageResource(R.mipmap.ic_ios);
//        }else if (tech.equals(GankFragment.tabTitle[2])){
//            holder.mImage.setImageResource(R.mipmap.ic_web);
//        }
        holder.mTitle.setText(gankItemBean.getDesc());
        holder.mAuthor.setText(gankItemBean.getWho());
        holder.mTime.setText(DateUtil.formatDateTime(DateUtil.subStandardTime(gankItemBean.getPublishedAt()),true));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<GankItemBean> list, int page) {
        if (page == 1){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mAuthor;
        private final TextView mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.iv_tech_icon);
            mTitle = itemView.findViewById(R.id.tv_tech_title);
            mAuthor = itemView.findViewById(R.id.tv_tech_author);
            mTime = itemView.findViewById(R.id.tv_tech_time);
        }
    }
}
