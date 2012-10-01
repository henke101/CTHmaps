package se.chalmers.project14.main;

import se.chalmers.project14.database.DatabaseHandler;
import se.chalmers.project14.enterBuilding.FloorViewer;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v4.app.NavUtils;

@TargetApi(11)
public class ChooseLocationActivity extends ListActivity {

	public final static String EXTRA_MESSAGE = "se.chalmers.project14.main.EXTRA_MESSAGE";

	@TargetApi(11)
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_location);
		DatabaseHandler db = new DatabaseHandler(this);
		Cursor c = db.fetchAllCoordinates();
		startManagingCursor(c);
		SimpleCursorAdapter cursor = new SimpleCursorAdapter(this,
				R.layout.row, c, new String[] { DatabaseHandler.KEY_CTHPLACE },
				new int[] { R.id.text123 }, 0);

		setListAdapter(cursor);

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
		Intent intent = new Intent(this, CTHmaps.class);
		EditText editText = (EditText) findViewById(R.id.search_locationText);
		String location = editText.getText().toString(); // I have Edithuset ==
		intent.putExtra(EXTRA_MESSAGE, location);
		startActivity(intent);
	}

}
