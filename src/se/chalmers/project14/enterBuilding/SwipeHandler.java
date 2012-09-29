package se.chalmers.project14.enterBuilding;

import se.chalmers.project14.main.R;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class SwipeHandler implements OnClickListener {
	ViewFlipper flipper;
	
	public SwipeHandler(ViewFlipper f) {
		flipper = f;
		
	}


	public void onClick(View v) {
		flipper.showNext();
		
		
	}

}
