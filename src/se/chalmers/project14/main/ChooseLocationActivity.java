package se.chalmers.project14.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.project14.database.DatabaseAdapter;
import se.chalmers.project14.database.DatabaseHandler;
import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.model.Door;
import se.chalmers.project14.model.House;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseLocationActivity extends ListActivity implements
		OnItemClickListener {

	public final static String CTHBUILDING_COORDINATES = "se.chalmers.project14.main.CTHBUILDING_COORDINATES";
	public final static String CTHBUILDING = "se.chalmers.project14.main.CTHBUILDING";
	public final static String CTHDOOR_COORDINATES = "se.chalmers.project14.main.CTHDOOR_CTHBUILDING";
	public final static String CTHBUILDING_FLOOR = "se.chalmers.project14.main.CTHBUILDING_FLOOR";

	private DatabaseAdapter dba;
	private ListView listview;
	private List<House> houseList;
	private DatabaseHandler db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_location);
		listview = getListView();
		db = new DatabaseHandler(this);
		houseList = new ArrayList<House>();
		houseList = db.getAllHouse();
		sortCoordinateList(houseList);
		dba = new DatabaseAdapter(this, houseList);
		setListAdapter(dba);
		listview.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_choose_location, menu);
		return true;
	}

	/**
	 * Sorting an list in containing house object by alfabethic order
	 * 
	 * @param sortHouseList
	 *            a List<House>
	 */
	public void sortCoordinateList(List<House> sortHouseList) {
		Collections.sort(houseList, new Comparator<House>() {

			public int compare(House h1, House h2) {
				return h1.getHouse().compareTo(h2.getHouse());

			}
		});
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
	 * Invoked when browse button is pressed, send the coordinates to Chalmers
	 * school to next view
	 * 
	 * @param view
	 */
	public void searchLocationButton(View view) {
		Intent intent = new Intent(this, Map.class);
		startActivity(intent);
	}

	/**
	 * Invoked when List view is pressed, send the coordinate to selected
	 * building and coordinates to the building doors and which building
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> arg0, View view, int listPosition,
			long i) {
		// HouseListItem h = (HouseListItem) view; Waat tha fuck!!! Bohn
		House house = houseList.get(listPosition);
		String cthBuilding = house.getHouse();
		int houseId = house.getId();
		Door door = db.getDoorCoordinates(houseId);
		Coordinates coordinate = db.getCoordinates(houseId);
		Intent intent = new Intent(this, Map.class);
		intent.putExtra(CTHBUILDING, cthBuilding);
		intent.putExtra(CTHBUILDING_COORDINATES, coordinate.getCoordinates());
		intent.putExtra(CTHBUILDING_FLOOR, door.getFloor());
		intent.putExtra(CTHDOOR_COORDINATES, door.getDoorCoordinates());
		// startActivity(intent);
	}
}
