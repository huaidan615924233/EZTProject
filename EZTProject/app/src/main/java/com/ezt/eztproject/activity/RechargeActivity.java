package com.ezt.eztproject.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class RechargeActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private RadioButton alipayRB, wechatRB;
    private RelativeLayout alipayRL, wechatRL;

    @Override
    protected void init() {
        setContentView(R.layout.activity_recharge);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("账户充值");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        alipayRB = (RadioButton) findViewById(R.id.rb_sele_alipay);
        wechatRB = (RadioButton) findViewById(R.id.rb_sele_wechat);
        alipayRL = (RelativeLayout) findViewById(R.id.epay_alipay);
        wechatRL = (RelativeLayout) findViewById(R.id.epay_wechat);
        alipayRB.setChecked(true);
    }

    @Override
    protected void setListener() {
        alipayRL.setOnClickListener(this);
        wechatRL.setOnClickListener(this);
        alipayRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    wechatRB.setChecked(false);
            }
        });
        wechatRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    alipayRB.setChecked(false);
            }
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.epay_alipay:
                alipayRB.setChecked(true);
                break;
            case R.id.epay_wechat:
                wechatRB.setChecked(true);
                break;
            default:
                break;
        }
    }
}
