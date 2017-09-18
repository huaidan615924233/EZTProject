package com.ezt.eztproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class PersonalAddressActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private TextView noDataTV;

    @Override
    protected void init() {
        setContentView(R.layout.activity_myaddress_list);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("收货地址");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.setMoreVisibility(View.VISIBLE);
        titleBar.setMoreOnClickListener(new TopBar.MoreClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext,AddAddressActivity.class);
                startActivity(intent);
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
