package se.chalmers.project14.database;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.model.Door;
import se.chalmers.project14.model.House;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A class representing the database, It creates the database and have methods
 * so we can add and get values from our database
 * 
 * @author tomassellden
 * 
 */
public class DatabaseHandler extends SQLiteOpenHelper {

	private Context context;

	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "cordinatesManager";
	private static final String TABLE_COORDINATE = "CoordinateTable";
	private static final String TABLE_HOUSE = "HouseTable";
	private static final String TABLE_DOORCOORDINATES = "DoorTable";

	private static final String KEY_ID = "_id";
	public static final String KEY_CTHPLACE = "_idCTHplace";
	private static final String KEY_COORDINATES = "_houseCoordinates";
	private static final String KEY_FLOOR = "_floor";
	private static final String KEY_DOOR = "_doorCoordinates";

	private String CREATE_HOUSE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_HOUSE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_CTHPLACE + " TEXT" + ")";

	private String CREATE_COORDINATES_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_COORDINATE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_COORDINATES + " TEXT" + ")";

	private String CREATE_DOOR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_DOORCOORDINATES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_FLOOR + " TEXT," + KEY_DOOR + " TEXT" + ")";

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
		db.execSQL(CREATE_HOUSE_TABLE);
		db.execSQL(CREATE_COORDINATES_TABLE);
		db.execSQL(CREATE_DOOR_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COORDINATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOUSE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOORCOORDINATES);
		onCreate(db);

	}

	/**
	 * Add coordinates to the database
	 * 
	 * @param coordinates
	 *            Coordinates you want to add to the database
	 */
	// public void addCordinates(Coordinates coordinates) {
	// SQLiteDatabase sqdb = this.getWritableDatabase();
	// ContentValues contentValues = new ContentValues();
	// contentValues.put(KEY_ID, coordinates.getId());
	// contentValues.put(KEY_CTHPLACE, coordinates.getCTHplace());
	// contentValues.put(KEY_COORDINATES, coordinates.getCoordinates());
	//
	// sqdb.insertWithOnConflict(TABLE_COORDINATE, null, contentValues,
	// SQLiteDatabase.CONFLICT_IGNORE);
	// sqdb.close();
	// }

	public void addCthHouse(House house, Coordinates coordinates, Door door) {
		SQLiteDatabase sqdb = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		int tableKey = house.getId();
		contentValues.put(KEY_ID, tableKey);
		contentValues.put(KEY_CTHPLACE, house.getHouse());
		sqdb.insertWithOnConflict(TABLE_HOUSE, null, contentValues,
				SQLiteDatabase.CONFLICT_IGNORE);
		Log.d("addCthHouse", " good");
		sqdb.close();
		addCthCoordinates(tableKey, coordinates, door);
	}

	public void addCthCoordinates(int tableKey, Coordinates coordinates,
			Door door) {
		SQLiteDatabase sqdb = this.getWritableDatabase();
		Log.d("addCthCoordinates", " good1");
		ContentValues contentValues = new ContentValues();
		Log.d("addCthCoordinates", " good2");
		contentValues.put(KEY_ID, tableKey);
		Log.d("addCthCoordinates", " good3");
		contentValues.put(KEY_COORDINATES, coordinates.getCoordinates());
		Log.d("addCthCoordinates", " good4");
		Log.d("addCthCoordinates", " " + tableKey);
		Log.d("addCthCoordinates", " " + coordinates.getCoordinates());
		sqdb.insertWithOnConflict(TABLE_COORDINATE, null, contentValues,
				SQLiteDatabase.CONFLICT_IGNORE);
		Log.d("addCthCoordinates", " good5");
		sqdb.close();
		// addCthDoors(tableKey, door);

	}

	public void addCthDoors(int tableKey, Door door) {
		SQLiteDatabase sqdb = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_ID, tableKey);
		contentValues.put(KEY_FLOOR, door.getFloor());
		contentValues.put(KEY_DOOR, door.getDoor());
		sqdb.insertWithOnConflict(TABLE_DOORCOORDINATES, null, contentValues,
				SQLiteDatabase.CONFLICT_IGNORE);
		Log.d("addCthDoors", " good");
		sqdb.close();
	}

	public House getHouse(int id) {
		SQLiteDatabase sqdb = this.getReadableDatabase();

		Cursor cursor = sqdb.query(TABLE_HOUSE, new String[] { KEY_ID,
				KEY_CTHPLACE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		House house = new House(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		return house;
	}

	/**
	 * A method that returns the coordinates that are connected to the String
	 * parameter
	 * 
	 * @param _idCTHplace
	 *            A String representing the building which the coordinates are
	 *            connected to.
	 * @return The coordinates that the String is connected to
	 */
	// public Coordinates getCoordinates(String _idCTHplace) {
	// SQLiteDatabase db = this.getReadableDatabase();
	//
	// Cursor cursor = db.query(TABLE_COORDINATE, new String[] { KEY_ID,
	// KEY_CTHPLACE, KEY_COORDINATES }, KEY_CTHPLACE + " = '"
	// + _idCTHplace + "'", null, null, null, null, null);
	// if (cursor != null) {
	// cursor.moveToFirst();
	// }
	//
	// Coordinates coordinates = new Coordinates(cursor.getString(1),
	// cursor.getString(2));
	//
	// db.close();
	// cursor.close();
	// return coordinates;
	// }

	public List<House> getAllHouse() {
		List<House> houseList = new ArrayList<House>();
		String allHouse = "SELECT * FROM " + TABLE_HOUSE;
		SQLiteDatabase sqdb = this.getWritableDatabase();
		Cursor cursor = sqdb.rawQuery(allHouse, null);

		cursor.moveToFirst();
		while (!cursor.isLast()) {
			House house = new House();
			house.setId(Integer.parseInt(cursor.getString(0)));
			house.setHouse(cursor.getString(1));
			houseList.add(house);
			cursor.moveToNext();
		}
		sqdb.close();
		cursor.close();
		return houseList;
	}

	public List<Door> getAllDoors() {
		List<Door> doorList = new ArrayList<Door>();
		String allDoors = "SELECT * FROM " + TABLE_DOORCOORDINATES;
		SQLiteDatabase sqdb = this.getWritableDatabase();
		Cursor cursor = sqdb.rawQuery(allDoors, null);

		cursor.moveToFirst();
		while (!cursor.isLast()) {
			Door door = new Door();
			door.SetFloor(cursor.getString(1));
			door.setDoor(cursor.getString(2));
			doorList.add(door);
			cursor.moveToNext();
		}
		sqdb.close();
		cursor.close();
		return doorList;
	}

	public List<Coordinates> getAllCoordinates() {
		List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
		String allCoordinates = "SELECT * FROM " + TABLE_COORDINATE;
		SQLiteDatabase sqdb = this.getWritableDatabase();
		Cursor cursor = sqdb.rawQuery(allCoordinates, null);

		cursor.moveToFirst();
		while (!cursor.isLast()) {
			Coordinates coordinates = new Coordinates();
			coordinates.setCoordinates(cursor.getString(1));
			coordinatesList.add(coordinates);
			cursor.moveToFirst();
		}
		sqdb.close();
		cursor.close();
		return coordinatesList;
	}
}
