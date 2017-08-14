package com.ezt.eztproject.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.MainActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.activity.ScanActivity;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class MainFragment extends EZTBaseFragment implements View.OnClickListener {
    private ImageView scanIV;
    private ImageButton rechargeIb, payHistoryIb, rechargeHistoryIb, shareIb, integerIb, newsIb;

    @Override
    protected void initView() {
        scanIV = (ImageView) mView.findViewById(R.id.scan_code);
        rechargeIb = (ImageButton) mView.findViewById(R.id.recharge_ib);
        payHistoryIb = (ImageButton) mView.findViewById(R.id.pay_history_ib);
        rechargeHistoryIb = (ImageButton) mView.findViewById(R.id.recharge_history_ib);
        shareIb = (ImageButton) mView.findViewById(R.id.share_ib);
        integerIb = (ImageButton) mView.findViewById(R.id.integer_ib);
        newsIb = (ImageButton) mView.findViewById(R.id.news_ib);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setListener() {
        rechargeIb.setOnClickListener(this);
        payHistoryIb.setOnClickListener(this);
        rechargeHistoryIb.setOnClickListener(this);
        shareIb.setOnClickListener(this);
        integerIb.setOnClickListener(this);
        newsIb.setOnClickListener(this);
    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater
                    .inflate(R.layout.fragment_index, container, false);

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
        int id = v.getId();
        switch (id) {
            case R.id.scan_code:
                Intent intent = new Intent();
                intent.setClass(mContext, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.recharge_ib:
                break;
            case R.id.pay_history_ib:
                break;
            case R.id.recharge_history_ib:
                break;
            case R.id.share_ib:
                break;
            case R.id.integer_ib:
                ((MainActivity) mActivity).mTabHost.setCurrentTabByTag("积分");
                break;
            case R.id.news_ib:
                break;
            default:
                break;
        }
    }
}
