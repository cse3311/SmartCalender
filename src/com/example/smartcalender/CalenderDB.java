package com.example.smartcalender;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class CalenderDB extends SQLiteOpenHelper{

		public CalenderDB(Context applicationcontext) {
				
				// Call use the database or to create it
				
		        super(applicationcontext, "events.db", null, 1);
		        
		    }

		@Override
		public void onCreate(SQLiteDatabase database) {

			String query = "CREATE TABLE events ( eventId INTEGER PRIMARY KEY, eventName TEXT, " +
					"location TEXT, date TEXT, time TEXT)";
			
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
			
			values.put("eventName", queryValues.get("eventName"));
			values.put("location", queryValues.get("location"));
			values.put("date", queryValues.get("date"));
			values.put("time", queryValues.get("time"));
			
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
		    
		    values.put("eventName", queryValues.get("eventName"));
			values.put("location", queryValues.get("location"));
			values.put("date", queryValues.get("date"));
			values.put("time", queryValues.get("time"));
		    
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
			
			// ArrayList that contains every row in the database
			// and each row key / value stored in a HashMap
			
			ArrayList<HashMap<String, String>> eventArrayList;
			
			eventArrayList = new ArrayList<HashMap<String, String>>();
			
			String selectQuery = "SELECT  * FROM events ORDER by date";
			
			// Open a database for reading and writing
			
		    SQLiteDatabase database = this.getWritableDatabase();
		    
		    // Cursor provides read and write access for the 
		    // data returned from a database query
		    
		    // rawQuery executes the query and returns the result as a Cursor
		    
		    Cursor cursor = database.rawQuery(selectQuery, null);	
		    
		    // Move to the first row
		    
		    if (cursor.moveToFirst()) {
		        do {
		        	HashMap<String, String> contactMap = new HashMap<String, String>();
		        	
		        	// Store the key / value pairs in a HashMap
		        	// Access the Cursor data by index that is in the same order
		        	// as used when creating the table
		        	
		        	contactMap.put("eventId", cursor.getString(0));
		        	contactMap.put("eventName", cursor.getString(1));
		        	contactMap.put("location", cursor.getString(2));
		        	contactMap.put("date", cursor.getString(3));
		        	contactMap.put("time", cursor.getString(4));
		        	
		        	eventArrayList.add(contactMap);
		        } while (cursor.moveToNext()); // Move Cursor to the next row
		    }
		 
		    // return contact list
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
						
		        	eventMap.put("firstName", cursor.getString(1));
		        	eventMap.put("lastName", cursor.getString(2));
		        	eventMap.put("phoneNumber", cursor.getString(3));
		        	eventMap.put("emailAddress", cursor.getString(4));
					   
		        } while (cursor.moveToNext());
		    }				    
		return eventMap;
		}	
	
}