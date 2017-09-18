package com.ezt.eztproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.R;
import com.ezt.eztproject.activity.IntegerProductDetailActivity;
import com.ezt.eztproject.adapter.EasePayAdapter;
import com.ezt.eztproject.adapter.IntegerListAdapter;
import com.ezt.eztproject.bean.EasePayInfo;
import com.ezt.eztproject.bean.IntegerProduct;

import java.util.ArrayList;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class EasePayBeforeUsedFragment extends EZTBaseFragment implements View.OnClickListener {
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
        EasePayInfo info4 = new EasePayInfo();
        info4.setTitle("注册就送100元");
        info4.setDesc("限西安地区使用");
        info4.setUsedData("2019-8-18");
        info4.setPrice("100.00");
        EasePayInfo info3 = new EasePayInfo();
        info3.setTitle("加油抵扣10元");
        info3.setDesc("全国通用");
        info3.setUsedData("2019-8-18");
        info3.setPrice("10.00");
        EasePayInfo info2 = new EasePayInfo();
        info2.setTitle("注册就送100元");
        info2.setDesc("全国通用");
        info2.setUsedData("2019-8-18");
        info2.setPrice("100.00");
        data.add(info1);
        data.add(info2);
        data.add(info3);
        data.add(info4);
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
