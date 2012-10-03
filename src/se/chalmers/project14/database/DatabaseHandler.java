package se.chalmers.project14.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A class representing the database, It creates the database and have methods
 * so we can add and get values from our database
 * 
 * @author tomassellden
 * 
 */
public class DatabaseHandler extends SQLiteOpenHelper {
	private Context context;

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "cordinatesManager";
	private static final String TABLE_COORDINATES = "CoordinateTable";

	private static final String KEY_ID = "id";
	private static final String KEY_CTHPLACE = "CTHplace";
	private static final String KEY_COORDINATES = "coordinates";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;

	}

	/**
	 * Creates the database. It«s creating a table which contain of three
	 * columns, an int representing the key, a String representing the building
	 * and another String representing the coordinates that are connected to the
	 * building
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_COORDINATES_TABLE = "CREATE TABLE " + TABLE_COORDINATES
				+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CTHPLACE
				+ " TEXT," + KEY_COORDINATES + " TEXT" + ")";

		db.execSQL(CREATE_COORDINATES_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COORDINATES);
		onCreate(db);

	}

	/**
	 * 
	 * @param coordinates
	 *            Coordinates you want to add to the database
	 */
	public void addCordinates(Coordinates coordinates) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_ID, coordinates.getID());
		contentValues.put(KEY_CTHPLACE, coordinates.getCTHplace());
		contentValues.put(KEY_COORDINATES, coordinates.getCoordinates());

		db.insertWithOnConflict(TABLE_COORDINATES, null, contentValues,
				SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
	}

	/**
	 * A method that returns the coordinates that are connected to the String
	 * parameter
	 * 
	 * @param CTHplace
	 *            A String representing the building which the coordinates are
	 *            connected to.
	 * @return The coordinates that the String is connected to
	 */
	public Coordinates getCoordinates(String CTHplace) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_COORDINATES, new String[] { KEY_ID,
				KEY_CTHPLACE, KEY_COORDINATES }, KEY_CTHPLACE + " = '"
				+ CTHplace + "'", null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}

		Coordinates coordinates = new Coordinates(cursor.getString(1),
				cursor.getString(2));

		db.close();
		cursor.close();
		return coordinates;
	}

	/**
	 * 
	 * @return a List which contain all building that exists in the database
	 */
	public List<Coordinates> getAllCoordinates() {
		List<Coordinates> coordinateList = new ArrayList<Coordinates>();
		String getCoordinates = "SELECT * FROM " + TABLE_COORDINATES;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(getCoordinates, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Coordinates coordinate = new Coordinates();
			coordinate.setCTHplace(cursor.getString(1));
			coordinateList.add(coordinate);
			cursor.moveToNext();
		}
		db.close();
		cursor.close();

		return coordinateList;
	}
}
