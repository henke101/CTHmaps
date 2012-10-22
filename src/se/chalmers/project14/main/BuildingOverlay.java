package se.chalmers.project14.main;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import java.util.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class BuildingOverlay extends ItemizedOverlay {

	private ArrayList<OverlayItem> buildingOverlays = new ArrayList<OverlayItem>();
	private Context buildingContext;

	public BuildingOverlay(Drawable pic) {
		super(boundCenterBottom(pic));
	}

	public BuildingOverlay(Drawable pic, Context context) {
		super(boundCenterBottom(pic));
		buildingContext = context;
	}

	public void addOverlay(OverlayItem overlay) {
		buildingOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return buildingOverlays.get(i);
	}

	@Override
	public int size() {
		return buildingOverlays.size();
	}

}
