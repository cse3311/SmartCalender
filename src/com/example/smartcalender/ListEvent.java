package com.example.smartcalender;

import java.util.ArrayList;

import com.example.smartcalender.AddEvent;
import com.example.smartcalender.CalenderDB;
import java.util.HashMap;

import android.os.Bundle;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListEvent extends ListActivity {

	
	Intent intent;
	TextView eventId;
	
	
	CalenderDB calenderdb = new CalenderDB(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_event);
		setTitle("List Event");
		
		ArrayList<HashMap<String, String>> eventList =  calenderdb.getAllEvents();
		
		if(eventList.size()!=0) {
			
			
			ListView listView = getListView();
			Log.d("eventList", "Value: " + eventList.get(eventList.size()-1));
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					
					eventId = (TextView) view.findViewById(R.id.eventID);
					
					
					String eventIdValue = eventId.getText().toString();	
					
				
					Intent  theIndent = new Intent(getApplication(),EditEvent.class);
					
					
					theIndent.putExtra("eventId", eventIdValue); 
					
					
					startActivity(theIndent); 
				}
			}); 
			
		
			
			ListAdapter adapter = new SimpleAdapter( ListEvent.this,eventList, R.layout.activity_add_event, new String[] { "eventId","text_eventname", "text_eventdate"}, new int[] {R.id.eventID, R.id.text_eventname, R.id.text_eventdate});
			
			setListAdapter(adapter);
		}
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_calender, menu);
		return true;
	}
	
	public void showAddEvent(View view) {
		Intent theIntent = new Intent(getApplicationContext(), AddEvent.class);
		startActivity(theIntent);
	}
}
