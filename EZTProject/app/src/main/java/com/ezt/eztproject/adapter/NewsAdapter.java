package com.ezt.eztproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseAdapter;
import com.ezt.eztproject.R;
import com.ezt.eztproject.bean.GasStationInfo;
import com.ezt.eztproject.bean.NewsInfo;

/**
 * Created by SEELE on 2017/8/12.
 */

public class NewsAdapter extends EZTBaseAdapter<NewsInfo> {

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_news_item, null);
            holder.newsTitleTV = (TextView) convertView.findViewById(R.id.news_title);
            holder.newsDataTV = (TextView) convertView.findViewById(R.id.news_time);
            holder.newsIcon = (ImageView) convertView.findViewById(R.id.news_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsInfo info = getItem(position);
        holder.newsTitleTV.setText(info.getNewsTitle());
        holder.newsDataTV.setText(info.getNewsData());
        return convertView;
    }

    class ViewHolder {
        TextView newsTitleTV, newsDataTV;
        ImageView newsIcon;
    }

}
