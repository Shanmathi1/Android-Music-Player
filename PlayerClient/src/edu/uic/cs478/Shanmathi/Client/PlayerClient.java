package edu.uic.cs478.Shanmathi.Client;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import edu.uic.cs478.Shanmathi.Common.MusicPlayer;

/*
 * Development of Mobile Apps Project 4
 * Created By: Shanmathi Mayuram Krithivasan
 * Date : 20th April 2015
 */

/*
 * Player Client Application exposes the functionality of Player Service
 * Includes functionality to play, pause, resume, stop and viewing the record of all requests
 */
public class PlayerClient extends Activity {

	protected static final String TAG = "MusicServiceClient";
	private MusicPlayer mMusicService;
	private boolean mIsBound;
	ListView listView;
	private int item;
	String[] recordList;
	private static final String CUSTOM_INTENT = "edu.uic.cs478.sendToast";
	
	// Description of Intent values to be matched
	private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);

	// Called when the activity is starting
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Set the activity content from a layout resource
		setContentView(R.layout.main);

		// Finds a view that was identified by the list id attribute from the XML that was processed in onCreate
		listView = (ListView) findViewById(R.id.list);

		// Register a BroadcastReceiver to be run in the main activity thread
		registerReceiver(myReceiver, intentFilter);

		// Defined Array values to show in ListView
		String[] values = new String[] { "Clip 1", "Clip 2", "Clip 3" };
		
		// BaseAdapter that is backed by an array of arbitrary objects
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);

		// Sets the data behind this ListView
		listView.setAdapter(adapter);

		// Finds a view that was identified by the play_button id attribute from the XML
		final Button PlayButton = (Button) findViewById(R.id.play_button);

		// Register a callback to be invoked when the play button view is clicked
		PlayButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {

					// Call playMusic
					if (mIsBound)
						mMusicService.playMusic(item);

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
			}
		});

		// Finds a view that was identified by the stop_button id attribute from the XML
		final Button StopButton = (Button) findViewById(R.id.stop_button);

		// Register a callback to be invoked when the stop button view is clicked
		StopButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {

					// Call stopMusic
					if (mIsBound)
						mMusicService.stopMusic();

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
			}
		});

		// Finds a view that was identified by the resume_button id attribute from the XML
		final Button ResumeButton = (Button) findViewById(R.id.resume_button);

		// Register a callback to be invoked when the resume button view is clicked
		ResumeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {

					// Call resumeMusic
					if (mIsBound)
						mMusicService.resumeMusic();

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
			}
		});

		// Finds a view that was identified by the pause_button id attribute from the XML
		final Button PauseButton = (Button) findViewById(R.id.pause_button);
		
		// Register a callback to be invoked when the pause button view is clicked
		PauseButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				try {

					// Call pauseMusic
					if (mIsBound)
						mMusicService.pauseMusic();

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}

			}
		});

		// Finds a view that was identified by the view_button id attribute from the XML
		final Button ViewButton = (Button) findViewById(R.id.view_button);
		
		// Register a callback to be invoked when the view button view is clicked
		ViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				try {

					// Call view
					if (mIsBound)
						recordList = mMusicService.view();
					
					// Create an intent for the specific component
					Intent intent = new Intent(getApplicationContext(),
							DataDisplay.class);
					
					// Add the string array with the records to the intent
					intent.putExtra("KEY", recordList);
					
					// Starts the activity
					startActivity(intent);

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}

			}
		});

		// Register a callback to be invoked when an item in this ListView has been clicked
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// Retreives the position of the item in the list view
				item = position + 1;

			}

		});
	}

	// Bind to Music Player Service
	@Override
	protected void onResume() {
		super.onResume();

		if (!mIsBound) {

			Intent intent = new Intent(MusicPlayer.class.getName());
			// Connect to the application service, creating it if needed
			bindService(intent, this.mConnection, Context.BIND_AUTO_CREATE);

		}
	}
	
	// Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed
	@Override
	protected void onPause() {

		super.onPause();
	}
	
	
	// Interface for monitoring the state of an application service
	private final ServiceConnection mConnection = new ServiceConnection() {

		// Called when a connection to the Service has been established
		public void onServiceConnected(ComponentName className, IBinder iservice) {

			mMusicService = MusicPlayer.Stub.asInterface(iservice);

			mIsBound = true;

		}
		
		// Called when a connection to the Service has been lost
		public void onServiceDisconnected(ComponentName className) {

			mMusicService = null;

			mIsBound = false;

		}
	};

	// code that will receive intents sent by sendBroadcast()
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		// Method that is called when the BroadcastReceiver is receiving an Intent broadcast
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			System.out.println("Entered onReceive");
			// Toast to show that clip has finished playing
			Toast.makeText(getApplicationContext(),
					"Current clip has finished playing !", Toast.LENGTH_LONG)
					.show();
		}
	};

	// Perform any final cleanup before an activity is destroyed
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mIsBound) {
			// Disconnect from an application service
			unbindService(this.mConnection);

		}

	}

}
