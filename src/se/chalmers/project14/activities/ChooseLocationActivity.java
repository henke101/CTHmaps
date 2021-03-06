package se.chalmers.project14.activities;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selld�n and Marcus Tyr�n
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.model.StorageAdapter;
import se.chalmers.project14.model.Door;
import se.chalmers.project14.model.House;
import se.chalmers.project14.model.storage.Storage;
import se.chalmers.project14.model.storage.Storable;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseLocationActivity extends ListActivity implements
OnItemClickListener {

	public final static String CTHBUILDING_COORDINATES = "se.chalmers.project14.activities.CTHBUILDING_COORDINATES";
	public final static String CTHBUILDING = "se.chalmers.project14.activities.CTHBUILDING";
	public final static String CTHDOOR_COORDINATES = "se.chalmers.project14.activities.CTHDOOR_CTHBUILDING";
	public final static String CTHBUILDING_FLOOR = "se.chalmers.project14.activities.CTHBUILDING_FLOOR";
	public final static String CTHLECTURE_ROOM = "se.chalmers.project14.activities.CTHLECTURE_ROOM";

	private StorageAdapter dba;
	private ListView listview;
	private List<House> houseList;
	private Storable db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_location);
		listview = getListView();
		db = new Storage(this);
		houseList = new ArrayList<House>();
		houseList = db.getAllLectureRoom();
		sortCoordinateList(houseList);
		dba = new StorageAdapter(this, houseList);
		setListAdapter(dba);
		listview.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_options, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		startActivity(new Intent(this, OptionsActivity.class));
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
				return h1.getLectureRoom().compareTo(h2.getLectureRoom());

			}
		});
	}

	/**
	 * Invoked when browse button is pressed, send the coordinates to Chalmers
	 * school to next view
	 * 
	 * @param view
	 */
	public void browseDestinationButton(View view) {
		Intent intent = new Intent(this, CthMapActivity.class);
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
		House house = houseList.get(listPosition);
		String cthFloor = house.getFloor(); // v�ning check
		String cthLectureRoom = house.getLectureRoom();
		int houseId = house.getId();
		Door door = db.getDoorCoordinates(houseId);
		Coordinates coordinate = db.getCoordinates(houseId);
		Intent intent = new Intent(this, CthMapActivity.class);
		intent.putExtra(CTHLECTURE_ROOM, cthLectureRoom);
		intent.putExtra(CTHBUILDING_FLOOR, cthFloor);
		intent.putExtra(CTHBUILDING_COORDINATES, coordinate.getCoordinates());
		intent.putExtra(CTHBUILDING, door.getBuilding());
		intent.putExtra(CTHDOOR_COORDINATES, door.getDoorCoordinates());
		startActivity(intent);

	}
}
