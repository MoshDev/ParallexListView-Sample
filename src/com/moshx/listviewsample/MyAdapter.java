package com.moshx.listviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private String[] countries;
	private Context context;

	public MyAdapter(Context context) {
		this.context = context;
		countries = context.getResources().getStringArray(
				R.array.countries_array);
	}

	@Override
	public int getCount() {
		return countries.length;
	}

	@Override
	public Object getItem(int position) {
		return countries[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.view_list_item_country, parent, false);
		}

		TextView textView = (TextView) convertView;
		textView.setText(getItem(position).toString());
		return convertView;
	}

}
