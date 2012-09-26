package se.chalmers.project14.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	private DatabaseHandler data;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "cordinatesManager";
	private static final String TABLE_CONTACTS = "coordinates";

	private static final String KEY_ID = "id";
	private static final String KEY_CTHPLACE = "CTHplace";
	private static final String KEY_COORDINATES = "coordinates";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	//Database created
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_COORDINATES_TABLE = "CREATE_TABLE " + TABLE_CONTACTS
				+ "(" + KEY_ID + " INTEGER PRIMARY KEY " + KEY_CTHPLACE
				+ " TEXT, " + KEY_COORDINATES + " TEXT" + ")";
		
		db.execSQL(CREATE_COORDINATES_TABLE);
		data.addCordinates(new Coordinates(1, "Edithuset", "57.68744,11.979491"));
		
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		onCreate(db);

	}
	//add coordinates to database
	public void addCordinates(Coordinates coordinates) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_CTHPLACE, coordinates.getCTHplace());
		contentValues.put(KEY_COORDINATES, coordinates.getCoordinates());

		db.insert(TABLE_CONTACTS, null, contentValues);
		db.close();
	}

	public Coordinates getCoordinates(String cthPlace) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_CTHPLACE, KEY_COORDINATES }, KEY_CTHPLACE + "?",
				 new String [] { cthPlace } , null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Coordinates coordinates = new Coordinates(Integer.parseInt(cursor
				.getString(0)), cursor.getString(1), cursor.getString(2));
		return coordinates;
	}
	
	
	
}
