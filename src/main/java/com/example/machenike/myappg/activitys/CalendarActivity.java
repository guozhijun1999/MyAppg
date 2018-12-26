package com.example.machenike.myappg.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.machenike.myappg.R;
import com.example.machenike.myappg.base.activity.SimpleActivity;
import com.example.machenike.myappg.utils.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_calender)
    MaterialCalendarView viewCalender;
    @BindView(R.id.tv_calender_enter)
    TextView tvCalenderEnter;
    CalendarDay mDate;

    @Override
    public void initData() {
        setToolBar(toolBar,"选择日期");
        viewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        viewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
    }

    @Override
    public void viewCreated() {

    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_calendar;
    }

    @OnClick(R.id.tv_calender_enter)
    public void onViewClicked() {
        if (mDate != null) {
            EventBus.getDefault().post(mDate);
        }
        finish();
    }

}
