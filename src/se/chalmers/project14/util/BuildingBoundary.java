package se.chalmers.project14.util;

import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

public class BuildingBoundary extends MapActivity {
	private final Point[] points;
	
	public BuildingBoundary(GeoPoint ul,GeoPoint lr){
		
	}
	public boolean insidePolygon(GeoPoint ul, GeoPoint lr){
		return true;
	}
	public boolean contains(GeoPoint gp){
		int i;
		int j;
		boolean result = false;
		for(i = 0, j = points.length -1; i < points.length; j = i++){
			if((points[i].y > gp.y))
		}
		return true;
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
