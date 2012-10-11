package se.chalmers.project14.main;

import java.util.List;

import utils.CoordinateParser;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Map extends MapActivity {
	private LocationManager locManager;
	private LocationListener locListener;
	private MapController controller;
	private Button buttonToggle, buttonNewDest, buttonClear;
	private MapView mapView;
	private GeoPoint geoPoint;
	private TouchOverlay touchOverlay;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		//Create buttons and listeners
		buttonToggle = (Button) findViewById(R.id.buttonToggle);
		buttonNewDest = (Button) findViewById(R.id.buttonNewDest);
		buttonClear = (Button) findViewById(R.id.buttonRemoveDest);
		buttonToggle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mapView.setSatellite(!mapView.isSatellite());
			}
		});
		buttonNewDest.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onBackPressed();

			}
		});
		buttonClear.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				touchOverlay.getDestOverlay().removeDestinationMarker();
				mapView.invalidate();
			}
		});
		
		
		
		// Enabling zooming
		mapView = (MapView) findViewById(R.id.mapview);
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

		/*
		 * Using the controller to pan in to the EDIT-house's coordinates and to
		 * zoom in at a lucid level
		 */
		controller = mapView.getController();
		controller.animateTo(new GeoPoint(57688018, 11977886));
		controller.setZoom(16);

		// Overlays
		List<Overlay> mapOverlays = mapView.getOverlays();
		touchOverlay = new TouchOverlay(this, mapView, getIntent());
		mapOverlays.add(touchOverlay);
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
