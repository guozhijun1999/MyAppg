package com.example.machenike.myappg.adapter.weichatadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.beans.wechat.WXItemBean;
import com.example.machenike.myappg.utils.ImageLoader;

import java.util.List;

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHolder> {
    private Context context;
    private List<WXItemBean> mList;
    private LayoutInflater mInflater;

    public WechatAdapter(Context context, List<WXItemBean> list) {
        this.context = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.itme_wechat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WXItemBean wxItemBean = mList.get(position);
        ImageLoader.load(context,wxItemBean.getPicUrl(),holder.mImage);
        holder.mTitle.setText(wxItemBean.getTitle());
        holder.mTime.setText(wxItemBean.getCtime());
        holder.mFrom.setText(wxItemBean.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(int page, List<WXItemBean> list) {
        if (page ==1){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData2(List<WXItemBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mTime;
        private final TextView mFrom;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.iv_wechat_item_image);
            mTitle = itemView.findViewById(R.id.tv_wechat_item_title);
            mTime = itemView.findViewById(R.id.tv_wechat_item_time);
            mFrom = itemView.findViewById(R.id.tv_wechat_item_from);
        }
    }
}
