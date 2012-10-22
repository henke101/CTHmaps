package se.chalmers.project14.model;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

import java.util.List;
import se.chalmers.project14.activities.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StorageAdapter extends BaseAdapter {

	private List<House> houseList;
	private LayoutInflater inflater;

	public StorageAdapter(Activity activity, List<House> houseList) {
		this.houseList = houseList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return houseList.size();
	}

	public Object getItem(int position) {
		return houseList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			view = inflater.inflate(R.layout.row, null);
			TextView t = (TextView) view.findViewById(R.id.text123);
			String house = houseList.get(position).getLectureRoom();
			t.setText(house);
		} else {
			view = convertView;
		}
		return view;
	}

}
