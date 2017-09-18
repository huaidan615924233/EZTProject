package com.ezt.eztproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class PersonalOrdersActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private TextView noDataTV;

    @Override
    protected void init() {
        setContentView(R.layout.activity_myorders_list);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("我的订单");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        noDataTV = (TextView) findViewById(R.id.no_data_tv);
        noDataTV.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void getData() {

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
