package com.example.smartcalender;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SmartCalender extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_calender);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_calender, menu);
		return true;
	}
	
	public void showAddEvent(View view) {
		Intent theIntent = new Intent(this, AddEvent.class);
		startActivity(theIntent);
	}

}
