package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import se.chalmers.project14.database.DatabaseHandler;
import se.chalmers.project14.model.Door;
import utils.CoordinateParser;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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
import com.google.android.maps.Projection;

public class TouchOverlay extends Overlay implements LocationListener {
	//private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context context;
	private long touchStart, touchStop;
	private float touchStartX = 1, touchStartY = 2, touchStopX = 3,
			touchStopY = 4;
	private MapView mapView;
	private GeoPoint myGeoPoint, destGeoPoint, focusedGeoPoint;
	private MarkerOverlay sourceOverlay, destOverlay;
	private CoordinateParser coordinateParser = CoordinateParser.getInstance();
	private MyLocationOverlay myLocationOverlay;

	private LocationManager locManager;
	private Projection projection;
	private boolean useGpsData = true;

	public TouchOverlay(Context context, MapView mapView, Intent intent) {
		super();
		this.context = context;
		this.mapView=mapView;
		projection = mapView.getProjection();

		//Checks if a specific classroom has been chosen
		if (intent.getStringExtra(ChooseLocationActivity.CTHBUILDING.toString()) != null) {
			drawChosenEntrances(intent);
		}

		else{
			drawAllEntrances(intent);
		}

		/* Using the LocationManager class to obtain GPS-location */
		locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);

		/*
		 * Using the MyLocationOverlay-class to add users current position to
		 * map-view
		 */
		myLocationOverlay = new MyLocationOverlay(context,
				mapView);
		mapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass(); // Adding a compass to the map

		// Creates a position-marker avatar
		Drawable avatar = mapView.getResources().getDrawable(R.drawable.anton);
		sourceOverlay = new MarkerOverlay(avatar, mapView);

		// Creates a destination flag overlay
		Drawable destFlag = mapView.getResources().getDrawable(R.drawable.destination_flag);
		destOverlay = new MarkerOverlay(destFlag, mapView);

		//Adds the created overlays		
		mapView.getOverlays().add(sourceOverlay);
		mapView.getOverlays().add(destOverlay);	
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
				focusedGeoPoint = mapView.getProjection().fromPixels((int)touchStopX, (int)touchStopY);
				AlertDialog.Builder options = new AlertDialog.Builder(context);
				options.setTitle("Options");
				options.setMessage("Coordinates:\nLatitude: " + focusedGeoPoint.getLatitudeE6()/1E6 + "\nLongitude: " 
						+ focusedGeoPoint.getLongitudeE6()/1E6 + "\n\nWhat do you want to do?");
				options.setNegativeButton("Set destination", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {						
						//updating the destination-GeoPoint
						destGeoPoint = mapView.getProjection().fromPixels((int)touchStopX, (int)touchStopY);
						//Adding a destination marker
						OverlayItem destinationItem = new OverlayItem(destGeoPoint, "Destinationmarker", "This is the chosen destination");
						destOverlay.setMarker(destinationItem);
						mapView.invalidate();
					}
				});
				options.setNeutralButton("Set location", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						if(!useGpsData){
							//myGeoPoint set from coordinates on focus
							myGeoPoint = mapView.getProjection().fromPixels((int)touchStopX, (int)touchStopY);
							//Adding a location marker at the manually set position
							OverlayItem sourceItem = new OverlayItem(myGeoPoint, "Locationmarker", "This is the recent location");
							sourceOverlay.setMarker(sourceItem);
							mapView.invalidate();
						}
						else{
							Toast.makeText(context, "Turn of GPS-location to set manual position", Toast.LENGTH_SHORT).show();
						}
					}
				});
				options.setPositiveButton("Back to Map", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, "Back to map", Toast.LENGTH_SHORT).show();
					}
				});
				options.show();
				return true;
			}
		}
		return false;
	}
	public MarkerOverlay getDestOverlay(){
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

	public void onLocationChanged(Location location) {

		if(useGpsData){ //if GPS-data is used the location is set automatically
			//TODO Make the the location-toast optional by a choice in settings
			String text = "Min nuvarande position är: \nLatitud: " + location.getLatitude() + 
					"\nLongitud: " + location.getLongitude();		
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

			//Obtaining the latitude and longitude
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			//myGeoPoint is being set by the collected longitude and latitude
			myGeoPoint = new GeoPoint(lat, lng);
			//Adding a location marker at the obtained geoPoint
			OverlayItem sourceItem = new OverlayItem(myGeoPoint, "Locationmarker", "This is the recent location");
			sourceOverlay.setMarker(sourceItem);
			mapView.invalidate();
		}
		else{ //if GPS-data is not used the location is set manually
			Toast.makeText(context, "Manually set location", Toast.LENGTH_SHORT).show();
		}
	}

	public void onProviderDisabled(String provider) {
		Toast.makeText(context, "GPS Disabled", Toast.LENGTH_SHORT).show();
	}

	public void onProviderEnabled(String provider) {
		Toast.makeText(context, "GPS Enabled", Toast.LENGTH_SHORT).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onBackPressed() {
		// Stopping the update och GPS-status, when closing
		// map-activity/pressing the back-button in the map-activity
		locManager.removeUpdates(this);
	}
	private void drawChosenEntrances (Intent intent) {
		// Retrieves info about the chosen classroom from the database
		String cthLectureRoom = intent
				.getStringExtra(ChooseLocationActivity.CTHLECTURE_ROOM);
		String cthBuilding = intent
				.getStringExtra(ChooseLocationActivity.CTHBUILDING);
		int [] doorCoordinates = coordinateParser.parseCoordinatesFromString(intent
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
	private void drawAllEntrances(Intent intent){

		DatabaseHandler db = new DatabaseHandler(context);
		List<Door> doors =  db.getAllDoorsAndBuildings();
		List<Door> editDoors = new ArrayList<Door>();
		List<Door> maskinDoors = new ArrayList<Door>();
		List<Door> haDoors = new ArrayList<Door>();
		List<Door> hbDoors = new ArrayList<Door>();
		List<Door> hcDoors = new ArrayList<Door>();

		// Splits the list of doors into a list of each building
		for(int i=0; i<doors.size();i++){
			if(doors.get(i).getBuilding().equals("EDIT-huset")){
				editDoors.add(doors.get(i));
			}
			else if (doors.get(i).getBuilding().equals("Maskinhuset")){
				maskinDoors.add(doors.get(i));
			}
			else if (doors.get(i).getBuilding().equals("HA")){
				haDoors.add(doors.get(i));
			}
			else if (doors.get(i).getBuilding().equals("HB")){
				hbDoors.add(doors.get(i));
			}
			else if (doors.get(i).getBuilding().equals("HC")){
				hcDoors.add(doors.get(i));
			}
		}
		//Adds the overlay into the mapview				
		
		mapView.getOverlays().add(generateBuildingOverlay(maskinDoors));
		mapView.getOverlays().add(generateBuildingOverlay(haDoors));
		mapView.getOverlays().add(generateBuildingOverlay(hbDoors));
		mapView.getOverlays().add(generateBuildingOverlay(hcDoors));
		mapView.getOverlays().add(generateBuildingOverlay(editDoors));
	}

	private BuildingOverlay generateBuildingOverlay(List<Door> doors){

		Set<Integer> doorCoordinates = coordinateParser.parseCoordinatesToSetFromDoors(doors);

		// Creates clickable map overlays for the chosen classrooms closest entrances
		Drawable buildingIcon = setBuildingIcon(doors.get(0).getBuilding());
		BuildingOverlay buildingOverlay = new BuildingOverlay(buildingIcon, context);
		Iterator<Integer> iterator = doorCoordinates.iterator();
		while (iterator.hasNext()){
			int lat = iterator.next();
			int lon = iterator.next();
			GeoPoint entranceGeoPoint = new GeoPoint(lat, lon);
			OverlayItem entranceOverlayItem = new OverlayItem(entranceGeoPoint,
					"Entrance" + " " + (doors.get(0).getBuilding()), "Classrooms close to this entrance:");
			buildingOverlay.addOverlay(entranceOverlayItem);
			System.out.println(entranceGeoPoint.getLatitudeE6()+ " "+ entranceGeoPoint.getLongitudeE6());
			System.out.println(lat+ " " +lon);
		}
		System.out.println(buildingOverlay.size());
		return buildingOverlay;
	}
	//Drawing the line
	public void draw(Canvas canvas, MapView mapview, boolean shadow){
		super.draw(canvas, mapView, shadow);

		//Customizing the paint-brush
		Paint mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(4);

		//Points
		Point myPoint = new Point();
		Point destPoint = new Point();
		Path path1 = new Path();

		if(myGeoPoint!=null && destGeoPoint!=null){
			projection.toPixels(myGeoPoint, myPoint);//converting GeoPoints to Points
			projection.toPixels(destGeoPoint, destPoint);
			path1.moveTo(myPoint.x, myPoint.y);//Moving to myPoint (my location)
			path1.lineTo(destPoint.x,destPoint.y);//Path to destPoint (my destination)
			canvas.drawPath(path1, mPaint);//Drawing the path
		}	
	}
	//Method to toggle the use of GPS-data on and off
	public void toggleUseGpsData(){
		useGpsData = !useGpsData;
		String useGps = "The use of GPS-data is turned: ";
		if(useGpsData){
			Toast.makeText(context, useGps + "ON", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(context, useGps + "OFF", Toast.LENGTH_SHORT).show();
		}
	}

}
