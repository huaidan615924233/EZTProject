package com.ezt.eztproject.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.EasePayAdapter;
import com.ezt.eztproject.bean.EasePayInfo;

import java.util.ArrayList;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class EasePayAfterUsedFragment extends EZTBaseFragment implements View.OnClickListener {
    private ListView mListView;
    private ArrayList<EasePayInfo> data = new ArrayList<>();
    private EasePayAdapter adapter;

    @Override
    protected void initView() {
        mListView = (ListView) mView.findViewById(R.id.mEasePayLV);
    }

    @Override
    protected void getData() {
        adapter = new EasePayAdapter(mContext);
        EasePayInfo info1 = new EasePayInfo();
        info1.setTitle("注册就送100元");
        info1.setDesc("全国通用");
        info1.setUsedData("2019-8-18");
        info1.setPrice("100.00");
        data.add(info1);
        adapter.setDatas(data);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater
                    .inflate(R.layout.fragment_easepay, container, false);

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
