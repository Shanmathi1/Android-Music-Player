package edu.uic.cs478.Shanmathi.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import edu.uic.cs478.Shanmathi.Common.MusicPlayer;

/*
 * Development of Mobile Apps Project 4
 * Created By: Shanmathi Mayuram Krithivasan
 * Date : 20th April 2015
 */

/* 
 * Music Player Service which exposes an API for clients to use
 * API supports functionality for play, pause, resume and stop the clips
 * Additionally this Application maintains an SQLite database to keep track of all the requests received by the App
 * Broadcasts an implicit intent when a clip finishes playing
 */

public class PlayerService extends Service {

	// MediaPlayer class used to control playback of audio/video files and
	// streams
	private MediaPlayer mPlayer;

	// Length of the current clip
	private int length = 0;

	// Keeps track of the clip that is playing
	private long playing = 0;

	// Create a new DatabaseHelper
	private DatabaseOpenHelper mDbHelper;

	// Keeps track of the current state of the player
	private String state;

	// Called by the system when the service is first created
	@Override
	public void onCreate() {
		super.onCreate();

		// Set up the Media Player
		mPlayer = MediaPlayer.create(this, R.raw.clip2);

		// Create a new DatabaseHelper
		mDbHelper = new DatabaseOpenHelper(this);

		// start with an empty database
		if (mDbHelper != null) {
			mDbHelper.getWritableDatabase().close();
			mDbHelper.deleteDatabase();
		}
	}

	// Method to start playing a clip

	void playAudio(long n) {

		// Check which clip has been selected by the client
		if (n == 1) {
			playing = n;
			// If music player is already playing, stop the current clip and
			// start this clip
			if (mPlayer.isPlaying()) {
				stopPlaying();
				mPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.clip1);
				// Starts playback
				
				mPlayer.start();
			} else {
				mPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.clip1);
				// Starts playback
				mPlayer.start();
			}

		}
		if (n == 2) {
			playing = n;
			// If music player is already playing, stop the current clip and
			// start this clip
			if (mPlayer.isPlaying()) {
				// Stop the current clip
				stopPlaying();
				mPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.clip2);
				// Starts playback
				mPlayer.start();
			} else {

				mPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.clip2);
				// Starts playback
				mPlayer.start();
			}

		}
		if (n == 3) {

			playing = n;
			// If music player is already playing, stop the current clip and
			// start this clip
			if (mPlayer.isPlaying()) {
				// Stop the current clip
				stopPlaying();
				mPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.clip3);
				// Starts playback
				mPlayer.start();
			} else {
				mPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.clip3);
				// Starts playback
				mPlayer.start();
			}

		}
		if (null != mPlayer) {

			mPlayer.setLooping(false);

			// Register a callback to be invoked when the end of a media source
			// has been reached during playback
			mPlayer.setOnCompletionListener(new OnCompletionListener() {

				// Called when the end of a media source is reached during
				// playback
				@Override
				public void onCompletion(MediaPlayer mp) {

					System.out.println("Entered completion!");

					// broadcasts an implicit intent when a clip finishes
					// playing
					Intent intent = new Intent();
					// Set the general action to be performed
					intent.setAction("edu.uic.cs478.sendToast");
					// Broadcast the given intent to all interested
					// BroadcastReceivers
					sendBroadcast(intent);

				}
			});
		}

	}

	// Implement the Stub for this Object
	private final MusicPlayer.Stub mBinder = new MusicPlayer.Stub() {

		// Plays the clip selected by the client in the music player
		public void playMusic(long n) {

			// Calling playAudio method passing the clip selected by the client
			playAudio(n);

			// Insert this transaction in the SQLite Database
			insertDB(n, "Play");

			// Changing the current state of the music player
			state = "Playing Clip " + n;
		}

		// Pauses the clip selected by the client in the music player
		public void pauseMusic() {
			if (null != mPlayer) {

				// Pauses playback
				mPlayer.pause();

				// Length of clip played when pause pressed
				length = mPlayer.getCurrentPosition();

			}

			// Insert this transaction in the SQLite Database
			insertDB(playing, "Pause");

			// Changing the current state of the music player
			state = "Paused Clip " + playing;

		}

		// Resumes the clip selected by the client in the music player
		public void resumeMusic() {
			if (null != mPlayer) {
				// Seeks to specified time position
				mPlayer.seekTo(length);

				// Starts or resumes playback
				mPlayer.start();

			}

			// Insert this transaction in the SQLite Database
			insertDB(playing, "Resume");

			// Changing the current state of the music player
			state = "Resume Clip " + playing;

		}

		// Stops the clip selected by the client in the music player
		public void stopMusic() {
			if (null != mPlayer) {
				// Stops playback after playback has been stopped or paused
				mPlayer.stop();

			}

			// Insert this transaction in the SQLite Database
			insertDB(playing, "Stop");

			// Changing the current state of the music player
			state = "Stopped Clip " + playing;
		}

		// Return the transactions stored in SQLite Database when requested by
		// the client
		public String[] view() {

			int i = 1;

			// Read Data from database and store in the cursor
			Cursor c = readData();

			// Storing the transactions in a String Array
			String[] dataList = new String[100];
			String[] data;

			if (c != null) {

				// Loop till the cursor reaches the last row
				while (c.moveToNext()) {

					// Assign each column of the cursor to the String
					data = new String[6];
					if (c.getString(0) != null)
						data[0] = c.getString(0);
					else
						data[0] = "null";

					if (c.getString(1) != null)
						data[1] = c.getString(1);
					else
						data[1] = "null";

					if (c.getString(2) != null)
						data[2] = c.getString(2);
					else
						data[2] = "null";

					if (c.getString(3) != null)
						data[3] = c.getString(3);
					else
						data[3] = "null";

					if (c.getString(4) != null)
						data[4] = c.getString(4);
					else
						data[4] = "null";

					if (c.getString(5) != null)
						data[5] = c.getString(5);
					else
						data[5] = "null";

					dataList[i] = "Id : " + data[0]
							+ ",\nDate : " + data[2] + ",\t Time : " + data[3]
							+ ",\nRequest : " + data[4] + " Clip " + data[1]
							+ ",\nPrevious State : " + data[5];
					i++;
				}

				// Closes the Cursor, releasing all of its resources and making
				// it completely invalid
				c.close();
			}

			// Checking if the strings are not null
			List<String> list = new ArrayList<String>();

			for (String s : dataList) {
				if (s != null && s.length() > 0) {
					list.add(s);
				}
			}

			dataList = list.toArray(new String[list.size()]);

			// Returning the String containing the transactions stored in the
			// database
			return dataList;

		}

	};

	// Method for stopping the current song played by the song
	private void stopPlaying() {
		if (mPlayer != null) {

			// Stops playback after playback has been stopped or paused.
			mPlayer.stop();

			// Releases resources associated with this MediaPlayer object
			mPlayer.release();
			mPlayer = null;
		}
	}

	// Insert each transaction in the SQLite database
	private void insertDB(long n, String req) {

		// Storing the date and time corresponding to each request
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
		String date = formatter.format(today);
		String time = formatter1.format(today);

		// Class used to store a set of values
		ContentValues values = new ContentValues();

		// Adds each value to the set
		values.put(DatabaseOpenHelper.SONG, n);
		values.put(DatabaseOpenHelper.DATE, date);
		values.put(DatabaseOpenHelper.TIME, time);
		values.put(DatabaseOpenHelper.REQUEST, req);
		values.put(DatabaseOpenHelper.STATE, state);

		// Method for inserting a row into the database
		mDbHelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME,
				null, values);

		// Removes all values
		values.clear();

	}

	// Returns all clip records in the database
	private Cursor readData() {

		// Query the given table, returning a Cursor over the result set
		return mDbHelper.getWritableDatabase().query(
				DatabaseOpenHelper.TABLE_NAME, DatabaseOpenHelper.columns,
				null, new String[] {}, null, null, null);
	}

	// Return the Stub defined above
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	// Close database
	@Override
	public void onDestroy() {

		// Releases a reference to the object, closing the object if the last
		// reference was released
		mDbHelper.getWritableDatabase().close();
		mDbHelper.deleteDatabase();

		// Stops playback after playback has been stopped or paused.
		mPlayer.stop();

		// Releases resources associated with this MediaPlayer object
		mPlayer.release();
		mPlayer = null;

		super.onDestroy();

	}

}
