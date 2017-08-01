package com.ezt.eztproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by guocongli on 2016/11/2.
 */
public class SquareTextView extends TextView {
    public SquareTextView(Context context) {
        super(context);
    }

    public SquareTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int squareSize=widthMeasureSpec>heightMeasureSpec?widthMeasureSpec:heightMeasureSpec;
        super.onMeasure(squareSize, squareSize);
    }
}
