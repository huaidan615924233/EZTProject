package com.ezt.eztproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @describe Activity父类
 * @author Zhaop
 */
public abstract class EZTBaseActivity extends FragmentActivity implements OnClickListener {

    protected Context mContext;

    protected MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        myApplication = MyApplication.getInstance();
        init();
        setListener();
        getData();
    }

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 获取数据
     */
    protected abstract void getData();

    @Override
    public void onClick(View v) {

    }

}
