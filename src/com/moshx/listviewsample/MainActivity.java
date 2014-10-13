package com.moshx.listviewsample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final MyAdapter adapter = new MyAdapter(this);

		View headerView = getLayoutInflater().inflate(
				R.layout.view_list_item_header, null);

		ListView listView = (ListView) findViewById(R.id.exactListView);
		listView.setAdapter(adapter);
		listView.addHeaderView(headerView, null, false);
		listView.setOnScrollListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				View headerView = view.findViewById(R.id.header);

				final float mTop = -headerView.getTop();
				float height = headerView.getHeight();
				if (mTop > height) {
					// ignore
					return;
				}
				View imgView = headerView.findViewById(R.id.imageView);
				imgView.setTranslationY(mTop / 2f);

			}
		});
	}
}
