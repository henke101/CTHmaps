package se.chalmers.project14.database;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.project14.main.R;
import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.view.HouseListItem;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DatabaseAdapter extends BaseAdapter {
	private Activity activity;
	private int layoutId;
	private List<Coordinates> coordinateList;
	private LayoutInflater inflater;

	public DatabaseAdapter(Activity activity, int layoutId,
			List<Coordinates> coordinateList) {//change this to House instead of Coordinates!!!!!!!
		this.activity = activity;
		this.layoutId = layoutId;
		this.coordinateList = coordinateList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return coordinateList.size();
	}

	public Object getItem(int position) {
		return coordinateList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			view = inflater.inflate(layoutId, null);
			TextView t = (TextView) view.findViewById(R.id.text123);
			String data = coordinateList.get(position).getCoordinates();
			t.setText(data);
		} else {
			view = convertView;
		}
		return view;
		// return new HouseListItem(activity,h);
	}

}
