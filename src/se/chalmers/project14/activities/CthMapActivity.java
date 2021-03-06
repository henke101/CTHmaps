package se.chalmers.project14.activities;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selld�n and Marcus Tyr�n
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

import java.util.List;
import se.chalmers.project14.activities.R;
import se.chalmers.project14.model.overlay.OverlayHolder;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CthMapActivity extends MapActivity {
	private MapController controller;
	private Button buttonToggleSat, buttonCenter, buttonNewDest, buttonClear, buttonToggleGpsPosition;
	private MapView mapView;
	private OverlayHolder overlayHolder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		//Create buttons and listeners
		buttonNewDest = (Button) findViewById(R.id.buttonNewDest);
		buttonClear = (Button) findViewById(R.id.buttonRemoveDest);
		buttonCenter = (Button) findViewById(R.id.buttonCenter);
		buttonToggleSat = (Button) findViewById(R.id.buttonToggleSat);
		buttonToggleGpsPosition = (Button) findViewById(R.id.buttonToggleGpsPosition);
		
		buttonNewDest.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onBackPressed();
			}
		});
		buttonClear.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				overlayHolder.resetDestination();
				mapView.invalidate();
			}
		});
		buttonCenter.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				controller.animateTo(new GeoPoint(57688018, 11977886));
			}
		});
		buttonToggleSat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mapView.setSatellite(!mapView.isSatellite());
			}
		});
		buttonToggleGpsPosition.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				overlayHolder.toggleUseGpsData();
			}
		});
		

		// Enabling zooming
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		/*
		 * Using the controller to pan in to the EDIT-house's coordinates and to
		 * zoom in at a lucid level
		 */
		controller = mapView.getController();
		controller.animateTo(new GeoPoint(57688018, 11977886));
		controller.setZoom(16);
		
		// Overlays
		List<Overlay> mapOverlays = mapView.getOverlays();
		overlayHolder = new OverlayHolder(this, mapView, getIntent());
		mapOverlays.add(overlayHolder);
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

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overlayHolder.stopGpsUpdates();
	}
}
