package se.chalmers.project14.main;

import java.util.List;

import se.chalmers.project14.database.Coordinates;
import se.chalmers.project14.database.DatabaseAdapter;
import se.chalmers.project14.database.DatabaseHandler;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseLocationActivity extends ListActivity implements
		OnItemClickListener {

	public final static String EXTRA_MESSAGE = "se.chalmers.project14.main.EXTRA_MESSAGE";
	public final static String CTHBUILDING_MESSAGE = "se.chalmers.project14.main.CTHBUILDING_MESSAGE";
	private DatabaseAdapter dba;
	private ListView listview;
	private List<Coordinates> coordinateList;
	private DatabaseHandler db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_location);
		listview = getListView();

		db = new DatabaseHandler(this);
		coordinateList = db.getAllCoordinates();
		dba = new DatabaseAdapter(this, R.layout.row, coordinateList);
		setListAdapter(dba);
		listview.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_choose_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * @param view
	 */
	public void searchLocationButton(View view) {
		Intent intent = new Intent(this, CTHmaps.class);
		EditText editText = (EditText) findViewById(R.id.search_locationText);
		String location = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, location);
		startActivity(intent);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> arg0, View view, int listPosition,
			long i) {
		Coordinates coordinate = coordinateList.get(listPosition);
		String cthBuilding = coordinate.getCTHplace();
		coordinate = db.getCoordinates(cthBuilding);
		Log.d("OnitemClick ", " " + coordinate.getCoordinates());

		Intent intent = new Intent(this, CTHmaps.class);
		intent.putExtra(CTHBUILDING_MESSAGE, coordinate.getCoordinates());

		startActivity(intent);

	}
}
