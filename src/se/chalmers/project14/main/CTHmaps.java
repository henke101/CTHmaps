package se.chalmers.project14.main;



import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CTHmaps extends MapActivity implements OnTouchListener {
	private LocationManager locManager;
	private LocationListener locListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		// Enabling zooming
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setOnTouchListener(this);

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
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.edit); 
		BuildingOverlay buildingOverlay = new BuildingOverlay(drawable, this); 
		GeoPoint point = new GeoPoint(57687806,11979323);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
		buildingOverlay.addOverlay(overlayitem);
		mapOverlays.add(buildingOverlay);
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


	public boolean onTouch(View v, MotionEvent event) {
		Intent openFloorViewer = new Intent("se.chalmers.project14.main.ChooseLocationActivity");
		startActivity(openFloorViewer);
		return false;
	}
}
