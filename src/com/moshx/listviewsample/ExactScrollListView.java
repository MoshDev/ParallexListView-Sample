package com.moshx.listviewsample;

import java.util.Dictionary;
import java.util.Hashtable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class ExactScrollListView extends ListView {

	private OnScrollListener mExternalOnScrollListener;
	private ExactScrollListener mExactScrollListener;

	public ExactScrollListView(Context context) {
		this(context, null, 0);
	}

	public ExactScrollListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ExactScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnScrollListener(internalScrollListener);
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		if (l != internalScrollListener) {
			mExternalOnScrollListener = l;
		}
		super.setOnScrollListener(l);
	}

	private AbsListView.OnScrollListener internalScrollListener = new AbsListView.OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (mExternalOnScrollListener != null) {
				mExternalOnScrollListener.onScrollStateChanged(view,
						scrollState);
			}

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (mExternalOnScrollListener != null) {
				mExternalOnScrollListener.onScroll(view, firstVisibleItem,
						visibleItemCount, totalItemCount);
			}

			if (mExactScrollListener != null) {
				int incrementalOffset = getScroll();
				mExactScrollListener.onScrollChange(incrementalOffset);
			}

		}
	};

	private Dictionary<Integer, Integer> listViewItemHeights = new Hashtable<Integer, Integer>();

	private int getScroll() {
		View c = getChildAt(0); // this is the first visible row
		int scrollY = -c.getTop();
		listViewItemHeights.put(getFirstVisiblePosition(), c.getHeight());
		for (int i = 0; i < getFirstVisiblePosition(); ++i) {
			if (listViewItemHeights.get(i) != null) // (this is a sanity check)
				scrollY += listViewItemHeights.get(i); // add all heights of the
														// views that are gone
		}
		return scrollY;
	}

	public ExactScrollListener getExactScrollListener() {
		return mExactScrollListener;
	}

	public void setExactScrollListener(ExactScrollListener mExactScrollListener) {
		this.mExactScrollListener = mExactScrollListener;
	}

	public interface ExactScrollListener {

		public void onScrollChange(int scroll);
	}
}
