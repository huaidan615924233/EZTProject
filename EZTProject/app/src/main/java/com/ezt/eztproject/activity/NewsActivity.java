package com.ezt.eztproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.NewsAdapter;
import com.ezt.eztproject.bean.NewsInfo;
import com.ezt.eztproject.view.TopBar;

import java.util.ArrayList;

public class NewsActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private ListView newsLV;
    private NewsAdapter adapter;
    private ArrayList<NewsInfo> data = new ArrayList<>();

    @Override
    protected void init() {
        setContentView(R.layout.activity_news);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("最新热点");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        newsLV = (ListView) findViewById(R.id.news_lv);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void getData() {
        NewsInfo info = new NewsInfo();
        info.setNewsTitle("E站途正式上线啦");
        info.setNewsData("2017-8-17");
        data.add(info);
        data.add(info);
        data.add(info);
        data.add(info);
        data.add(info);
        data.add(info);
        data.add(info);
        data.add(info);
        adapter = new NewsAdapter(mContext);
        adapter.setDatas(data);
        newsLV.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            default:
                break;
        }
    }
}
