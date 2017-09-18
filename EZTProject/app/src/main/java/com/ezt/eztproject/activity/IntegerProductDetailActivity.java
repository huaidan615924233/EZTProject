package com.ezt.eztproject.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.NewsAdapter;
import com.ezt.eztproject.bean.NewsInfo;
import com.ezt.eztproject.view.TopBar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IntegerProductDetailActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private TextView beforePriceTV;
    private String productName, needInteger, productDesc, productIcon, productPrice;
    private ImageView productIconIV;
    private TextView productNameTV, productDescTV, needIntegerTV;

    @Override
    protected void init() {
        setContentView(R.layout.activity_integer_product_detail);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("商品详情");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        beforePriceTV = (TextView) findViewById(R.id.before_priceTV);
        beforePriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        productNameTV = (TextView) findViewById(R.id.product_name);
        needIntegerTV = (TextView) findViewById(R.id.product_integer);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            needInteger = extras.getString("needInteger");
            productName = extras.getString("productName");
            productPrice = extras.getString("productPrice");
        }
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void getData() {
        productNameTV.setText(productName);
        beforePriceTV.setText("￥" + productPrice + ".00");
        needIntegerTV.setText(needInteger);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            default:
                break;
        }
    }
}
