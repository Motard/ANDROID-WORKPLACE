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
import android.util.Log;

public class GPSProvider extends ContentProvider {

	private SQLiteOpenHelper helper;
	
	public static final String AUTHORITY = "com.example.ambulare.provider.gpsprovider";
	
	
	public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);
	
	private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int ROTA_ID = 1;
	private static final int ROTA_ALL = 2;
	private static final int COORDENADAS_ID = 3;
	private static final int COORDENADAS_ALL = 4;
	
	private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.com.example.ambulare.provider." + GPSContract.TABLE_ROTAS;
	private static final String MIME_ONE = "vnd.android.cursor.item/vnd.com.example.ambulare.provider." + GPSContract.TABLE_ROTAS;

	static {
		
		URIMATCHER.addURI(AUTHORITY, GPSContract.TABLE_ROTAS+"/#", ROTA_ID);
		URIMATCHER.addURI(AUTHORITY, GPSContract.TABLE_ROTAS, ROTA_ALL);
		URIMATCHER.addURI(AUTHORITY, GPSContract.TABLE_COORDENADAS+"/#", COORDENADAS_ID);
		URIMATCHER.addURI(AUTHORITY, GPSContract.TABLE_COORDENADAS, COORDENADAS_ALL);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return URIMATCHER.match(uri) == ROTA_ALL ? MIME_ALL : MIME_ONE;
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		int uri_type = URIMATCHER.match(uri);
		
		Log.d("uri_type", " uri type = " + uri_type);
		
		if (uri_type == 1 || uri_type == 2)
		{
			try
			{
				long row = db.insert(GPSContract.TABLE_ROTAS, null, values);
				return (row == -1)? null:ContentUris.withAppendedId(uri, row);
			}
			finally
			{
				db.close();
				
			}
		}
		else
		{
			try
			{
				long row = db.insert(GPSContract.TABLE_COORDENADAS, null, values);
				return (row == -1)? null:ContentUris.withAppendedId(uri, row);
			}
			finally
			{
				db.close();
				
			}
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
		SQLiteDatabase db = helper.getReadableDatabase();
		
		return db.query(GPSContract.TABLE_ROTAS, projection, selection, selectionArgs, GPSContract.NOME_ROTA, null,  GPSContract.NOME_ROTA);		
		
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
			super(context, "ambulare.db", null, 1);
			
		}

		public void onCreate(SQLiteDatabase db) 
		{
			String columns = GPSContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
							 GPSContract.ROTA_ID + " LONG NOT NULL, "+
							 GPSContract.ALT + " TEXT NOT NULL, "+
							 GPSContract.LAT + " TEXT NOT NULL, "+
							 GPSContract.LNG + " TEXT NOT NULL";
			
			String sql = "CREATE TABLE IF NOT EXISTS " + GPSContract.TABLE_COORDENADAS + " (" + columns + ")";
			Log.d("tabela", sql);
			db.execSQL(sql);
			
			columns = 	GPSContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
								GPSContract.NOME_ROTA + " TEXT NOT NULL";
			
			sql = "CREATE TABLE IF NOT EXISTS " + GPSContract.TABLE_ROTAS + " (" + columns + ")";
			
			Log.d("tabela", sql);
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO When need code for database upgrade
			
		}
	}

}
