package se.chalmers.project14.model;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selld�n and Marcus Tyr�n
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

import android.view.GestureDetector.*;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class SwipeHandler extends SimpleOnGestureListener {
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	ViewFlipper flipper;

	public SwipeHandler(ViewFlipper f) {
		super();
		flipper = f;

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {		
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;
			// right to left swipe
			if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				
				flipper.showNext();
				// left to right swipe
			}  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			
				flipper.showNext();
			}
		 
		return false;
	}
	@Override
	public boolean onDown(MotionEvent e) {
	    return true;        
	}



}
