package com.example.machenike.myappg.adapter.gankadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.machenike.myappg.R;
import com.example.machenike.myappg.beans.gank.GankItemBean;
import com.example.machenike.myappg.utils.ImageLoader;

import java.util.List;

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.ViewHolder> {
    private Context context;
    private List<GankItemBean> mList;
    private LayoutInflater mInflater;

    public FuliAdapter(Context context, List<GankItemBean> list) {
        this.context = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FuliAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.itme_gril,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FuliAdapter.ViewHolder holder, int position) {
        GankItemBean gankItemBean = mList.get(position);
//        ImageLoader.load(context,gankItemBean.getUrl(),holder.mImage);
        Glide.with(context).load(gankItemBean.getUrl()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<GankItemBean> list, int page) {
        if (page==1){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.iv_girl);
        }
    }
}
