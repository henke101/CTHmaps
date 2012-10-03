package se.chalmers.project14.main;

import se.chalmers.project14.database.Coordinates;
import se.chalmers.project14.database.DatabaseHandler;
import se.chalmers.project14.enterBuilding.FloorViewer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class ChooseLocationActivity extends Activity {

	public final static String EXTRA_MESSAGE = "se.chalmers.project14.main.EXTRA_MESSAGE";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_location);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_choose_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void searchLocationButton(View view) {
		Intent intent = new Intent(this, Map.class);
		DatabaseHandler db = new DatabaseHandler(this);
		Coordinates coordinate = db.getCoordinates("EDIT-huset");
		String coordinates = coordinate.getCoordinates();
		Log.d(" input from coordinates are ", coordinates);
		intent.putExtra(EXTRA_MESSAGE, coordinates);
		startActivity(intent);

	}

}
