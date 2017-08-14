package com.ezt.eztproject.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.R;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class IntegerFragment extends EZTBaseFragment implements View.OnClickListener{
    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
    }

    @Override
    protected void setListener() {

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

    }
}
