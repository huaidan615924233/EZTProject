/**
 * @file XListView.java
 * @package me.maxwin.view
 * @create Mar 18, 2012 6:28:41 PM
 * @author Maxwin
 * @description An ListView support (a) Pull down to refresh, (b) Pull up to load more.
 * 		Implement IXListViewListener, and see stopRefresh() / stopLoadMore().
 */
package com.ezt.eztproject.view.xlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.ezt.eztproject.R;
import com.ezt.eztproject.utils.MeasureUtil;

public class XListView extends ListView implements OnScrollListener {

	private int mScreenWidth; // 屏幕宽度
	private int mDownX; // 按下点的x值
	private int mDownY; // 按下点的y值
	private int mDeleteBtnWidth;// 删除按钮的宽度

	private boolean isDeleteShown; // 删除按钮是否正在显示

	private boolean mEnableSideslip = false;
	private boolean mSideslipMove = false;

	private ViewGroup mPointChild; // 当前处理的item
	private LinearLayout.LayoutParams mLayoutParams; // 当前处理的item的LayoutParams

	private float mLastY = -1; // save event y
	private Scroller mScroller; // used for scroll back
	private OnScrollListener mScrollListener; // user's scroll listener

	// the interface to trigger refresh and load more.
	private IXListViewListener mListViewListener;

	// -- header view
	private XListViewHeader mHeaderView;
	// header view content, use it to calculate the Header's height. And hide it
	// when disable pull refresh.
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderTimeView;
	private int mHeaderViewHeight; // header view's height
	private boolean mEnablePullRefresh = true;
	private boolean mPullRefreshing = false; // is refreashing.

	// -- footer view
	private XListViewFooter mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;

	// total list items, used to detect is at the bottom of listview.
	private int mTotalItemCount;

	// for mScroller, scroll back from header or footer.
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;

	private final static int SCROLL_DURATION = 400; // scroll back duration
	private static int PULL_LOAD_MORE_DELTA = 100; // when pull up >= 50px
														// at bottom, trigger
														// load more.
	private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
													// feature.

	public void setStateHint(String strReady,String strNormal){
		if(mFooterView!=null){
			mFooterView.setStateHint(strReady, strNormal);
		}
//		this.strStateReady=strReady;
//		this.strStateNormal=strNormal;
	}
	
	/**
	 * @param context
	 */
	public XListView(Context context) {
		super(context);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWithContext(context);
	}

	private void initWithContext(Context context) {
		mScroller = new Scroller(context, new DecelerateInterpolator(2.0f));
		// XListView need the scroll event, and it will dispatch the event to
		// user's listener (as a proxy).
		super.setOnScrollListener(this);

		// 获取屏幕宽度
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		PULL_LOAD_MORE_DELTA= MeasureUtil.dip2px(context, 100);
		// init header view
		mHeaderView = new XListViewHeader(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView
				.findViewById(R.id.xlistview_header_content);
		mHeaderTimeView = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_time);
		addHeaderView(mHeaderView);

		// init footer view
		mFooterView = new XListViewFooter(context);

		// init header height
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						mHeaderViewHeight = mHeaderViewContent.getHeight();
						getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
	}

	public void setEnableSideslip(boolean enableSideslip) {
		this.mEnableSideslip = enableSideslip;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// make sure XListViewFooter is the last footer view, and only add once.
		if (mIsFooterReady == false) {
			mIsFooterReady = true;
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	/**
	 * enable or disable pull down refresh feature.
	 * 
	 * @param enable
	 */
	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh) { // disable, hide the content
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else {
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * enable or disable pull up load more feature.
	 * 
	 * @param enable
	 */
	public void setPullLoadEnable(boolean enable) {
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
		} else {
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
			// both "pull up" and "click" will invoke load more.
			mFooterView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startLoadMore();
				}
			});
		}
	}

	/**
	 * stop refresh, reset header view.
	 */
	public void stopRefresh() {
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	/**
	 * stop load more, reset footer view.
	 */
	public void stopLoadMore() {
		if (mPullLoading == true) {
			mPullLoading = false;
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
		}
	}

	/**
	 * set last refresh time
	 * 
	 * @param time
	 */
	public void setRefreshTime(String time) {
		mHeaderTimeView.setText(time);
	}

	private void invokeOnScrolling() {
		if (mScrollListener instanceof OnXScrollListener) {
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			l.onXScrolling(this);
		}
	}

	private void updateHeaderHeight(float delta) {
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
				mHeaderView.setState(XListViewHeader.STATE_READY);
			} else {
				mHeaderView.setState(XListViewHeader.STATE_NORMAL);
			}
		}
		setSelection(0); // scroll to top each time
	}

	/**
	 * reset header view's height.
	 */
	private void resetHeaderHeight() {
		int height = mHeaderView.getVisiableHeight();
		if (height == 0) // not visible.
			return;
		// refreshing and header isn't shown fully. do nothing.
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int finalHeight = 0; // default: scroll back to dismiss header.
		// is refreshing, just scroll back to show all the header.
		if (mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);
		// trigger computeScroll
		invalidate();
	}

	private void updateFooterHeight(float delta) {
		if (mEnablePullLoad && !mPullLoading) {
			int height = mFooterView.getBottomMargin() + (int) delta;
			if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
													// more.
				mFooterView.setState(XListViewFooter.STATE_READY);
			} else {
				mFooterView.setState(XListViewFooter.STATE_NORMAL);
			}
			mFooterView.setBottomMargin(height);
		}

		// setSelection(mTotalItemCount - 1); // scroll to bottom
	}

	private void resetFooterHeight() {
		int bottomMargin = mFooterView.getBottomMargin();
		if (bottomMargin > 0) {
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
					SCROLL_DURATION);
			invalidate();
		}
	}

	private void startLoadMore() {
		mPullLoading = true;
		mFooterView.setState(XListViewFooter.STATE_LOADING);
		if (mListViewListener != null) {
			mListViewListener.onLoadMore();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mLastY == -1) {
			mLastY = ev.getRawY();
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mSideslipMove = false;
			if (mEnableSideslip) {
				performActionDown(ev);
			}
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			return performActionMove(ev);
		default:
			mLastY = -1; // reset
			if (getFirstVisiblePosition() == 0) {
				// invoke refresh
				if (mEnablePullRefresh
						&& mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
					mPullRefreshing = true;
					mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
					if (mListViewListener != null) {
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			}
			if (getLastVisiblePosition() == mTotalItemCount - 1) {
				// invoke load more.
				if (mEnablePullLoad
						&& mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
					startLoadMore();
				}
				resetFooterHeight();
			}
			if (mEnableSideslip) {
				performActionUp();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			if (mScrollBack == SCROLLBACK_HEADER) {
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else {
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mScrollListener = l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mScrollListener != null) {
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// send to user's listener
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null) {
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}

	/**
	 * you can listen ListView.OnScrollListener or this one. it will invoke
	 * onXScrolling when header/footer scroll back.
	 */
	public interface OnXScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}

	// 处理action_down事件
	private void performActionDown(MotionEvent ev) {
		if (isDeleteShown) {
			turnToNormal();
			mPointChild=null;
		} else {
			mDownX = (int) ev.getX();
			mDownY = (int) ev.getY();
			// 获取当前点的item
			mPointChild = (ViewGroup) getChildAt(pointToPosition(mDownX, mDownY)
					- getFirstVisiblePosition());
			if (mPointChild != null && mPointChild.getChildAt(1) != null) {
				// 获取删除按钮的宽度
				mDeleteBtnWidth = mPointChild.getChildAt(1).getLayoutParams().width;
				mLayoutParams = (LinearLayout.LayoutParams) mPointChild
						.getChildAt(0).getLayoutParams();
				// 为什么要重新设置layout_width 等于屏幕宽度
				// 因为match_parent时，不管你怎么滑，都不会显示删除按钮
				// why？ 因为match_parent时，ViewGroup就不去布局剩下的view
				mLayoutParams.width = mScreenWidth;
				mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
			}
		}
	}

	// 处理action_move事件
	private boolean performActionMove(MotionEvent ev) {
		int nowX = (int) ev.getX();
		int nowY = (int) ev.getY();
		if (Math.abs(nowX - mDownX) > Math.abs(nowY - mDownY)
				&& mEnableSideslip) {
			// 如果向左滑动
			if (nowX < mDownX) {
				// 计算要偏移的距离
				if (mPointChild != null) {
					int scroll = (nowX - mDownX) / 2;
					// 如果大于了删除按钮的宽度， 则最大为删除按钮的宽度
					if (-scroll >= mDeleteBtnWidth) {
						scroll = -mDeleteBtnWidth;
					}
					// 重新设置leftMargin
					mLayoutParams.leftMargin = scroll;

					mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
				}
			}
			mSideslipMove = true;
			return mSideslipMove;
		} else if (!mSideslipMove) {
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();
			if (getFirstVisiblePosition() == 0
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
				// the first item is showing, header has shown or pull down.
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				invokeOnScrolling();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1
					&& (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
				// last item, already pulled up or want to pull up.
				updateFooterHeight(-deltaY / OFFSET_RADIO);
			}
		}
		return super.onTouchEvent(ev);
	}

	// 处理action_up事件
	private void performActionUp() {
		// 偏移量大于button的一半，则显示button
		// 否则恢复默认
		if (mPointChild != null) {
			if (-mLayoutParams.leftMargin >= mDeleteBtnWidth / 2) {
				mLayoutParams.leftMargin = -mDeleteBtnWidth;
				isDeleteShown = true;
			} else {
				turnToNormal();
			}
			mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
		}
	}

	/**
	 * 变为正常状态
	 */
	public void turnToNormal() {
		if(mLayoutParams!=null){
			mLayoutParams.leftMargin = 0;
			mLayoutParams.width = LayoutParams.MATCH_PARENT;
		}
		if (mPointChild != null) {
			mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
		}
		isDeleteShown = false;
	}

	public boolean performItemClick(View view, int position, long id) {
		if (getOnItemClickListener() != null) {
			playSoundEffect(SoundEffectConstants.CLICK);
			if (view != null) {
				view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
			}
			if (!mEnablePullRefresh) {
				if (position >= 0 && position < getAdapter().getCount()) {
					getOnItemClickListener().onItemClick(this, view, position,
							id);
				}
			} else {
				if ((position - 1) >= 0
						&& (position - 1) < getAdapter().getCount()) {
					getOnItemClickListener().onItemClick(this, view,
							position - 1, id);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 当前是否可点击
	 * 
	 * @return 是否可点击
	 */
	public boolean canClick() {
		return !isDeleteShown && !mSideslipMove;
	}

	/**
	 * implements this interface to get refresh/load more event.
	 */
	public interface IXListViewListener {
		public void onRefresh();

		public void onLoadMore();
	}
}
