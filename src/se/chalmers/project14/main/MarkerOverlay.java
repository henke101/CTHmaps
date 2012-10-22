package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * 
 *Class containing an overlayitem. This overlayitem could be a destination- or location-marker.
 * 
 * @version
 * 
 *          0.2 21 Oktober 2012
 * @author
 * 
 *         Anton Palmqvist
 */
public class MarkerOverlay extends ItemizedOverlay{
	private MapView mapView;
	private OverlayItem markerItem;
	
	/**
	 * Constructor creating the markeroverlay.
	 * @param marker The icon to draw on the position of the mapview.
	 * @param mapView MapView
	 */
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
	/**
	 * Method putting out a markerItem on the map, replacing an older markerItem if there is one.
	 * @param markerItem OverlayItem
	 */
	public void setMarker(OverlayItem markerItem){
		this.markerItem = markerItem;
		populate();
	}

	/**
	 * Method removing the existing marker if there is one.
	 */
	public void removeDestinationMarker() {
		markerItem=null;
		populate();
	}
}
