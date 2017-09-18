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
import com.ezt.eztproject.bean.CarInfo;

import org.w3c.dom.Text;

/**
 * Created by SEELE on 2017/8/12.
 */

public class GasStationServiceListAdapter extends EZTBaseAdapter<Integer> {

    public GasStationServiceListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_gasstation_service_item, null);
            holder.serviceIcon = (ImageView) convertView.findViewById(R.id.service_icon);
            holder.serviceName = (TextView) convertView.findViewById(R.id.service_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (getItem(position)) {
            case 1:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_1);
                holder.serviceName.setText("洗手间");
                break;
            case 2:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_2);
                holder.serviceName.setText("热水");
                break;
            case 3:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_3);
                holder.serviceName.setText("商店");
                break;
            case 4:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_4);
                holder.serviceName.setText("餐饮");
                break;
            case 5:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_5);
                holder.serviceName.setText("住宿");
                break;
            case 6:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_6);
                holder.serviceName.setText("免费WiFi");
                break;
            case 7:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_7);
                holder.serviceName.setText("停车场");
                break;
            case 8:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_8);
                holder.serviceName.setText("修车");
                break;
            default:
                holder.serviceIcon.setImageResource(R.mipmap.ic_service_1);
                holder.serviceName.setText("洗手间");
                break;
        }
        return convertView;
    }

    class ViewHolder {
        ImageView serviceIcon;
        TextView serviceName;
    }

}
