package com.ezt.eztproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.R;
import com.ezt.eztproject.activity.AccountActivity;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class MineFragment extends EZTBaseFragment {
    private RelativeLayout accountManager;

    @Override
    protected void initView() {
        accountManager = (RelativeLayout) mView.findViewById(R.id.me_mange_account);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setListener() {
        accountManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(mContext, AccountActivity.class);
                startActivity(intent);
            }
        });
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
}
