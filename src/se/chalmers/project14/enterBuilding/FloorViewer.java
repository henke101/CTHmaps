package se.chalmers.project14.enterBuilding;

import se.chalmers.project14.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.SlidingDrawer;
import android.widget.ViewFlipper;
import android.support.v4.app.NavUtils;

public class FloorViewer extends Activity{

	ViewFlipper floorFlipper;
	OnClickListener swipeHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_viewer);
        floorFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
        swipeHandler = new SwipeHandler(floorFlipper);
        floorFlipper.setOnClickListener(swipeHandler);
        
        
        /*getActionBar().setDisplayHomeAsUpEnabled(true);
        IS NEVER USED AND ONLY CREATES PROBLEMS / HENKE */
    }

    /*@Override IS NEVER USED AND ONLY CREATES PROBLEMS / HENKE
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_floor_viewer, menu);
        return true;
    }

    
    @Override IS NEVER USED AND ONLY CREATES PROBLEMS / HENKE
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

	

}