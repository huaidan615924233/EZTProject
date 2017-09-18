package com.ezt.eztproject.activity;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.ezt.eztproject.EZTBaseActivity;
import com.ezt.eztproject.R;
import com.ezt.eztproject.adapter.PayTypeAdapter;
import com.ezt.eztproject.view.TopBar;

import java.util.ArrayList;

public class RechargeHistoryActivity extends EZTBaseActivity {
    private TopBar titleBar;
    private RelativeLayout payTypeChooseRL;
    private PopupWindow mPopupWindow;

    @Override
    protected void init() {
        setContentView(R.layout.activity_recharge_history);
        titleBar = (TopBar) findViewById(R.id.title_bar);
        titleBar.setTitleIVVisibility(View.INVISIBLE);
        titleBar.setTitleVisibility(View.VISIBLE);
        titleBar.setTitle("充值记录");
        titleBar.setBackOnClickListener(new TopBar.BackClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        payTypeChooseRL = (RelativeLayout) findViewById(R.id.epay_check_type1);
        View view = View.inflate(mContext, R.layout.pop_pay_type, null);
        mPopupWindow = new PopupWindow(view,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
//        mSwitchPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext
//                .getResources(), Bitmap.createBitmap(1, 1,
//                Bitmap.Config.ARGB_8888)));
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        ListView payTypeLV = (ListView) view.findViewById(R.id.pay_type_listLV);
        PayTypeAdapter adapter = new PayTypeAdapter(mContext);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        adapter.setDatas(data);
        payTypeLV.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        payTypeChooseRL.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.epay_check_type1:
                if (!mPopupWindow.isShowing())
                    showAtLocation(mPopupWindow, titleBar);
            default:
                break;
        }
    }

    public void showAtLocation(PopupWindow mPopupWindow, View parent) {
        setBackgroundAlpha(0.5f);//设置屏幕透明度

//        mPopupWindow.showAsDropDown(parent, 10, 10, Gravity.CENTER_HORIZONTAL);
        // 使其聚集
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
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
