package com.ezt.eztproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Sunshine on 2017/8/18.
 */

public class EasePayViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;
    private List<String> list_Title;
    private FragmentManager manager;

    public EasePayViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.manager = fm;
    }

    public EasePayViewPagerAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
        this.manager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position % list_Title.size());
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment fragment = list_fragment.get(position);
//        //判断当前的fragment是否已经被添加进入Fragmentanager管理器中
//        if (!fragment.isAdded()) {
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(fragment, fragment.getClass().getSimpleName());
//            transaction.commitAllowingStateLoss();
//            manager.executePendingTransactions();
//        }
//        if (fragment.getView().getParent() == null) {
//            container.addView(fragment.getView());
//        }
//        return fragment.getView();
//
//    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(list_fragment.get(position).getView());
    }
}
