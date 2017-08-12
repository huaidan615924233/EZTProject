package com.ezt.eztproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseAdapter;
import com.ezt.eztproject.R;
import com.ezt.eztproject.bean.GasStationInfo;

/**
 * Created by SEELE on 2017/8/12.
 */

public class GasStationListAdapter extends EZTBaseAdapter<GasStationInfo> {

    public GasStationListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_gasstation_item, null);
            holder.gasStationName = (TextView)convertView.findViewById(R.id.a_gasstion_info_name);
            holder.gasStationTel = (TextView)convertView.findViewById(R.id.e_gas_phone);
            holder.gasStationAddress = (TextView)convertView.findViewById(R.id.e_gas_address);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        GasStationInfo info = getItem(position);
        holder.gasStationName.setText(info.getName());
        holder.gasStationTel.setText(info.getTel());
        holder.gasStationAddress.setText(info.getAddress());
        return convertView;
    }
    class ViewHolder{
        TextView gasStationName,gasStationTel,gasStationAddress ;
        ImageButton gasStationGoto;
    }

}
