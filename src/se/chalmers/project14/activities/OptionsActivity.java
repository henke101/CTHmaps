package se.chalmers.project14.activities;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

import se.chalmers.project14.activities.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class OptionsActivity extends Activity {


	CheckBox cbS;
	OnClickListener checkBoxListener;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/** Called when the activity is first created. */
		setContentView(R.layout.layout_options);
		super.onCreate(savedInstanceState);
		
		cbS=(CheckBox)findViewById(R.id.satDefault);
		checkBoxListener =new OnClickListener() {
			
			public void onClick(View v) {
				if(cbS.isChecked()){
					//get's null pointer
					//mapView.setSatellite(true);
					//mapView.invalidate();
				}

			}};
			cbS.setOnClickListener(checkBoxListener);
		}
	}	
