package se.chalmers.project14.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * This class reads a file which contains buildings and coordinates connected to
 * each other and adds this to the database
 * 
 * @author tomassellden
 * 
 */
public class DatabaseInput {
	private Context context;

	public DatabaseInput(Context context) {
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
		DatabaseHandler db = new DatabaseHandler(context);
		String[] coordinateInput = coordinateLine.split(":");
		int coordinateKey = Integer.parseInt(coordinateInput[0]);
		Coordinates coordinate = new Coordinates(coordinateKey,
				coordinateInput[1], coordinateInput[2]);
		db.addCordinates(coordinate);
	}
}
