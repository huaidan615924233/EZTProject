package com.ezt.eztproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ezt.eztproject.utils.MeasureUtil;
import com.ezt.eztproject.utils.PhoneInfoUtils;

import java.util.ArrayList;

/**
 * @describe Adapter Base
 * @author Zhaop
 * @date: 2015-10-16 下午3:56:37 <br/>
 * @param <T>
 */
public abstract class EZTBaseAdapter<T > extends BaseAdapter {

    protected Context mContext;

    protected ArrayList<T > datas;

    protected int screenWidth;// 屏幕宽 【px】

    protected int imageSize;// 图片大小

    protected int marginNum;// 图片间距

    public EZTBaseAdapter() {

    }

    public EZTBaseAdapter(Context context) {
        mContext = context;
        this.datas = new ArrayList<T >();
        fitScreen();
    }

    public EZTBaseAdapter(Context context, ArrayList<T > datas) {
        mContext = context;
        if(null != datas && 0 != datas.size()) {
            this.datas = datas;
        } else {
            this.datas = new ArrayList<T >();
        }
    }

    /**
     * 清除数据
     */
    public void cleanDatas() {
        if(datas != null && ! datas.isEmpty()) {
            datas.clear();
            onDatasChanged();
            notifyDataSetChanged();
        }
    }

    /**
     * 删除单条数据
     * @param position
     */
    public void deleteData(int position) {
        if(datas != null && ! datas.isEmpty()) {
            datas.remove(position);
            onDatasChanged();
            notifyDataSetChanged();
        }
    }

    public ArrayList<T > getDatas() {
        return this.datas;
    }

    /**
     * 绑定数据
     * @param datas
     */
    public void setDatas(ArrayList<T > datas) {
        this.datas = datas;
        onDatasChanged();
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param datas
     */
    public void addDatas(ArrayList<T > datas) {
        this.datas.addAll(datas);
        onDatasChanged();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(null != datas) {
            return datas.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 设置图片间距
    public int setImageMaginRight(int num) {
        return MeasureUtil.dip2px(mContext, num);
    }

    // 设置图片大小
    public int setImageSize(int size) {
        return MeasureUtil.dip2px(mContext, size);
    }

    // 屏幕适配
    public void fitScreen() {
        PhoneInfoUtils.getScreenResolution(mContext);
        screenWidth = PhoneInfoUtils.width;

        if(480 == screenWidth) {// 分辨率480*800 480*854
            marginNum = setImageMaginRight(5);
            imageSize = setImageSize(50);
        } else if(540 == screenWidth) {// 分辨率960*540
            marginNum = setImageMaginRight(6);
            imageSize = setImageSize(52);
        } else if(720 == screenWidth) {// 分辨率 1280*720
            marginNum = setImageMaginRight(6);
            imageSize = setImageSize(58);
        } else if(800 == screenWidth) {// 分辨率 1280*800
            marginNum = setImageMaginRight(6);
            imageSize = setImageSize(60);
        } else if(1080 == screenWidth) {// 分辨率 1920*1080
            marginNum = setImageMaginRight(7);
            imageSize = setImageSize(70);
        }
    }
    
    public  void onDatasChanged(){

    };

    /**
     * <p>
     * <p/>
     * 
     * <pre>
     * ViewHolder holder;
     * if(convertView == null) {
     *     holder = new ViewHolder();
     *     convertView = mInflater.inflate(R.layout.xxx, null);
     *     holder.tv = (TextView)convertView.findViewById(R.id.tv);
     *     convertView.setTag(holder);
     * } else {
     *     holder = (ViewHolder)convertView.getTag();
     * }
     * if(datas != null &amp;&amp; datas.size() &gt; 0) {
     *     holder.tv.setText(getItem(position).getTv());
     * }
     * return convertView;
     * </pre>
     * <p/>
     * </p>
     */
    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

}
