package utils;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import com.google.android.maps.MapView;

import se.chalmers.project14.main.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Options extends Activity {
	
	private MapView mapView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/** Called when the activity is first created. */
		setContentView(R.layout.layout_options);
		super.onCreate(savedInstanceState);
		
//		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//		
//		StringBuilder builder = new StringBuilder();
//		builder.append("\n" + sharedPrefs.getBoolean("perform_updates", false));

	}
	public boolean checkBox(View view){
		if(true){
			mapView.setSatellite(true);
		}
		return false;	
	}
}
