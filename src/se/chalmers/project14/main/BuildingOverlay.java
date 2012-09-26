package se.chalmers.project14.main;


import java.util.*;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class BuildingOverlay extends ItemizedOverlay {

	private ArrayList<OverlayItem> buildingOverlays = new ArrayList<OverlayItem>();
	private Context buildingContext;
	
	public BuildingOverlay(Drawable pic) {
		 super(boundCenterBottom(pic));
	}
	
	public BuildingOverlay(Drawable pic, Context context) {
		  super(boundCenterBottom(pic));
		  buildingContext = context;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    buildingOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = buildingOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(buildingContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return buildingOverlays.get(i);
	}

	@Override
	public int size() {
		return buildingOverlays.size();
	}

}
