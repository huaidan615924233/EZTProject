package com.ezt.eztproject.activity;

import android.view.View;
import android.widget.ImageView;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;

public class AccountActivity extends EZTBaseActivity {
    private ImageView backButton;

    @Override
    protected void init() {
        setContentView(R.layout.activity_account);
        backButton = (ImageView) findViewById(R.id.action_left);
    }

    @Override
    protected void setListener() {
        backButton.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.action_left:
                finish();
                break;
            default:
                break;
        }
    }
}
