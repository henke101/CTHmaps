package se.chalmers.project14.activities;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import se.chalmers.project14.activities.R;
import se.chalmers.project14.model.SwipeHandler;
import se.chalmers.project14.model.overlay.OverlayHolder;
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
		String building = getIntent().getStringExtra(OverlayHolder.CHOSEN_BUILDING);
		
		//initialize(building);
		setContentView(R.layout.edit_view);
		floorFlipper = (ViewFlipper) findViewById(R.id.editFlipper);
		System.out.println(building);
				
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

	private void initialize(String building) {
		if (building.equals("EDIT-huset")){
			setContentView(R.layout.edit_view);
			floorFlipper = (ViewFlipper) findViewById(R.id.editFlipper);
		}
		else if (building.equals("Maskinhuset")){
			setContentView(R.layout.maskin_view);
			floorFlipper = (ViewFlipper) findViewById(R.id.maskinFlipper);
		}
		else if (building.equals("HA")){
			setContentView(R.layout.ha_view);
			floorFlipper = (ViewFlipper) findViewById(R.id.haFlipper);
		}
		else if (building.equals("HB")){
			setContentView(R.layout.hb_view);
			floorFlipper = (ViewFlipper) findViewById(R.id.hbFlipper);
		}
		else if (building.equals("HC")){
			setContentView(R.layout.hc_view);
			floorFlipper = (ViewFlipper) findViewById(R.id.hcFlipper);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (detector.onTouchEvent(event))
			return true;
		else
			return false;


	}

}