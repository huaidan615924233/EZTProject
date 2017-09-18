package com.ezt.eztproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.MyCarsAdapter;
import com.ezt.eztproject.bean.CarInfo;
import com.ezt.eztproject.view.TopBar;

import java.util.ArrayList;

public class PersonalCarListActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private ImageButton addCarIB;
    private ListView mCarsLV;
    private MyCarsAdapter adapter;
    private ArrayList<CarInfo> data = new ArrayList<>();

    @Override
    protected void init() {
        setContentView(R.layout.activity_carlist_personal);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("我的车辆");
        addCarIB = (ImageButton) findViewById(R.id.btn_add_car);
        mCarsLV = (ListView) findViewById(R.id.mCarsLV);
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {
        addCarIB.setOnClickListener(this);
    }

    @Override
    protected void getData() {
        adapter = new MyCarsAdapter(mContext);
        CarInfo info1 = new CarInfo();
        info1.setCarNumber("京 88888");
        CarInfo info2 = new CarInfo();
        info2.setCarNumber("京 11111");
        CarInfo info3 = new CarInfo();
        info3.setCarNumber("京 22222");
        CarInfo info4 = new CarInfo();
        info4.setCarNumber("京 33333");
        CarInfo info5 = new CarInfo();
        info5.setCarNumber("京 66666");
        data.add(info1);
        data.add(info2);
        data.add(info3);
        data.add(info4);
        data.add(info5);
        adapter.setDatas(data);
        mCarsLV.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.btn_add_car:
                intent.setClass(mContext, AddCarActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
