package se.chalmers.project14.view;

import se.chalmers.project14.main.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class HouseListItem extends LinearLayout{

	
	public HouseListItem(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.row, this, true);
		// TODO Auto-generated constructor stub
	}
//	public HouseListItem(Context context,) {
//		super(context);
//		LayoutInflater inflater = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		inflater.inflate(R.layout.row, this, true);
//		// TODO Auto-generated constructor stub
//	}
	
	public void setHouse(){
		findViewById(R.id.text123);
	}
	
	
	

}
