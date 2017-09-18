package com.ezt.eztproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.MainActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.activity.NewsActivity;
import com.ezt.eztproject.activity.PayHistoryActivity;
import com.ezt.eztproject.activity.RechargeActivity;
import com.ezt.eztproject.activity.RechargeHistoryActivity;
import com.ezt.eztproject.activity.ScanActivity;
import com.ezt.eztproject.utils.ShareUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class MainFragment extends EZTBaseFragment implements View.OnClickListener {
    private ImageView scanIV, rechargeIV;
    private ImageButton rechargeIb, payHistoryIb, rechargeHistoryIb, shareIb, integerIb, newsIb, cargoHistoryIb, cargoSourceIb;

    @Override
    protected void initView() {
        scanIV = (ImageView) mView.findViewById(R.id.scan_code);
        rechargeIV = (ImageView) mView.findViewById(R.id.btn_recharge);
        rechargeIb = (ImageButton) mView.findViewById(R.id.recharge_ib);
        payHistoryIb = (ImageButton) mView.findViewById(R.id.pay_history_ib);
        rechargeHistoryIb = (ImageButton) mView.findViewById(R.id.recharge_history_ib);
        shareIb = (ImageButton) mView.findViewById(R.id.share_ib);
        integerIb = (ImageButton) mView.findViewById(R.id.integer_ib);
        newsIb = (ImageButton) mView.findViewById(R.id.news_ib);
        cargoHistoryIb = (ImageButton) mView.findViewById(R.id.cargo_history_ib);
        cargoSourceIb = (ImageButton) mView.findViewById(R.id.cargo_source_ib);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setListener() {
        scanIV.setOnClickListener(this);
        rechargeIV.setOnClickListener(this);
        rechargeIb.setOnClickListener(this);
        payHistoryIb.setOnClickListener(this);
        rechargeHistoryIb.setOnClickListener(this);
        shareIb.setOnClickListener(this);
        integerIb.setOnClickListener(this);
        newsIb.setOnClickListener(this);
        cargoHistoryIb.setOnClickListener(this);
        cargoSourceIb.setOnClickListener(this);
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
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.scan_code:
                intent.setClass(mContext, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.recharge_ib:
                intent.setClass(mContext, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recharge:
                intent.setClass(mContext, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.pay_history_ib:
                intent.setClass(mContext, PayHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.recharge_history_ib:
                intent.setClass(mContext, RechargeHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.share_ib:
                new ShareUtils().share(mActivity, "测试", "https://www.baidu.com/img/baidu_jgylogo3.gif", "测试分享EZT", "https://www.baidu.com", new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Toast.makeText(mContext, "share complete!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(mContext, "share error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(mContext, "share cancel!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.integer_ib:
                ((MainActivity) mActivity).mTabHost.setCurrentTabByTag("积分");
                break;
            case R.id.news_ib:
                intent.setClass(mContext, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.cargo_history_ib:
                Toast.makeText(mContext, "该功能正在开发中。。。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cargo_source_ib:
                Toast.makeText(mContext, "该功能正在开发中。。。", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
