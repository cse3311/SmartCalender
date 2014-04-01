package com.example.smartcalender;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditEvent extends Activity{
	
	EditText text_eventname;
	EditText text_eventlocation;
	EditText text_eventdate;
	EditText text_eventtime;
	
	CalenderDB calenderdb = new CalenderDB(this);
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);
		text_eventname = (EditText) findViewById(R.id.text_eventname);
		text_eventlocation = (EditText) findViewById(R.id.text_eventlocation);
		text_eventdate = (EditText) findViewById(R.id.text_eventdate);
		text_eventtime = (EditText) findViewById(R.id.text_eventtime);
		
		Intent theIntent = getIntent();
		
		String eventId = theIntent.getStringExtra("eventId");
		
		HashMap<String, String> eventList = calenderdb.getEventInfo(eventId);
		
		if(eventList.size() != 0){
			
			text_eventname.setText(eventList.get("eventName"));
			text_eventlocation.setText(eventList.get("location"));
			text_eventdate.setText(eventList.get("date"));
			text_eventtime.setText(eventList.get("time"));
			
			
		}
	}
	
	public void editEvent(View view){
		
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		text_eventname = (EditText) findViewById(R.id.text_eventname);
		text_eventlocation = (EditText) findViewById(R.id.text_eventlocation);
		text_eventdate = (EditText) findViewById(R.id.text_eventdate);
		text_eventtime = (EditText) findViewById(R.id.text_eventtime);
		
		Intent theIntent = getIntent();
		
		String eventId = theIntent.getStringExtra("eventId");
		
		queryValuesMap.put("eventId", eventId);
		queryValuesMap.put("text_eventname", text_eventname.getText().toString());
		queryValuesMap.put("text_eventlocation", text_eventlocation.getText().toString());
		queryValuesMap.put("text_eventdate", text_eventdate.getText().toString());
		queryValuesMap.put("text_eventtime", text_eventtime.getText().toString());
		
		calenderdb.updateEvents(queryValuesMap);
		
		Toast.makeText(getApplicationContext(), "Event Edited Succesfully", Toast.LENGTH_SHORT).show();
		this.callMainActivity(view);
		
	}
	
	public void deleteEvent(View view){
		
		Intent theIntent = getIntent();
		
		String eventId = theIntent.getStringExtra("eventId");
		
		calenderdb.deleteEvent(eventId);
		
		Toast.makeText(getApplicationContext(), "Event Deleted Succesfully", Toast.LENGTH_SHORT).show();
		
		this.callMainActivity(view);
		
	}
	
	public void callMainActivity(View view){
		
		Intent objIntent = new Intent(getApplication(), ListEvent.class);
		
		startActivity(objIntent);
		
	}
	
	public void showAddEvent(View view) {
		Intent theIntent = new Intent(getApplicationContext(), EditEvent.class);
		startActivity(theIntent);
	}
	

	
}







