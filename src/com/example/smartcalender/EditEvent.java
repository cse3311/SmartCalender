package com.example.smartcalender;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class EditEvent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		setTitle("Edit Event");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_calender, menu);
		return true;
	}
}
