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
import com.example.machenike.myappg.beans.zhihu.SectionListBean;
import com.example.machenike.myappg.utils.ImageLoader;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    private Context context;
    private List<SectionListBean.DataBean> mList;
    private LayoutInflater layoutInflater;

    public SectionAdapter(Context context, List<SectionListBean.DataBean> list) {
        this.context = context;
        mList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.itme_section,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.ViewHolder holder, int position) {
        SectionListBean.DataBean dataBean = mList.get(position);
        ImageLoader.load(context,dataBean.getThumbnail(),holder.mImage);
        holder.mTvKind.setText(dataBean.getName());
        holder.mTvdes.setText(dataBean.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(SectionListBean sectionListBean) {
        mList.clear();
        mList.addAll(sectionListBean.getData());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTvKind;
        private final TextView mTvdes;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.section_bg);
            mTvKind = itemView.findViewById(R.id.section_kind);
            mTvdes = itemView.findViewById(R.id.section_des);
        }
    }
}
