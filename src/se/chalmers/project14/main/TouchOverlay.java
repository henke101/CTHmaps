package se.chalmers.project14.main;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TouchOverlay extends Overlay {
	//private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context context;
	private long touchStart, touchStop;
	private float touchStartX = 1, touchStartY = 2, touchStopX = 3,
			touchStopY = 4;
	private MapView mapView;
	private GeoPoint geoPoint;
	private DestinationMarkerOverlay destOverlay;

	public TouchOverlay(Context context, MapView mapView) {
		super();
		this.context = context;
		this.mapView=mapView;
		
		// Creates clickable map overlays for the EDIT-house entrances
		
		Drawable editIcon = mapView.getResources().getDrawable(R.drawable.edit);
		BuildingOverlay editOverlay = new BuildingOverlay(editIcon, context);
		GeoPoint edit1GeoPoint = new GeoPoint(57687808, 11979096);
		OverlayItem edit1OverlayItem = new OverlayItem(edit1GeoPoint,
				"Entrance EDIT huset", "Classrooms close to this entrance:");
		editOverlay.addOverlay(edit1OverlayItem);
		GeoPoint edit2GeoPoint = new GeoPoint(57687458, 11978455);
		OverlayItem edit2OverlayItem = new OverlayItem(edit2GeoPoint,
				"Entrance EDIT huset", "Classrooms close to this entrance:");
		editOverlay.addOverlay(edit2OverlayItem);
		GeoPoint edit3GeoPoint = new GeoPoint(57688242, 11978600);
		OverlayItem edit3OverlayItem = new OverlayItem(edit3GeoPoint,
				"Entrance EDIT huset", "Classrooms close to this entrance:");
		editOverlay.addOverlay(edit3OverlayItem);
		
		
		// Creates a destination flag overlay
		Drawable destFlag = mapView.getResources().getDrawable(R.drawable.destination_flag);
		destOverlay = new DestinationMarkerOverlay(destFlag, mapView);
		
		//Adds the created overlays
		mapView.getOverlays().add(destOverlay);
		mapView.getOverlays().add(editOverlay);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event, MapView m) {
		// when user touches the screen
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			touchStart = event.getEventTime();
			touchStartX = event.getX();
			touchStartY = event.getY();
		}// when screen is released
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			touchStop = event.getEventTime();
			touchStopX = event.getX();
			touchStopY = event.getY();

			/*
			 * Checking holdtime to be above 1000 ms and at he same position
			 */
			if (touchStop - touchStart > 1000 && touchStartX <= touchStopX+20 && touchStartX >= touchStopX-20 
					&& touchStartY <= touchStopY+20 && touchStartY >= touchStopY-20) {
				geoPoint = mapView.getProjection().fromPixels((int)touchStopX, (int)touchStopY);
				AlertDialog.Builder options = new AlertDialog.Builder(context);
				options.setTitle("Options");
				options.setMessage("Coordinates:\nLatitude: " + geoPoint.getLatitudeE6()/1E6 + "\nLongitude: " 
						+ geoPoint.getLongitudeE6()/1E6 + "\n\nWhat do you want to do?");
				options.setNegativeButton("Set destination", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {						
						//Adding a destination marker
						OverlayItem destinationItem = new OverlayItem(geoPoint, "Destinationmarker", "This is the chosen destination");
						destOverlay.setDestination(destinationItem);
				        mapView.invalidate();
					}
				});
				options.setPositiveButton("Back to Map", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, "Test2", Toast.LENGTH_SHORT).show();
					}
				});
				options.show();
				return true;
			}
		}
		return false;
	}
	public DestinationMarkerOverlay getDestOverlay(){
		return destOverlay;
	}
}
