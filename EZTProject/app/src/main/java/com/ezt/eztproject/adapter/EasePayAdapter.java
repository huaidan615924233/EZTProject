package com.ezt.eztproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseAdapter;
import com.ezt.eztproject.R;
import com.ezt.eztproject.bean.EasePayInfo;
import com.ezt.eztproject.bean.NewsInfo;

/**
 * Created by SEELE on 2017/8/12.
 */

public class EasePayAdapter extends EZTBaseAdapter<EasePayInfo> {

    public EasePayAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_easepay_item, null);
            holder.priceTV = (TextView) convertView.findViewById(R.id.a_t_coupons_price);
            holder.usedDataTV = (TextView) convertView.findViewById(R.id.a_t_coupons_validate);
            holder.titleTV = (TextView) convertView.findViewById(R.id.a_t_coupons_content);
            holder.descTV = (TextView) convertView.findViewById(R.id.a_t_coupons_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EasePayInfo info = getItem(position);
        holder.priceTV.setText(info.getPrice());
        holder.usedDataTV.setText("有效期至 " + info.getUsedData());
        holder.titleTV.setText(info.getTitle());
        holder.descTV.setText(info.getDesc());
        return convertView;
    }

    class ViewHolder {
        TextView priceTV, usedDataTV, titleTV, descTV;
    }

}
