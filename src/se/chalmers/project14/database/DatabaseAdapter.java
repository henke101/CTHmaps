package se.chalmers.project14.database;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.project14.main.R;

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
	private Context context;
	private int layoutId;
	private List<Coordinates> coordinateList;
	private LayoutInflater inflater;

	public DatabaseAdapter(Activity activity, int layoutId,
			List<Coordinates> coordinateList) {
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
		Log.d("still", " working");
		if (convertView == null) {
			Log.d("still 2 ", "working");
			view = inflater.inflate(R.layout.activity_choose_location, parent,
					false);
			Log.d("still 3 ", "working");
			TextView textView = (TextView) view.findViewById(layoutId);
			Log.d("still 4 ", "working");
			String data = coordinateList.get(position).getCTHplace();
			Log.d("still 5 ", "working");
			Log.d("what sata contain", data);
			textView.setText(data);
			Log.d("still 6 ", "working");
		} else {
			Log.d("shouldn«t be here", "no way!");
			view = convertView;
		}
		if (view == null) {
			Log.d("view is null", "a problem");
		}
		return view;
	}

}
