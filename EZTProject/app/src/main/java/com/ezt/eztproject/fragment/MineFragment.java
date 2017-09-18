package com.ezt.eztproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.R;
import com.ezt.eztproject.activity.AccountActivity;
import com.ezt.eztproject.activity.PersonalCustomServiceActivity;
import com.ezt.eztproject.activity.PersonalEasePayActivity;
import com.ezt.eztproject.activity.PersonalOrdersActivity;
import com.ezt.eztproject.activity.PersonalCarListActivity;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class MineFragment extends EZTBaseFragment implements View.OnClickListener {
    private RelativeLayout accountManager,myCars,myOrders,myEasePay,myService;

    @Override
    protected void initView() {
        accountManager = (RelativeLayout) mView.findViewById(R.id.me_mange_account);
        myCars = (RelativeLayout) mView.findViewById(R.id.mycars_rl);
        myService = (RelativeLayout)mView.findViewById(R.id.myservice_rl);
        myOrders = (RelativeLayout)mView.findViewById(R.id.myorder_rl);
        myEasePay = (RelativeLayout)mView.findViewById(R.id.myeasepay_rl);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setListener() {
        accountManager.setOnClickListener(this);
        myCars.setOnClickListener(this);
        myService.setOnClickListener(this);
        myOrders.setOnClickListener(this);
        myEasePay.setOnClickListener(this);
    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater
                    .inflate(R.layout.fragment_mine, container, false);

        } else {
            if (mView.getParent() != null) {
                ((ViewGroup) mView.getParent()).removeView(mView);
            }
        }
        return mView;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    public void onClick(View v) {
        Intent intent  = new Intent();
        int id = v.getId();
        switch (id){
            case R.id.me_mange_account:
                intent.setClass(mContext, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.mycars_rl:
                intent.setClass(mContext, PersonalCarListActivity.class);
                startActivity(intent);
                break;
            case R.id.myservice_rl:
                intent.setClass(mContext, PersonalCustomServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.myorder_rl:
                intent.setClass(mContext, PersonalOrdersActivity.class);
                startActivity(intent);
                break;
            case R.id.myeasepay_rl:
                intent.setClass(mContext, PersonalEasePayActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
