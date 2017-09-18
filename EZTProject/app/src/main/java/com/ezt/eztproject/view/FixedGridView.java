package com.ezt.eztproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class FixedGridView extends GridView {
    private boolean hasScrollBar = true;

    public boolean isHasScrollBar() {
        return hasScrollBar;
    }

    public void setHasScrollBar(boolean hasScrollBar) {
        this.hasScrollBar = hasScrollBar;
    }

    public FixedGridView(Context context) {
        super(context);
    }

    public FixedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = heightMeasureSpec;
        if(hasScrollBar){
            expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);// 注意这里,这里的意思是直接测量出GridView的高度
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
