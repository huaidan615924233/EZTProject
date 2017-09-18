package com.ezt.eztproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ezt.eztproject.EZTBaseAdapter;
import com.ezt.eztproject.R;
import com.ezt.eztproject.bean.CarInfo;
import com.ezt.eztproject.bean.GasStationInfo;

/**
 * Created by SEELE on 2017/8/12.
 */

public class MyCarsAdapter extends EZTBaseAdapter<CarInfo> {

    public MyCarsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_personal_car_item, null);
            holder.carNumber = (TextView)convertView.findViewById(R.id.a_car_number);
            holder.carDesc = (TextView)convertView.findViewById(R.id.a_car_desc);
            holder.carDelete = (ImageButton)convertView.findViewById(R.id.mycar_delete);
            holder.carUpdate = (ImageButton)convertView.findViewById(R.id.mycar_update);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        CarInfo info = getItem(position);
        holder.carNumber.setText(info.getCarNumber());
        holder.carDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(position);
            }
        });
        holder.carUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView carNumber,carDesc ;
        ImageButton carUpdate,carDelete;
    }

}
