package com.ezt.eztproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseAdapter;
import com.ezt.eztproject.R;

/**
 * Created by SEELE on 2017/8/12.
 */

public class PayTypeAdapter extends EZTBaseAdapter<Integer> {

    public PayTypeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_pay_type_item, null);
            holder.payTypeName = (TextView) convertView.findViewById(R.id.pay_type_nameTV);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (getItem(position)) {
            case 1:
                holder.payTypeName.setText("加气站充值");
                break;
            case 2:
                holder.payTypeName.setText("支付宝");
                break;
            case 3:
                holder.payTypeName.setText("微信支付");
                break;
            case 4:
                holder.payTypeName.setText("物流公司充值");
                break;
            case 5:
                holder.payTypeName.setText("转账打款充值");
                break;
            default:
                holder.payTypeName.setText("支付类型");
                break;
        }
        return convertView;
    }

    class ViewHolder {
        TextView payTypeName;
    }

}
