package se.chalmers.project14.activities;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

import java.io.IOException;

import se.chalmers.project14.activities.R;
import se.chalmers.project14.model.storage.StorageInput;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		StorageInput di = new StorageInput(this);
		try {
			di.readCoordinatesFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openMain = new Intent(
							"se.chalmers.project14.activities.ChooseLocationActivity");
					startActivity(openMain);
				}
			}
		};
		timer.start();
	}

}
