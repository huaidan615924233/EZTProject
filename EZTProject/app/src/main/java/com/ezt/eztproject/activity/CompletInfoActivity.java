package com.ezt.eztproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.MainActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.view.TopBar;

public class CompletInfoActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private Button completeBtn, switchBtn;
    private PopupWindow mSwitchPopupWindow;
    private RelativeLayout mainRL;

    @Override
    protected void init() {
        setContentView(R.layout.activity_completeinfo);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("完善信息");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        completeBtn = (Button) findViewById(R.id.btn_complete);
        switchBtn = (Button) findViewById(R.id.btn_switch);
        mainRL = (RelativeLayout)findViewById(R.id.main_rl);
        mSwitchPopupWindow = new PopupWindow(View.inflate(mContext, R.layout.pop_select_identity, null),
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
//        mSwitchPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext
//                .getResources(), Bitmap.createBitmap(1, 1,
//                Bitmap.Config.ARGB_8888)));
        mSwitchPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSwitchPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }

    @Override
    protected void setListener() {
        completeBtn.setOnClickListener(this);
        switchBtn.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.btn_complete:
                intent.setClass(mContext, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_switch:
                if (!mSwitchPopupWindow.isShowing())
                    showAsDropDown(mSwitchPopupWindow, titleBar);
                break;
            default:
                break;
        }
    }

    public void showAsDropDown(PopupWindow mPopupWindow, View parent) {
        setBackgroundAlpha(0.5f);//设置屏幕透明度

        mPopupWindow.showAsDropDown(parent, 50, 10,Gravity.CENTER_HORIZONTAL);
        // 使其聚集
        mPopupWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        // 刷新状态
        mPopupWindow.update();
    }

    // 隐藏菜单
    public void dismiss(PopupWindow mPopupWindow) {
        mPopupWindow.dismiss();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }
}
