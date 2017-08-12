package com.ezt.eztproject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Zhaop
 * @describe 页面的初始化操作 JackBall 初始化F
 * @date: 2016年1月21日 下午2:58:25 <br/>
 */
public abstract class EZTBaseFragment extends Fragment implements OnSharedPreferenceChangeListener {

    protected MyApplication myApplication;

    protected Context mContext;

    protected FragmentActivity mActivity;

    protected FragmentManager mManager;

    protected InputMethodManager inputMethodManager;

    protected boolean isConflict;

    protected View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mActivity = (FragmentActivity) activity;
        mManager = mActivity.getSupportFragmentManager();
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 设置屏幕
        myApplication = MyApplication.getInstance();
        return setView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        getData();
        setListener();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isConflict) {
            outState.putBoolean("isConflict", true);
        }
    }

    protected abstract void initView();

    protected abstract void getData();

    protected abstract void setListener();

    protected abstract View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * @param
     * @author Zhaop
     * @Description 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 展示View
     *
     * @param view
     * @return
     */
    protected EZTBaseFragment show(final View view) {
        view.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 隐藏View
     *
     * @param view
     * @return
     */
    protected EZTBaseFragment hide(final View view) {
        view.setVisibility(View.INVISIBLE);
        return this;
    }

}
