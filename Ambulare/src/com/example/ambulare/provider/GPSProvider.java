package com.example.ambulare.provider;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class GPSProvider extends ContentProvider {

	private SQLiteOpenHelper helper;
	
	public static final String AUTHORITY = "com.example.ambulare.provider.gpsprovider";
	
	
	public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);
	
	private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int ROTA_ID = 1;
	private static final int ROTA_ALL = 2;
	
	private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.com.example.ambulare.provider." + GPSContract.TABLE;
	private static final String MIME_ONE = "vnd.android.cursor.item/vnd.com.example.ambulare.provider." + GPSContract.TABLE;

	static {
		
		URIMATCHER.addURI(AUTHORITY, GPSContract.TABLE+"/#", ROTA_ID);
		URIMATCHER.addURI(AUTHORITY, GPSContract.TABLE, ROTA_ALL);
	}
	
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
		
		SQLiteDatabase db = helper.getWritableDatabase();
		try
		{
			long row = db.insert(GPSContract.TABLE, null, values);
			return (row == -1)? null:ContentUris.withAppendedId(uri, row);
		}
		finally
		{
			db.close();
			
		}
	}

	@Override
	public boolean onCreate() {
		helper = new GPSHelper(getContext());
		return true;
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
	
	
	
	private class GPSHelper extends SQLiteOpenHelper
	{

		public GPSHelper(Context context) 
		{
			super(context, "rotas.db", null, 1);
			
		}

		public void onCreate(SQLiteDatabase db) 
		{
			String columns = GPSContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
							 GPSContract.Rota + "TEXT NOT NULL"+
							 GPSContract.Alt + " TEXT NOT NULL, "+
							 GPSContract.Lat + " TEXT NOT NULL, "+
							 GPSContract.Lng + " TEXT NOT NULL";
			
			String sql = "CREATE TABLE IF NOT EXISTS " + GPSContract.TABLE + " (" + columns + ")";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO When need code for database upgrade
			
		}
	}

}
