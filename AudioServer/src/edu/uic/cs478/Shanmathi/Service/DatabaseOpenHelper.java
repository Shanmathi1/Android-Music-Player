package edu.uic.cs478.Shanmathi.Service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Development of Mobile Apps Project 4
 * Created By: Shanmathi Mayuram Krithivasan
 * Date : 20th April 2015
 */

// A helper class to manage database creation and version management
public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	// Creating table names and column names
	
	final static String TABLE_NAME = "Music_Player";
	final static String SONG = "Songs";
	final static String DATE = "Date";
	final static String TIME = "time";
	final static String REQUEST = "request";
	final static String STATE = "state";
	final static String _ID = "_id";
	final static String[] columns = { _ID, SONG, DATE, TIME, REQUEST, STATE };

	final private static String CREATE_CMD =

	"CREATE TABLE music_player (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ SONG + " TEXT NOT NULL," + "DATE," + "TIME," + "REQUEST," + "STATE)";

	final private static String NAME = "music_db";
	final private static Integer VERSION = 1;
	final private Context mContext;

	// Constructor for DatabaseOpenHelper
	public DatabaseOpenHelper(Context context) {
		super(context, NAME, null, VERSION);
		this.mContext = context;
	}

	// Called when the database is created for the first time
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CMD);
	}

	// Called when the database needs to be upgraded
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}

	void deleteDatabase() {
		mContext.deleteDatabase(NAME);
	}
}
