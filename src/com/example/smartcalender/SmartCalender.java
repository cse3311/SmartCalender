package com.example.smartcalender;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class SmartCalender extends Activity {

	CalendarView cal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_calender);
		
		cal = (CalendarView) findViewById(R.id.calendarView1);
        
        cal.setOnDateChangeListener(new OnDateChangeListener() {
			
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getBaseContext(),"Selected Date is\n\n"
				+dayOfMonth+" : "+month+" : "+year , 
				Toast.LENGTH_LONG).show();
		}
	});
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
	
	public void showCurDate(View view) {
		Calendar caltoday = Calendar.getInstance();
		Toast.makeText(getBaseContext(), caltoday.getTime().toString(), Toast.LENGTH_LONG).show();
	}


}
