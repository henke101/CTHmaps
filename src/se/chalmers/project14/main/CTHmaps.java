package se.chalmers.project14.main;

import java.util.*;

import com.google.android.maps.*;

import se.chalmers.project14.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class CTHmaps extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //Enabling zooming
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.edit);
        BuildingOverlay overlay = new BuildingOverlay(drawable, this);
        GeoPoint point = new GeoPoint(57687855,11979225);
        OverlayItem overlayEdit = new OverlayItem(point, "Hej!", "Nu ska jag gå in till EDIT-huset");
        overlay.addOverlay(overlayEdit);
        mapOverlays.add(overlay);
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
