package com.ezt.eztproject.activity;

import android.view.View;
import android.widget.ImageView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;

public class ScanActivity extends EZTBaseActivity {

    @Override
    protected void init() {
        setContentView(R.layout.activity_qr_scan);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            default:
                break;
        }
    }
}
