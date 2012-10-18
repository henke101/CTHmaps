package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
	private Context context;
	
	public MyLocationListener(Context context) {
		this.context=context;
	}
	
	public void onLocationChanged(Location location) {
		String text = "Min nuvarande position är: \nLatitud: " + location.getLatitude() + 
				"\nLongitud: " + location.getLongitude();		
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public void onProviderDisabled(String provider) {
		Toast.makeText(context, "GPS Disabled", Toast.LENGTH_SHORT).show();
	}

	public void onProviderEnabled(String provider) {
		Toast.makeText(context, "GPS Enabled", Toast.LENGTH_SHORT).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
