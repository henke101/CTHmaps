package se.chalmers.project14.model.overlay;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selld�n and Marcus Tyr�n
 * MIT is the used license. See the file license.txt for copying permission.
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
 *         System version 0.3 21 Oktober 2012
 * @author
 * 
 *         Anton Palmqvist
 */
public class MarkerOverlay extends ItemizedOverlay<OverlayItem>{
	private OverlayItem markerItem;
	
	/**
	 * Constructor creating the markeroverlay.
	 * @param marker The icon to draw on the position of the mapview.
	 * @param mapView MapView
	 */
	public MarkerOverlay(Drawable marker, MapView mapView) {
		/* Fixing so that the flag is pointed to the lower left corner*/
		super(boundCenterBottom(marker));
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
