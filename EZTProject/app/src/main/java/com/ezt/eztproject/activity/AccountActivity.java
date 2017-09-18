package com.ezt.eztproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class AccountActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private RelativeLayout feekBackRL,findSafePassRL,confirmIdentityRL,changePhoneRL,aboutUsRL,myAddressRL;

    @Override
    protected void init() {
        setContentView(R.layout.activity_account);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        feekBackRL = (RelativeLayout)findViewById(R.id.me_feedback_pre);
        findSafePassRL = (RelativeLayout)findViewById(R.id.find_safepass_rl);
        confirmIdentityRL = (RelativeLayout)findViewById(R.id.confirm_identity_rl);
        changePhoneRL = (RelativeLayout)findViewById(R.id.pre_change_phone);
        aboutUsRL = (RelativeLayout)findViewById(R.id.about_us_rl);
        myAddressRL = (RelativeLayout)findViewById(R.id.my_address_rl);
    }

    @Override
    protected void setListener() {
        feekBackRL.setOnClickListener(this);
        findSafePassRL.setOnClickListener(this);
        confirmIdentityRL.setOnClickListener(this);
        changePhoneRL.setOnClickListener(this);
        aboutUsRL.setOnClickListener(this);
        myAddressRL.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.me_feedback_pre:
                intent.setClass(mContext,FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.find_safepass_rl:
                intent.setClass(mContext,SetSafeActivity.class);
                startActivity(intent);
                break;
            case R.id.confirm_identity_rl:
                intent.setClass(mContext,VerfityActivity.class);
                startActivity(intent);
                break;
            case R.id.pre_change_phone:
                intent.setClass(mContext,ChangePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.about_us_rl:
                intent.setClass(mContext,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.my_address_rl:
                intent.setClass(mContext,PersonalAddressActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
