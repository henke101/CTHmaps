package se.chalmers.project14.util;

import java.util.Arrays;
import java.util.List;

import se.chalmers.project14.database.Coordinates;
import se.chalmers.project14.main.CTHmaps;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

public class BuildingBoundary extends MapActivity implements LocationListener{
	
	private int x1,y1,x2,y2,x3,y3,x4,y4,lat,lon;
	private CTHmaps m;
	private LocationManager lm;

	public BuildingBoundary(CTHmaps m){
		m = this.m;
	}
	
	public void onLocationChanged(Location loc) {
//		loc.getLatitude();
//		loc.getLongitude();
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//---PendingIntent to launch activity if the user is within a certain location
		PendingIntent pendIntent = PendingIntent.getActivity(this, 0, new Intent(context,se.chalmers.project14.enterBuilding.FloorViewer.class), 0);
		lm.addProximityAlert(latitude, longitude, radius, expiration, pendIntent);
		lat = (int)loc.getLatitude();
		lon = (int)loc.getLongitude();
	}        
	public boolean isPointInside(){
		
		String s = 
	    String[] ss = s.split(",");
	    List list = Arrays.asList(ss);
	    
	    x1 = (Integer) list.get(0);
	    y1 = (Integer) list.get(1);
	    x2 = (Integer) list.get(2);
	    y2 = (Integer) list.get(3);
	    x3 = (Integer) list.get(4);
	    y3 = (Integer) list.get(5);
	    x4 = (Integer) list.get(6);
	    y4 = (Integer) list.get(7);
	    
	    GeoPoint ul = new LatLonPoint(x1,y1);
	    GeoPoint ur = new LatLonPoint(x2,y2);
	    GeoPoint ll = new LatLonPoint(x3,y3);
	    GeoPoint lr = new LatLonPoint(x4,y4);
	    GeoPoint me = new LatLonPoint(lat,lon);
	    
	    return ul <= me && lat <= x2 && x4 <= lat && lat <= x3 &&
				y1 <= lon && lon <= y2 && lon <= y3 && y4 <=lon;
	}
	public void pointInside(BuildingBoundary b){
		//if this is true then show the indoor view
		if(b.isPointInside() == true){
			System.out.print("im inside the buildiings borders");
			// want another method to ask if i want to do this before it happens
			Intent fview = new Intent(this,se.chalmers.project14.enterBuilding.FloorViewer.class); 
			startActivity(fview);
		}
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
