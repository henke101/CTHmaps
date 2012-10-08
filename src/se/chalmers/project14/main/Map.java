package se.chalmers.project14.main;

import java.util.List;

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
		Intent i = getIntent();
		if(i.getStringExtra(ChooseLocationActivity.CTHBUILDING.toString()) != null){
			String cthBuilding = i
					.getStringExtra(ChooseLocationActivity.CTHBUILDING);
			String doorCoordinates = i
					.getStringExtra(ChooseLocationActivity.CTHDOOR_COORDINATES);
			String cthBuildingCoordinates = i
					.getStringExtra(ChooseLocationActivity.CTHBUILDING_COORDINATES);
			//Set geoPoint to the coordinate of the building
			geoPoint = new GeoPoint(57688018, 11977886);
			
		}else{
			geoPoint = new GeoPoint(57688018, 11977886);
		}
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
		controller.animateTo(geoPoint);
		controller.setZoom(16);

		// Overlays
		List<Overlay> mapOverlays = mapView.getOverlays();
		touchOverlay = new TouchOverlay(this, mapView);
		mapOverlays.add(touchOverlay);

		// Adding clickable map overlays for the EDIT-house entrances
		mapOverlays = mapView.getOverlays();
		Drawable editIcon = this.getResources().getDrawable(R.drawable.edit);
		BuildingOverlay editOverlay = new BuildingOverlay(editIcon, this);
		GeoPoint edit1GeoPoint = new GeoPoint(57687808, 11979096);
		OverlayItem edit1OverlayItem = new OverlayItem(edit1GeoPoint,
				"Entrance EDIT huset", "Classrooms close to this entrance:");
		editOverlay.addOverlay(edit1OverlayItem);
		GeoPoint edit2GeoPoint = new GeoPoint(57687458, 11978455);
		OverlayItem edit2OverlayItem = new OverlayItem(edit2GeoPoint,
				"Entrance EDIT huset", "Classrooms close to this entrance:");
		editOverlay.addOverlay(edit2OverlayItem);
		GeoPoint edit3GeoPoint = new GeoPoint(57688242, 11978600);
		OverlayItem edit3OverlayItem = new OverlayItem(edit3GeoPoint,
				"Entrance EDIT huset", "Classrooms close to this entrance:");
		editOverlay.addOverlay(edit3OverlayItem);
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

	public int[] convertStringCordinateToIntCoordinate(String coordinates) {

		String[] coord = coordinates.split(",");
		int cdr[] = new int[coordinates.length()];
		for (int i = 0; i < coord.length; i++) {
			cdr[i] = Integer.parseInt(coord[i]);

		}
		return cdr;
	}

}
