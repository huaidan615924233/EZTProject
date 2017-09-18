package com.ezt.eztproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ezt.eztproject.EZTBaseFragment;
import com.ezt.eztproject.R;
import com.ezt.eztproject.activity.GasSationDetailActivity;
import com.ezt.eztproject.activity.IntegerProductDetailActivity;
import com.ezt.eztproject.adapter.IntegerListAdapter;
import com.ezt.eztproject.bean.GasStationInfo;
import com.ezt.eztproject.bean.IntegerProduct;

import java.util.ArrayList;

/**
 * Created by Sunshine on 2017/7/31.
 */

public class IntegerFragment extends EZTBaseFragment implements View.OnClickListener {
    private ListView mListView;
    private IntegerListAdapter adapter;
    private ArrayList<IntegerProduct> data = new ArrayList<>();

    @Override
    protected void initView() {
        mListView = (ListView) mView.findViewById(R.id.mListView);
    }

    @Override
    protected void getData() {
        IntegerProduct product = new IntegerProduct();
        product.setNeedInteger("15000");
        product.setProductName("宝马专用机油 20L");
        product.setProductPrice("250");
        IntegerProduct product2 = new IntegerProduct();
        product2.setNeedInteger("5000");
        product2.setProductName("奥迪润滑油");
        product2.setProductPrice("200");
        IntegerProduct product3 = new IntegerProduct();
        product3.setNeedInteger("8000");
        product3.setProductName("加油卡");
        product3.setProductPrice("1000");
        IntegerProduct product4 = new IntegerProduct();
        product4.setNeedInteger("20000");
        product4.setProductName("电饭锅");
        product4.setProductPrice("500");
        data.add(product);
        data.add(product2);
        data.add(product3);
        data.add(product4);
        adapter = new IntegerListAdapter(mContext);
        adapter.setDatas(data);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntegerProduct info = data.get(position);
                Intent intent = new Intent();
                intent.setClass(mContext, IntegerProductDetailActivity.class);
//                intent.putExtra("productIcon", info.getProductIcon());
                intent.putExtra("productName", info.getProductName());
                intent.putExtra("needInteger", info.getNeedInteger());
                intent.putExtra("productPrice", info.getProductPrice());
//                intent.putExtra("productDesc", info.getProductDesc());
                startActivity(intent);
            }
        });
    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater
                    .inflate(R.layout.fragment_integer, container, false);

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
