package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selld�n and Marcus Tyr�n
 * See the file license.txt for copying permission.
 */

import java.util.List;
import utils.CoordinateParser;
import utils.Options;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class Map extends MapActivity {
	private MapController controller;
	private Button buttonToggle, buttonCenter, buttonNewDest, buttonClear, buttonManualPosition;
	private MapView mapView;
	private GeoPoint geoPoint;
	private OverlayHolder overlayHolder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		//Create buttons and listeners
		buttonToggle = (Button) findViewById(R.id.buttonToggle);
		buttonCenter = (Button) findViewById(R.id.buttonCenter);
		buttonNewDest = (Button) findViewById(R.id.buttonNewDest);
		buttonClear = (Button) findViewById(R.id.buttonRemoveDest);
		buttonManualPosition = (Button) findViewById(R.id.buttonManualPosition);
		buttonToggle.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				mapView.setSatellite(!mapView.isSatellite());
			}
		});
		buttonCenter.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				controller.animateTo(new GeoPoint(57688018, 11977886));
			}
		});
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

		buttonManualPosition.setOnClickListener(new OnClickListener() {
			
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

		startActivity(new Intent(this, Options.class));
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
