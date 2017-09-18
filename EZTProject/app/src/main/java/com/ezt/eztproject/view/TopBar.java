package com.ezt.eztproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezt.eztproject.R;

/**
 * @author Zhaop
 * @describe TitleBar
 */
public class TopBar extends LinearLayout implements OnClickListener {

    private ImageView backIV;

    private TextView commentTV;

    private ImageView moreIV;

    private TextView titleTV;

    private ImageView titleIV;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_topbar, this, true);

    }

    public void setCommentVisibility(int visibility) {
        commentTV.setVisibility(visibility);
    }

    public void setCommentBackGroundColor(int color) {
        commentTV.setBackgroundColor(color);
    }

    public void setCommentOnClickListener(CommentClickListener listener) {
        commentClickListener = listener;
    }

    public void setCommentText(String strCount) {
        commentTV.setText(strCount);
    }

    public void setCommentTextColor(int color) {
        commentTV.setTextColor(color);
    }

    public void setTitle(String strTitle) {
        titleTV.setText(strTitle);
    }

    public void setTitleVisibility(int visibility) {
        titleTV.setVisibility(visibility);
    }

    public void setTitleIVVisibility(int visibility) {
        titleIV.setVisibility(visibility);
    }

    public void setBackOnClickListener(BackClickListener listener) {
        backClickListener = listener;
    }

    public void setMoreOnClickListener(MoreClickListener listener) {
        moreClickListener = listener;
    }

    public void setMoreVisibility(int visibility) {
        moreIV.setVisibility(visibility);
    }

    public void setBackVisibility(int visibility) {
        backIV.setVisibility(visibility);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        backIV = (ImageView) findViewById(R.id.img_bar_back);
        commentTV = (TextView) findViewById(R.id.txt_bar_comment);
        moreIV = (ImageView) findViewById(R.id.img_bar_more);
        titleTV = (TextView) findViewById(R.id.txt_bar_title);
        titleIV = (ImageView) findViewById(R.id.img_bar_title);
        backIV.setOnClickListener(this);
        commentTV.setOnClickListener(this);
        moreIV.setOnClickListener(this);
    }

    /**
     * 对按钮进行回调方式的处理
     */
    public BackClickListener backClickListener;

    public MoreClickListener moreClickListener;

    public CommentClickListener commentClickListener;

    public interface BackClickListener {

        void onClick(View view);
    }

    public interface MoreClickListener {

        void onClick(View view);
    }

    public interface CommentClickListener {

        void onClick(View view);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txt_bar_comment:
                if (commentClickListener != null)
                    commentClickListener.onClick(v);
                break;

            case R.id.img_bar_back:
                if (backClickListener != null)
                    backClickListener.onClick(v);
                break;
            case R.id.img_bar_more:
                if (moreClickListener != null)
                    moreClickListener.onClick(v);
                break;
        }
    }

}
