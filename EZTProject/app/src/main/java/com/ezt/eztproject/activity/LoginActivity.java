package com.ezt.eztproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class LoginActivity extends EZTBaseActivity {
    private Button loginBtn;
    @Override
    protected void init() {
        setContentView(R.layout.activity_login);
        loginBtn = (Button)findViewById(R.id.login_button);
    }

    @Override
    protected void setListener() {
        loginBtn.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.login_button:
                intent.setClass(mContext,CompletInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
