package se.chalmers.project14.util;

import com.google.android.maps.GeoPoint;

public class HouseBorder {
	public float x1,y1,x2,y2;
	
	public HouseBorder(){
		
	}
	
	public boolean isPointInside(HouseBorder b,float x, float y){
		return b.x1 <= x && x <= b.x2 &&
				b.y1 <= y && y <= b.y2;
	}
	GeoPoint region = self.mapView.region;

	Google location = user.gpsposition.coordinate;
	Google center   = region.center;
	Google northWestCorner, southEastCorner;

	northWestCorner.latitude  = center.latitude  - (region.span.latitudeDelta  / 2.0);
	northWestCorner.longitude = center.longitude - (region.span.longitudeDelta / 2.0);
	southEastCorner.latitude  = center.latitude  + (region.span.latitudeDelta  / 2.0);
	southEastCorner.longitude = center.longitude + (region.span.longitudeDelta / 2.0);

	if (
	    location.latitude  >= northWestCorner.latitude && 
	    location.latitude  <= southEastCorner.latitude &&

	    location.longitude >= northWestCorner.longitude && 
	    location.longitude <= southEastCorner.longitude
	    )
	{	 
	}else {  
	}
}
