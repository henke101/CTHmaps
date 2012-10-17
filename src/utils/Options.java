package utils;

import se.chalmers.project14.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Options extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/** Called when the activity is first created. */
		setContentView(R.layout.layout_options);
		super.onCreate(savedInstanceState);
	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.menu_options, menu);
//	    return true;
//	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//	    case R.id.options:
//	    startActivity(new Intent(this, Options.class));
//	    return true;
//	    default:
//		}
//		return super.onOptionsItemSelected(item);
//	}

}
