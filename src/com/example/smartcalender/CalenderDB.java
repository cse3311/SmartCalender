package com.example.smartcalender;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class CalenderDB extends SQLiteOpenHelper{

		public CalenderDB(Context applicationcontext) {
				
				// Call use the database or to create it
				
		        super(applicationcontext, "events.db", null, 1);
		        
		    }

		@Override
		public void onCreate(SQLiteDatabase database) {

			String query = "CREATE TABLE events ( eventId INTEGER PRIMARY KEY, eventName TEXT, " +
					"location TEXT, date DATE, time TIME)";
			
			 database.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
			String query = "DROP TABLE IF EXISTS events";
			
			
			database.execSQL(query);
	        onCreate(database);
			
		}
		
		public void insertEvents(HashMap<String, String> queryValues) {
			
			// Open a database for reading and writing
			
			SQLiteDatabase database = this.getWritableDatabase();
			
			// Stores key value pairs being the column name and the data
			// ContentValues data type is needed because the database
			// requires its data type to be passed
			
			ContentValues values = new ContentValues();
			
			
			values.put("eventName", queryValues.get("text_eventname"));
			values.put("location", queryValues.get("text_eventlocation"));
			values.put("date", queryValues.get("text_eventdate"));
			values.put("time", queryValues.get("text_eventtime"));
			
			
			    
			// Inserts the data in the form of ContentValues into the
			// table name provided
			
			database.insert("events", null, values);
			
			// Release the reference to the SQLiteDatabase object
			
			
			database.close();
		}
		
		

		public int updateEvents(HashMap<String, String> queryValues) {
			
			// Open a database for reading and writing
			
			SQLiteDatabase database = this.getWritableDatabase();	
			
			// Stores key value pairs being the column name and the data
			
		    ContentValues values = new ContentValues();
		    
		    values.put("eventName", queryValues.get("text_eventname"));
			values.put("location", queryValues.get("text_eventlocation"));
			values.put("date", queryValues.get("text_eventdate"));
			values.put("time", queryValues.get("text_eventtime"));
		    
			// update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)
			
		    return database.update("events", values, "eventId" + " = ?", new String[] { queryValues.get("eventId") });
		}
		
		
		public void deleteEvent(String id) {

			// Open a database for reading and writing
			
			SQLiteDatabase database = this.getWritableDatabase();
			
			String deleteQuery = "DELETE FROM  events where eventId='"+ id +"'";
			
			// Executes the query provided as long as the query isn't a select
			// or if the query doesn't return any data
			
			database.execSQL(deleteQuery);
		}
		
		
		public ArrayList<HashMap<String, String>> getAllEvents() {
			
		
			ArrayList<HashMap<String, String>> eventArrayList;
			
			eventArrayList = new ArrayList<HashMap<String, String>>();
			
			String selectQuery = "SELECT  * FROM events";
			
		
		    SQLiteDatabase database = this.getWritableDatabase();
		   
		    Cursor cursor = database.rawQuery(selectQuery, null);	
		
		    if (cursor.moveToFirst()) {
		        do {
		        	HashMap<String, String> eventMap = new HashMap<String, String>();
		        	
		        	eventMap.put("eventId", cursor.getString(0));
		        	eventMap.put("eventName", cursor.getString(1));
		        	eventMap.put("location", cursor.getString(2));
		        	eventMap.put("date", cursor.getString(3));
		        	eventMap.put("time", cursor.getString(4));
		        	
		        	eventArrayList.add(eventMap);
		        } while (cursor.moveToNext()); // Move Cursor to the next row
		    }
		 

		    return eventArrayList;
		}
		
		public HashMap<String, String> getEventInfo(String id) {
			HashMap<String, String> eventMap = new HashMap<String, String>();
			
			// Open a database for reading only
			
			SQLiteDatabase database = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM events where eventId='"+id+"'";
			
			// rawQuery executes the query and returns the result as a Cursor
			
			Cursor cursor = database.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
		        do {
						
		        	eventMap.put("eventName", cursor.getString(1));
		        	eventMap.put("location", cursor.getString(2));
		        	eventMap.put("date", cursor.getString(3));
		        	eventMap.put("time", cursor.getString(4));
					   
		        } while (cursor.moveToNext());
		    }				    
		return eventMap;
		}	
	
}