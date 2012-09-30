package se.chalmers.project14.main;

import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import se.chalmers.project14.database.Coordinates;
import se.chalmers.project14.database.DatabaseHandler;
import se.chalmers.project14.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.util.Log;

public class CTHmaps extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		// Enabling zooming*/
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
