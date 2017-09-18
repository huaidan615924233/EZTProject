package com.ezt.eztproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.ezt.eztproject.fragment.IntegerFragment;
import com.ezt.eztproject.fragment.MainFragment;
import com.ezt.eztproject.fragment.MineFragment;
import com.ezt.eztproject.fragment.StationFragment;
import com.ezt.eztproject.view.TopBar;

public class MainActivity extends FragmentActivity {
    private TopBar titleBar;
    public FragmentTabHost mTabHost;
    private TextView[] txtTabMsgCounts;
    private String[] tabString = {"首页", "加气站","积分", "我"};
    private int[] tabIconResources = {R.drawable.tab_index_btn001_selector, R.drawable.tab_index_btn002_selector,
            R.drawable.tab_index_btn003_selector,R.drawable.tab_index_btn004_selector};
    private Class[] tabFragment = {MainFragment.class, StationFragment.class,IntegerFragment.class,
            MineFragment.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleBar = (TopBar)findViewById(R.id.title_bar);
        titleBar.setBackVisibility(View.INVISIBLE);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
        txtTabMsgCounts = new TextView[tabFragment.length];
        for (int i = 0; i < tabFragment.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabString[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, tabFragment[i], null);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
            mTabHost.getTabWidget().getChildAt(i).setOnClickListener(onTabClick);
        }
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setCurrentTabByTag(tabString[0]);
    }
    private View.OnClickListener onTabClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int clickTabIndex = Integer.parseInt(view.getTag().toString());
            mTabHost.setCurrentTabByTag(tabString[clickTabIndex]);
        }
    };
    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_home_tab, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgTabIcon);
        imageView.setImageResource(tabIconResources[index]);
        txtTabMsgCounts[index] = (TextView) view.findViewById(R.id.txtTabMsgCount);
//        if (tabShowMsgNumber[index]) {
//            if (tabMsgNumber[index] > 0) {
//                txtTabMsgCounts[index].setText(tabMsgNumber[index] > 99 ? "99+" : String.valueOf(tabMsgNumber[index]));
//            } else {
//                txtTabMsgCounts[index].setVisibility(View.GONE);
//            }
//        } else {
//            if (tabMsgNumber[index] > 0) {
//                txtTabMsgCounts[index].setText("");
//                txtTabMsgCounts[index].setVisibility(View.VISIBLE);
//            } else {
//                txtTabMsgCounts[index].setVisibility(View.GONE);
//            }
//        }
        TextView textView = (TextView) view.findViewById(R.id.txtTabName);
        textView.setText(tabString[index]);
        view.setTag(index);
        return view;
    }
}
