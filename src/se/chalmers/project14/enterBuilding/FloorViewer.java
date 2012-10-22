package se.chalmers.project14.enterBuilding;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import se.chalmers.project14.main.OverlayHolder;
import se.chalmers.project14.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnTouchListener;
import android.view.GestureDetector.*;
import android.view.animation.Animation;
import android.view.View.OnClickListener;
import android.widget.SlidingDrawer;
import android.widget.ViewFlipper;
import android.support.v4.app.NavUtils;

public class FloorViewer extends Activity {

	ViewFlipper floorFlipper;
	SimpleOnGestureListener swipeHandler;
	GestureDetector detector;
	OnTouchListener gestureListener;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String building = getIntent().getStringExtra(OverlayHolder.NAME_OF_BUILDING);
        
        setContentView(R.layout.edit_viewer);
        floorFlipper = (ViewFlipper) findViewById(R.id.viewFlipper2);
       
        swipeHandler = new SwipeHandler(floorFlipper);
        detector = new GestureDetector(this, swipeHandler);
        gestureListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (detector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };
   
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if (detector.onTouchEvent(event))
	        return true;
	    else
	        return false;


	}

}