package se.chalmers.project14.main;

import java.util.ArrayList;

import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class DestinationMarkerOverlay extends ItemizedOverlay{
	//List of overlayitems, made generic to simplify future functions
	private ArrayList<OverlayItem> destOverlays = new ArrayList<OverlayItem>();
	
	public DestinationMarkerOverlay(Drawable marker) {
		super(boundCenterBottom(marker));
	}

	@Override
	protected OverlayItem createItem(int i) {
		return destOverlays.get(i);
	}

	@Override
	public int size() {
		return destOverlays.size();
	}

	//Adding an item (a flagmarker) to the list of destinationoverlays
	public void addOverlay(OverlayItem overlayitem) {
		destOverlays.add(overlayitem);
		populate();		
	}

}
