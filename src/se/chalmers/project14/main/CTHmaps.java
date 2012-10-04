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
import android.graphics.drawable.Drawable;
import android.view.Menu;


public class CTHmaps extends MapActivity {
	private LocationManager locManager;
	private LocationListener locListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		// Enabling zooming
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		//mapView.setOnTouchListener(this);

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
		
		// Adding a clickable map overlay for the EDIT-house
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable editIcon = this.getResources().getDrawable(R.drawable.edit); 
		BuildingOverlay editOverlay = new BuildingOverlay(editIcon, this); 
		GeoPoint editGeoPoint = new GeoPoint(57687806,11979323);
		OverlayItem editOverlayItem = new OverlayItem(editGeoPoint, "", "");
		editOverlay.addOverlay(editOverlayItem);
		mapOverlays.add(editOverlay);
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
