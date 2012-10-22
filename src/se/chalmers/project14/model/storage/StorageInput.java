package se.chalmers.project14.model.storage;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.model.Door;
import se.chalmers.project14.model.House;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * This class reads a file which contains buildings and coordinates connected to
 * each other and adds this to the database
 * 
 * @author tomassellden
 * 
 */
public class StorageInput {

	private Context context;

	public StorageInput(Context context) {
		this.context = context;
	}

	/**
	 * Reading the file Coordinate.txt and seperate every line to a String
	 * 
	 * @throws IOException
	 */
	public void readCoordinatesFile() throws IOException {
		AssetManager am = context.getAssets();
		InputStream is = am.open("Coordinates.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String coordinateLine = null;
		while ((coordinateLine = br.readLine()) != null) {
			addCoordinatesToDatabase(coordinateLine);

		}
	}

	/**
	 * In this method we create a new CoordinateObject and adds this to the
	 * database
	 * 
	 * @param coordinateLine
	 *            a String representing a row line from the file Coordinates.txt
	 */
	private void addCoordinatesToDatabase(String coordinateLine) {
		Storable db = new Storage(context);
		String[] coordinateInput = coordinateLine.split(":");
		int coordinateKey = Integer.parseInt(coordinateInput[0]);
		House house = new House(coordinateKey, coordinateInput[1],
				coordinateInput[2]);
		Coordinates coordinates = new Coordinates(coordinateInput[3]);
		Door door = new Door(coordinateInput[4], coordinateInput[5]);
		db.addCthHouse(house, coordinates, door);
	}
}
