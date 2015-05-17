package edu.uic.cs478.Shanmathi.Client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Development of Mobile Apps Project 4
 * Created By: Shanmathi Mayuram Krithivasan
 * Date : 20th April 2015
 */

/*
 * This Activity contains a ListView to display the transactions that were made
 * to the Music Player
 * The transactions were stored in SQLite Database by the service
 */
public class DataDisplay extends Activity {

	ListView listView;

	// Called when the activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the activity content from a layout resource
		setContentView(R.layout.list);
		
		// Finds a view that was identified by the list id attribute from the XML
		listView = (ListView) findViewById(R.id.list);
		
		// Returns the intent that started this activity
		Intent i = getIntent();
		
		// Retrieves the string array from the intent
		String[] array = i.getExtras().getStringArray("KEY");
		
		if (array != null) {
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_list_item_1, array);

			// Sets the string array data into the ListView
			listView.setAdapter(adapter1);
		}

	}

}