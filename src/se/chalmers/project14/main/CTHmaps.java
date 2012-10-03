package se.chalmers.project14.main;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;

public class CTHmaps extends MapActivity {
	private LocationManager locManager;
	private LocationListener locListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Intent intent = getIntent();
		String coordinates = intent
				.getStringExtra(ChooseLocationActivity.EXTRA_MESSAGE);
		String[] c = coordinates.split(",");
		/*
		 * c innehåller 4 koordinater där varje kordinat innehåller 2
		 * "heltalsvärden om du vill få dem till intar kan du så länge göra en
		 * fullösning :) int coordinate1 = c[0] int cordinate2 = c[1] osv 8
		 * gånger gå inte högre upp än c[7]... därefter lägga in dem i en
		 * geopoint å visa dem på kartan!
		 */

		String allbuildings = null;
		for (int i = 0; i < c.length; i++) {
			allbuildings = c[i];

		}
		Log.d("coordinates in c is ", allbuildings);
		// Enabling zooming
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		/* Using the LocationManager class to obtain GPS-location */
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locListener = new MyLocationListener(this);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				locListener);

		/*
		 * Using the MyLocationOverlay-class to add users current position to
		 * map-view
		 */
		MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this,
				mapView);
		mapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass(); // Adding a compass to the map
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// Stopping the update och GPS-status, when closing
		// map-activity/pressing the back-button in the map-activity
		locManager.removeUpdates(locListener);
	}
}
