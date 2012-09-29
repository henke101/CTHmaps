package se.chalmers.project14.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class DatabaseInput {
	private Context context;

	public DatabaseInput(Context context) {
		this.context = context;
	}

	public void readCoordinatesFile() throws IOException {
		DatabaseHandler db = new DatabaseHandler(context);
		AssetManager am = context.getAssets();
		InputStream is = am.open("Coordinates.txt");
		Log.d("DatabaseInput", "read Coordinates.txt whitout error!");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String coordinateLine = null;
		String[] coordinateInput = null;
		int coordinateKey;
		while ((coordinateLine = br.readLine()) != null) {
			Log.d("coordinateLine = ", coordinateLine);
			coordinateInput = coordinateLine.split(":");
			int pieceOfCoordinates = coordinateInput.length;
			Log.d("piece of coordinates ", "" + pieceOfCoordinates);
			Log.d("coordinateInput = ", coordinateInput[0] + " "
					+ coordinateInput[1] + " " + coordinateInput[2]);
			coordinateKey = Integer.parseInt(coordinateInput[0]);
			Coordinates coordinate = new Coordinates(coordinateKey,
					coordinateInput[1], coordinateInput[2]);
			db.addCordinates(coordinate);
			Log.d("database ", "added new coordinates");
			// coordinateKey = Integer.parseInt(coordinateInput[0]);
			// db.addCordinates(new Coordinates(coordinateKey,
			// coordinateInput[1],
			// coordinateInput[2]));
		}
	}
	//
	// public void addCoordinatesToDatabase(Coordinates coordinate) {
	// DatabaseHandler db = new DatabaseHandler(context);
	// db.addCordinates(new Coordinates(1, "Tomas", "77777777,777777"));
	// db.addCordinates(new Coordinates(1, "Tomas", "77777777,777777"));
	// db.addCordinates(new Coordinates(2, "Tomas", "77777777,777777"));
	// db.addCordinates(new Coordinates(3, "Tomas", "11111,11111"));
	// }
}
