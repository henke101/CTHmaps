package utils;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import com.google.android.maps.MapView;

import se.chalmers.project14.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class Options extends Activity {

	private MapView mapView;
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
