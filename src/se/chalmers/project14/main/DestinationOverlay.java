package se.chalmers.project14.main;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class DestinationOverlay extends ItemizedOverlay{
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context context;
	private long touchStart, touchStop;
	private float touchStartX, touchStartY, touchStopX, touchStopY;
	
	public DestinationOverlay(Drawable marker, Context context) {
		super(boundCenterBottom(marker));//Fixing so that the flag is pointed to the lower left corner
		this.context=context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouchEvent(MotionEvent e, MapView m){
		if(e.getAction()==MotionEvent.ACTION_DOWN){
			touchStart=e.getEventTime();
			touchStartX=e.getX();
			touchStartY=e.getY();
		}
		if(e.getAction()==MotionEvent.ACTION_UP){
			touchStop=e.getEventTime();
			touchStopX=e.getX();
			touchStopY=e.getY();
		}
		/*Checking holdtime to be above 1500 ms and at he same position*/
		if(touchStop-touchStart>1500 && touchStartX==touchStopX && touchStartY==touchStopY){
			AlertDialog options = new AlertDialog.Builder(context).create();
			options.setTitle("Options");
			options.setMessage("What do you want to do?");
			options.setButton("Set destination", new DialogInterface.OnClickListener(){
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			options.setButton2("Show coordinates", new DialogInterface.OnClickListener(){
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			options.show();
			return true;
		}
		return false;
	}
}
