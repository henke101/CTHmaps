package se.chalmers.project14.main;


import java.util.*;



import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
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

    public boolean onTouchEvent(MotionEvent event, MapView mapview){
		
		return false;
	}
	
	@Override
	protected boolean onTap(int index) {
		Intent intent = new Intent(buildingContext, se.chalmers.project14.enterBuilding.FloorViewer.class);
		buildingContext.startActivity(intent);
	  
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
 