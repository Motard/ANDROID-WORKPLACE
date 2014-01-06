package com.example.ambulare.provider;

import pt.flag.android_training.dummyhelloworld.providers.EmailsContract;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class GPSProvider extends ContentProvider {

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void onCreate(SQLiteDatabase db) 
	{
		String columns = EmailsContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
						 EmailsContract.EMAIL + " TEXT NOT NULL";
		
		String sql = "CREATE TABLE IF NOT EXISTS " + EmailsContract.TABLE + " (" + columns + ")";
		db.execSQL(sql);
	}

}
