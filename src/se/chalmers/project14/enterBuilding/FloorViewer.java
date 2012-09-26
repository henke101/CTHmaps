package se.chalmers.project14.enterBuilding;

import se.chalmers.project14.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;
import android.support.v4.app.NavUtils;

public class FloorViewer extends Activity implements OnClickListener{

	ViewFlipper floorFlipper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_viewer);
        floorFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
        floorFlipper.setOnClickListener(this);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_floor_viewer, menu);
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

	public void onClick(View v) {
		floorFlipper.showNext();
		
	}

}