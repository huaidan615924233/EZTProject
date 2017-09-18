package com.ezt.eztproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseAdapter;
import com.ezt.eztproject.R;
import com.ezt.eztproject.bean.IntegerProduct;
import com.ezt.eztproject.bean.NewsInfo;

/**
 * Created by SEELE on 2017/8/12.
 */

public class IntegerListAdapter extends EZTBaseAdapter<IntegerProduct> {

    public IntegerListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_integer_product_item, null);
            holder.productName = (TextView) convertView.findViewById(R.id.product_name);
            holder.productInteger = (TextView) convertView.findViewById(R.id.product_integer);
            holder.productPrice = (TextView) convertView.findViewById(R.id.product_price);
            holder.productIcon = (ImageView) convertView.findViewById(R.id.product_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IntegerProduct info = getItem(position);
        holder.productName.setText(info.getProductName());
        holder.productPrice.setText("市场参考价：" + info.getProductPrice() + ".00元");
        holder.productInteger.setText(info.getNeedInteger());
        return convertView;
    }

    class ViewHolder {
        TextView productName, productInteger, productPrice;
        ImageView productIcon;
    }

}
