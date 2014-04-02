package com.example.smartcalender;

import android.os.Bundle;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class AddEvent extends Activity {
	

	EditText text_eventname;
	EditText text_eventlocation;
	EditText text_eventdate;
	EditText text_eventtime;

	CalenderDB calenderdb = new CalenderDB(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		setTitle("Add Event");
		
		text_eventname = (EditText) findViewById(R.id.text_eventname);
		text_eventlocation = (EditText) findViewById(R.id.text_eventlocation);
		text_eventdate = (EditText) findViewById(R.id.text_eventdate);
		text_eventtime = (EditText) findViewById(R.id.text_eventtime);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_calender, menu);
		return true;
	}

	
	public void addNewEvent(View view) {
		
		// Will hold the HashMap of values 
		
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

		// Get the values from the EditText boxes
		
		queryValuesMap.put("text_eventname", text_eventname.getText().toString());
		queryValuesMap.put("text_eventlocation", text_eventlocation.getText().toString());
		queryValuesMap.put("text_eventdate", text_eventdate.getText().toString());
		queryValuesMap.put("text_eventtime", text_eventtime.getText().toString());

		// Call for the HashMap to be added to the database
		
		calenderdb.insertEvents(queryValuesMap);
		
		Toast.makeText(getApplicationContext(), "Event Added Succesfully", Toast.LENGTH_SHORT).show();
		
		// Call for MainActivity to execute
		
		this.callSmartCalender(view);
	}

	private void callSmartCalender(View view) {
		Intent theIntent = new Intent(getApplication(), ListEvent.class);
		startActivity(theIntent);
		
	}	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        // Handle action buttons
        switch(item.getItemId()) {
        
        	case R.id.action_new_event:
	        	Intent intent1 = new Intent( getBaseContext() , AddEvent.class);
				startActivity(intent1);
	        	break;
        	case R.id.action_home:
        		Intent intent2 = new Intent( getBaseContext() , SmartCalender.class);
				startActivity(intent2);
	        	break;

        }
        //default always happens
        return super.onOptionsItemSelected(item);
    }

}
