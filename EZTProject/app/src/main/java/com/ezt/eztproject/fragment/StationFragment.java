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
import com.ezt.eztproject.activity.GasSationDetailActivity;
import com.ezt.eztproject.adapter.GasStationListAdapter;
import com.ezt.eztproject.bean.GasStationInfo;

import java.util.ArrayList;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class StationFragment extends EZTBaseFragment {
    private ListView mListView;
    private GasStationListAdapter adapter;
    private ArrayList<GasStationInfo> data;

    @Override
    protected void initView() {
        mListView = (ListView) mView.findViewById(R.id.mGasStation);
    }

    @Override
    protected void getData() {
        data = new ArrayList<>();

        GasStationInfo info1 = new GasStationInfo();
        info1.setName("百度加气站");
        info1.setTel("010-88888888");
        info1.setAddress("北京市海淀区百度大厦");
        GasStationInfo info2 = new GasStationInfo();
        info2.setName("天津加气站");
        info2.setTel("13555555555");
        info2.setAddress("天津天津天津");
        GasStationInfo info3 = new GasStationInfo();
        info3.setName("太平洋加气站");
        info3.setTel("010-88888888");
        info3.setAddress("北京市海淀区百度大厦");
        GasStationInfo info4 = new GasStationInfo();
        info4.setName("实惠加气站");
        info4.setTel("010-88888888");
        info4.setAddress("北京市海淀区百度大厦");
        GasStationInfo info5 = new GasStationInfo();
        info5.setName("天天来加气站");
        info5.setTel("010-88888888");
        info5.setAddress("北京市海淀区百度大厦");
        data.add(info1);
        data.add(info2);
        data.add(info3);
        data.add(info4);
        data.add(info5);
        adapter = new GasStationListAdapter(mContext);
        adapter.setDatas(data);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GasStationInfo info = data.get(position);
                Intent intent = new Intent();
                intent.setClass(mContext, GasSationDetailActivity.class);
                intent.putExtra("stationName",info.getName());
                intent.putExtra("stationTel",info.getTel());
                intent.putExtra("stationAddress",info.getAddress());
                startActivity(intent);
            }
        });
    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater
                    .inflate(R.layout.fragment_gas_station, container, false);

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
