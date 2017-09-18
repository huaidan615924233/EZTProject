package com.ezt.eztproject.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.EasePayViewPagerAdapter;
import com.ezt.eztproject.adapter.MyCarsAdapter;
import com.ezt.eztproject.bean.CarInfo;
import com.ezt.eztproject.fragment.EasePayAfterUsedFragment;
import com.ezt.eztproject.fragment.EasePayBeforeUsedFragment;
import com.ezt.eztproject.fragment.EasePayUnUsedFragment;
import com.ezt.eztproject.view.TopBar;

import java.util.ArrayList;
import java.util.List;

public class PersonalEasePayActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private EasePayViewPagerAdapter adapter;
    private List<Fragment> list_fragment;
    private List<String> list_title;
    private EasePayBeforeUsedFragment easePayBeforeUsedFragment;
    private EasePayAfterUsedFragment easePayAfterUsedFragment;
    private EasePayUnUsedFragment easePayUnUsedFragment;

    @Override
    protected void init() {
        setContentView(R.layout.activity_myepay);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("我的优惠券");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void getData() {
        easePayAfterUsedFragment = new EasePayAfterUsedFragment();
        easePayBeforeUsedFragment = new EasePayBeforeUsedFragment();
        easePayUnUsedFragment = new EasePayUnUsedFragment();
        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(easePayBeforeUsedFragment);
        list_fragment.add(easePayAfterUsedFragment);
        list_fragment.add(easePayUnUsedFragment);
        list_title = new ArrayList<>();
        list_title.add("未使用(" + 4 + ")");
        list_title.add("已使用(" + 1 + ")");
        list_title.add("已过期(" + 0 + ")");
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));
        adapter = new EasePayViewPagerAdapter(getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            default:
                break;
        }
    }
}
