package se.chalmers.project14.util;

import java.util.Arrays;
import java.util.List;

import se.chalmers.project14.database.Coordinates;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.google.android.maps.MapActivity;

public class BuildingBoundary extends MapActivity implements LocationListener{
	
	private int x1,y1,x2,y2,x3,y3,x4,y4,lat,lon;
	private Coordinates c = new Coordinates();
	
	public BuildingBoundary(){
		
	}
	
	public void onLocationChanged(Location loc) {
//		loc.getLatitude();
//		loc.getLongitude();
		lat = (int)loc.getLatitude();
		lon = (int)loc.getLongitude();
	}        
	public boolean isPointInside(){
		
		String s = c.getCoordinates();
	    String[] ss = s.split(",");
	    List list = Arrays.asList(ss);
	    
	    x1 = (Integer) list.get((int)0);
	    y1 = (Integer) list.get((int)1);
	    x2 = (Integer) list.get((int)2);
	    y2 = (Integer) list.get((int)3);
	    x3 = (Integer) list.get((int)4);
	    y3 = (Integer) list.get((int)5);
	    x4 = (Integer) list.get((int)6);
	    y4 = (Integer) list.get((int)7);
		
	    return x1 <= lat && lat <= x2 && x4 <= lat && lat <= x3 &&
				y1 <= lon && lon <= y2 && lon <= y3 && y4 <=lon;
	}
	public void pointInside(BuildingBoundary b){
		//if this is true then show the indoor view
		if(b.isPointInside() == true){
			System.out.print("im inside the buildiings borders");
			// want another method to ask if i want to do this before it happens
			Intent intent = new Intent("se.chalmers.project14.enterBuilding.FloorViewer"); 
			startActivity(intent);
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
