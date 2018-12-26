package com.example.machenike.myappg.adapter.zhihuadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.beans.zhihu.DailyBeforeListBean;
import com.example.machenike.myappg.beans.zhihu.DailyListBean;
import com.example.machenike.myappg.utils.ImageLoader;
import com.example.machenike.myappg.utils.ZhihuDiffCallback;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int ITEM_TOP = 0; //滚动栏
    private static final int ITEM_DATE = 1;//日期
    private static final int ITEM_CONTENT = 2; //内容
    private List<DailyListBean.TopStoriesBean> mTopStoriesBeans;
    private Context context;
    private List<DailyListBean.StoriesBean> mStoriesBeans;
    private LayoutInflater layoutInflater;
    private boolean isBefore =false;
    private TopPagerAdapter mTopPagerAdapter;
    private String currentTitle = "今日热闻";
    private ViewPager topViewPager;


    public DailyAdapter(Context context, List<DailyListBean.StoriesBean> storiesBeans) {
        this.context = context;
        mStoriesBeans = storiesBeans;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (isBefore){
            if (position == 0){
                return ITEM_DATE;
            }else {
                return ITEM_CONTENT;
            }
        }else {
            if (position == 0){
                return ITEM_TOP;
            }else if (position == 1){
                return ITEM_DATE;
            }else {
                return ITEM_CONTENT;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TOP){
            mTopPagerAdapter = new TopPagerAdapter(context, mTopStoriesBeans);
            return new TopViewHolder(layoutInflater.inflate(R.layout.itme_top,null));
        }else if (viewType == ITEM_DATE){
            return new DateViewHolder(layoutInflater.inflate(R.layout.itme_date,parent,false));
        }
        return new ContentViewHolder(layoutInflater.inflate(R.layout.itme_daily,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ContentViewHolder){
                int contentPosition;
                if (isBefore){
                    contentPosition=position - 1;
                }else {
                    contentPosition=position - 2;
                }
                ((ContentViewHolder) holder).mTitle.setText(mStoriesBeans.get(contentPosition).getTitle());
                ImageLoader.load(context,mStoriesBeans.get(contentPosition).getImages().get(0),((ContentViewHolder) holder).mImage);
            }else if(holder instanceof DateViewHolder){
                ((DateViewHolder) holder).mTvDate.setText(currentTitle);
            }else {
                ((TopViewHolder) holder).vpTop.setAdapter(mTopPagerAdapter);
                topViewPager= ((TopViewHolder) holder).vpTop;
            }
    }

    @Override
    public int getItemCount() {
        return mStoriesBeans.size();
    }
    //追加数据
    public void addDailuDate(DailyListBean dailyListBean) {
        currentTitle="今日热闻";
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ZhihuDiffCallback(mStoriesBeans, dailyListBean.getStories()), true);
        mStoriesBeans=dailyListBean.getStories();
        mTopStoriesBeans=dailyListBean.getTop_stories();
        diffResult.dispatchUpdatesTo( this);
        isBefore=false;
    }
    //根据指定日期追加更新数据
    public void addDailyBeforeDate(DailyBeforeListBean dailyBeforeListBean) {
        currentTitle = dailyBeforeListBean.getDate();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ZhihuDiffCallback(mStoriesBeans, dailyBeforeListBean.getStories()), true);
        mStoriesBeans = dailyBeforeListBean.getStories();
        isBefore = true;
        //通知UI更新
        diffResult.dispatchUpdatesTo(this);
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        private final ImageView mImage;
        public ContentViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_daily_item_title);
            mImage = itemView.findViewById(R.id.iv_daily_item_image);
        }

    }
    public class DateViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTvDate;

        public DateViewHolder(View itemView) {
            super(itemView);
            mTvDate = itemView.findViewById(R.id.tv_daily_date);
        }

    }
    public class TopViewHolder extends RecyclerView.ViewHolder {
        private final ViewPager vpTop;

        private final LinearLayout mLlContainer;
        public TopViewHolder(View itemView) {
            super(itemView);
            vpTop = itemView.findViewById(R.id.vp_top);
            mLlContainer = itemView.findViewById(R.id.ll_point_container);
        }



    }
}
