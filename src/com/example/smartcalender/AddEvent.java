package com.example.smartcalender;

import android.os.Bundle;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import android.widget.EditText;

public class AddEvent extends Activity {
	

	EditText text_eventname;
	EditText text_eventlocation;
	EditText datePicker1;
	EditText timePicker1;

	CalenderDB calenderdb = new CalenderDB(this);
	
	public void onCreate(Bundle savedInstanceState) {

		// Get saved data if there is any

		super.onCreate(savedInstanceState);

		// Designate that add_new_contact.xml is the interface used
		
		setContentView(R.layout.activity_add_event);

		// Initialize the EditText objects
		
		text_eventname = (EditText) findViewById(R.id.text_eventname);
		text_eventlocation = (EditText) findViewById(R.id.text_eventlocation);
		datePicker1 = (EditText) findViewById(R.id.datePicker1);
		timePicker1 = (EditText) findViewById(R.id.timePicker1);
		
	}
	

	
	public void addNewEvent(View view) {
		
		// Will hold the HashMap of values 
		
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

		// Get the values from the EditText boxes
		
		queryValuesMap.put("text_eventname", text_eventname.getText().toString());
		queryValuesMap.put("text_eventlocation", text_eventlocation.getText().toString());
		queryValuesMap.put("datePicker1", datePicker1.getText().toString());
		queryValuesMap.put("timePicker1", timePicker1.getText().toString());

		// Call for the HashMap to be added to the database
		
		calenderdb.insertEvents(queryValuesMap);
		
		// Call for MainActivity to execute
		
		this.callSmartCalender(view);
	}

	private void callSmartCalender(View view) {
		Intent theIntent = new Intent(getApplication(), SmartCalender.class);
		startActivity(theIntent);
	}	

}
