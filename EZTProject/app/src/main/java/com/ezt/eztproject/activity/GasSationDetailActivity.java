package com.ezt.eztproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.GasStationServiceListAdapter;
import com.ezt.eztproject.view.FixedGridView;
import com.ezt.eztproject.view.TopBar;

import java.util.ArrayList;

public class GasSationDetailActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private String stationName, stationTel, stationAddress;
    private TextView stationNameTV, stationTelTV, stationAddressTV, stationGoto;
    private FixedGridView servicesGV;
    private GasStationServiceListAdapter adapter;
    private ArrayList<Integer> data = new ArrayList<>();

    @Override
    protected void init() {
        setContentView(R.layout.activity_gassation_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            stationName = extras.getString("stationName");
            stationTel = extras.getString("stationTel");
            stationAddress = extras.getString("stationAddress");
        }
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("加气站详情");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        stationNameTV = (TextView) findViewById(R.id.a_gasstion_info_name);
        stationTelTV = (TextView) findViewById(R.id.e_gas_phone);
        stationAddressTV = (TextView) findViewById(R.id.e_gas_address);
        stationGoto = (TextView) findViewById(R.id.e_gas_goto);
        servicesGV = (FixedGridView) findViewById(R.id.servicesGV);
    }

    @Override
    protected void setListener() {
        stationGoto.setOnClickListener(this);
    }

    @Override
    protected void getData() {
        stationNameTV.setText(stationName);
        stationTelTV.setText(stationTel);
        stationAddressTV.setText(stationAddress);
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
        data.add(8);
        adapter = new GasStationServiceListAdapter(mContext);
        adapter.setDatas(data);
        servicesGV.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.e_gas_goto:
                break;
            default:
                break;
        }
    }
}
