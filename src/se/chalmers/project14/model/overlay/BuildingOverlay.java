package se.chalmers.project14.model.overlay;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import java.util.*;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class BuildingOverlay extends ItemizedOverlay<OverlayItem>{

	private ArrayList<OverlayItem> buildingOverlays = new ArrayList<OverlayItem>();

	public BuildingOverlay(Drawable pic) {
		super(boundCenterBottom(pic));
	}

	public BuildingOverlay(Drawable pic, Context context) {
		super(boundCenterBottom(pic));
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
