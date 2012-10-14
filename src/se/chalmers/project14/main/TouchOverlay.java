package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 ICRL
 * See the file license.txt for copying permission.
 */

import java.util.ArrayList;
import java.util.List;

import utils.CoordinateParser;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.MyLocationOverlay;

public class TouchOverlay extends Overlay {
	//private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context context;
	private long touchStart, touchStop;
	private float touchStartX = 1, touchStartY = 2, touchStopX = 3,
			touchStopY = 4;
	private MapView mapView;
	private GeoPoint myGeoPoint, myLastGeoPoint, destGeoPoint;
	private DestinationMarkerOverlay sourceOverlay, destOverlay;
	private CoordinateParser coordinateParser = CoordinateParser.getInstance();
	private MyLocationOverlay myLocationOverlay;
	
	private LocationManager locManager;
	private LocationListener locListener;

	public TouchOverlay(Context context, MapView mapView, Intent intent) {
		super();
		this.context = context;
		this.mapView=mapView;

		//Checks if a specific classroom has been chosen
		if (intent.getStringExtra(ChooseLocationActivity.CTHBUILDING.toString()) != null) {

			// Retrieves info about the chosen classroom from the database
			String cthLectureRoom = intent
					.getStringExtra(ChooseLocationActivity.CTHLECTURE_ROOM);
			String cthBuilding = intent
					.getStringExtra(ChooseLocationActivity.CTHBUILDING);
			int [] doorCoordinates = coordinateParser.parseCoordinates(intent
					.getStringExtra(ChooseLocationActivity.CTHDOOR_COORDINATES));
			/*for the moment, never used varible
			int [] cthBuildingCoordinates = coordinateParser.parseCoordinates(intent
			.getStringExtra(ChooseLocationActivity.CTHBUILDING_COORDINATES));*/

			int cthBuildingFloor = Integer.parseInt(intent
					.getStringExtra(ChooseLocationActivity.CTHBUILDING_FLOOR));


			// Creates clickable map overlays for the chosen classrooms closest entrances
			Drawable buildingIcon = setBuildingIcon(cthBuilding);
			BuildingOverlay buildingOverlay = new BuildingOverlay(buildingIcon, context);
			for (int i=0; i<doorCoordinates.length;i +=2 ){
				GeoPoint entranceGeoPoint = new GeoPoint(doorCoordinates[i], doorCoordinates[i+1]);
				OverlayItem entranceOverlayItem = new OverlayItem(entranceGeoPoint,
						"Entrance" + " " + cthBuilding, "Classrooms close to this entrance:");
				buildingOverlay.addOverlay(entranceOverlayItem);
				mapView.getOverlays().add(buildingOverlay);
			}
		}
		
		/* Using the LocationManager class to obtain GPS-location */
		locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		locListener = new MyLocationListener(context);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				locListener);

		/*
		 * Using the MyLocationOverlay-class to add users current position to
		 * map-view
		 */
		myLocationOverlay = new MyLocationOverlay(context,
				mapView);
		mapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass(); // Adding a compass to the map
		
		//Sets myLastGeoPoint to a value (used if GPS-signal is not established)
		myLastGeoPoint = new GeoPoint(57688018, 11977886);
		
		try{
			myGeoPoint = myLocationOverlay.getMyLocation();	
			Toast.makeText(context, "try: " + myGeoPoint.getLatitudeE6() + "." , Toast.LENGTH_SHORT).show();
			Log.d("tag", "try: " + myGeoPoint.getLatitudeE6() + ".");
		} 
		//sets my location to a value if GPS-signal is not achieved
		catch (NullPointerException e){
			myGeoPoint = myLastGeoPoint;
			Toast.makeText(context, "catch", Toast.LENGTH_SHORT).show();
			Log.d("tag", "catch: " + myGeoPoint.getLatitudeE6() + ".");
		}
//
//		// Creates a position-marker avatar
//		Drawable avatar = mapView.getResources().getDrawable(R.drawable.anton);
//		sourceOverlay = new DestinationMarkerOverlay(avatar, mapView);
		
		// Creates a destination flag overlay
		Drawable destFlag = mapView.getResources().getDrawable(R.drawable.destination_flag);
		destOverlay = new DestinationMarkerOverlay(destFlag, mapView);

		//Adds the created overlays		
//		mapView.getOverlays().add(sourceOverlay);
		mapView.getOverlays().add(destOverlay);
//		
//		//
//		OverlayItem sourceItem = new OverlayItem(myGeoPoint, "Locationmarker", "This is the recent location");
//		sourceOverlay.setDestination(sourceItem);
//		mapView.invalidate();

	}


	@Override
	public boolean onTouchEvent(MotionEvent event, MapView m) {
		// when user touches the screen
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			touchStart = event.getEventTime();
			touchStartX = event.getX();
			touchStartY = event.getY();
		}// when screen is released
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			touchStop = event.getEventTime();
			touchStopX = event.getX();
			touchStopY = event.getY();

			/*
			 * Checking holdtime to be above 1000 ms and at he same position
			 */
			if (touchStop - touchStart > 1000 && touchStartX <= touchStopX+20 && touchStartX >= touchStopX-20 
					&& touchStartY <= touchStopY+20 && touchStartY >= touchStopY-20) {
				destGeoPoint = mapView.getProjection().fromPixels((int)touchStopX, (int)touchStopY);
				AlertDialog.Builder options = new AlertDialog.Builder(context);
				options.setTitle("Options");
				options.setMessage("Coordinates:\nLatitude: " + destGeoPoint.getLatitudeE6()/1E6 + "\nLongitude: " 
						+ destGeoPoint.getLongitudeE6()/1E6 + "\n\nWhat do you want to do?");
				options.setNegativeButton("Set destination", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {						
						//Adding a destination marker
						OverlayItem destinationItem = new OverlayItem(destGeoPoint, "Destinationmarker", "This is the chosen destination");
						destOverlay.setDestination(destinationItem);
						mapView.invalidate();
					}
				});
				options.setPositiveButton("Back to Map", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, "Test2", Toast.LENGTH_SHORT).show();
					}
				});
				options.show();
				return true;
			}
		}
		return false;
	}
	public DestinationMarkerOverlay getDestOverlay(){
		return destOverlay;
	}
	private Drawable setBuildingIcon(String s){
		if(s.equals("EDIT-huset")){
			return mapView.getResources().getDrawable(R.drawable.edit);
		}
		else if (s.equals("Maskinhuset")){
			return mapView.getResources().getDrawable(R.drawable.m);
		}
		else if (s.equals("HA")){
			return mapView.getResources().getDrawable(R.drawable.ha);
		}
		else if (s.equals("HB")){
			
			return mapView.getResources().getDrawable(R.drawable.hb);
		}
		else if (s.equals("HC")){
			return mapView.getResources().getDrawable(R.drawable.hc);
		}
		
		return null ;
	}
}
