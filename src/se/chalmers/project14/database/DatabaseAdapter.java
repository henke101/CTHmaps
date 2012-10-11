package se.chalmers.project14.database;

import java.util.List;
import se.chalmers.project14.main.R;
import se.chalmers.project14.model.House;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DatabaseAdapter extends BaseAdapter {

	private Activity activity;
	private List<House> houseList;
	private LayoutInflater inflater;

	public DatabaseAdapter(Activity activity, List<House> houseList) {// change
																		// this
																		// to
																		// House
																		// instead
																		// of
																		// Coordinates!!!!!!!
		this.activity = activity;
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
		// return new HouseListItem(activity,h);
	}

}
