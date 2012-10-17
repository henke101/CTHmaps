package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MarkerOverlay extends ItemizedOverlay{
	private MapView mapView;
	private OverlayItem markerItem;
	public MarkerOverlay(Drawable marker, MapView mapView) {
		/* Fixing so that the flag is pointed to the lower left corner*/
		super(boundCenterBottom(marker));
		this.mapView=mapView;
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return markerItem;
	}

	@Override
	public int size() {
		if(markerItem==null){
			return 0;
		}
		else{
			return 1;
		}
	}
	
	//Putting out an item (a flagmarker)
	public void setMarker(OverlayItem markerItem){
		this.markerItem = markerItem;
		populate();
	}

	public void removeDestinationMarker() {
		markerItem=null;
		populate();
	}
}
