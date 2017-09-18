package com.ezt.eztproject.activity;

import android.view.View;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class PayHistoryActivity extends EZTBaseActivity {
    private TopBar titleBar;

    @Override
    protected void init() {
        setContentView(R.layout.activity_epay_history);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("消费记录");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
