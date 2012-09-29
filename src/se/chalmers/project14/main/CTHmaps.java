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
		DatabaseHandler db = new DatabaseHandler(this);
		Coordinates coordinates = new Coordinates();
		// Intent intent = getIntent();
		// String cthLocation = intent
		// .getStringExtra(ChooseLocationActivity.EXTRA_MESSAGE);

		Log.d("inserting ", "is");
		// Log.d(" input is ", cthLocation);
		// db.addCordinates(new Coordinates("Edithuset", "57.68744,11.979491"));

		Log.d("have", "added Edithuset");
		coordinates = db.getCoordinates("Tomas");
		Log.d("invoked", "getCoordinates Tomas");
		String cthPlace = coordinates.getCTHplace();
		String coord = coordinates.getCoordinates();
		Log.d("From CTHmaps " + cthPlace, "From database getCoordinate method "
				+ coord);
		coordinates = db.getCoordinates("FirstTry");
		Log.d("FirstTry getting the coordinates from database ", ""
				+ coordinates.getID() + " " + coordinates.getCTHplace() + " "
				+ coordinates.getCoordinates());
		coordinates = db.getCoordinates("AdamOchEva");
		Log.d("AdamOchEva getting the coordinates from database ", ""
				+ coordinates.getID() + " " + coordinates.getCTHplace() + " "
				+ coordinates.getCoordinates());
		coordinates = db.getCoordinates("SecondTry");
		Log.d("SecondTry getting the coordinates from database ", ""
				+ coordinates.getID() + " " + coordinates.getCTHplace() + " "
				+ coordinates.getCoordinates());
		List<Coordinates> coordinateList = db.getAllCoordinates();
		for (Coordinates c : coordinateList) {
			String log = (" " + c.getCTHplace());
			Log.d("Name: ", log);
		}
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
