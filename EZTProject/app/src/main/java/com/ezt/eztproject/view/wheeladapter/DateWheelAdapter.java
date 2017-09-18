package com.ezt.eztproject.view.wheeladapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezt.eztproject.utils.TimeUtils;


public class DateWheelAdapter extends AbstractWheelTextAdapter{

	/** The default min value */
    public static final int DEFAULT_MAX_VALUE = 9;

    /** The default max value */
    private static final int DEFAULT_MIN_VALUE = 0;
    
    // Values
    private int minDate;
    private int maxDate;
    
    // format
    private String dataFormat;
    
    /**
     * Constructor
     * @param context the current context
     */
    public DateWheelAdapter(Context context) {
        this(context, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }

    /**
     * Constructor
     * @param context the current context
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public DateWheelAdapter(Context context, long minValue, long maxValue) {
        this(context, minValue, maxValue, null);
    }

    /**
     * Constructor
     * @param context the current context
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     * @param format the format string
     */
    public DateWheelAdapter(Context context, long minValue, long maxValue, String format) {
        super(context);
        
        this.minDate = (int)((long)minValue/(long)86400000);
        this.maxDate = (int)((long)maxValue/(long)86400000);
        this.dataFormat = format;
    }

    @Override
    public CharSequence getItemText(int index) {
        if (index >= 0 && index < getItemsCount()) {
            int value = minDate + index;
            long tempValue=(long)value*86400000l;
            return dataFormat == null ? TimeUtils.convertDataForTimeMillis(tempValue) : TimeUtils.convertDataForTimeMillis(dataFormat,tempValue);
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return maxDate - minDate + 1;
    }
    
    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        if (index >= 0 && index < getItemsCount()) {
            if (convertView == null) {
                convertView = getView(itemResourceId, parent);
            }
            TextView textView = getTextView(convertView, itemTextResourceId);
            if (textView != null) {
                CharSequence text = getItemText(index);
                if (text == null) {
                    text = "";
                }
                textView.setText(text);
    
                if (itemResourceId == TEXT_VIEW_ITEM_RESOURCE) {
                    configureTextView(textView);
                }
            }
            return convertView;
        }
    	return null;
    }   

}
