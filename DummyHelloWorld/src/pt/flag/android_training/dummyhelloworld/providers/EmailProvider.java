package pt.flag.android_training.dummyhelloworld.providers;
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

public class EmailProvider extends ContentProvider {
	
	private SQLiteOpenHelper helper;
	
	public static final String AUTHORITY = "pt.flag.android_training.dummyhelloworld.providers.emailprovider";
	
	public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);
	
	private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int EMAIL_ID = 1;
	private static final int EMAIL_ALL = 2;
	
	private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.pt.flag.android_training.dummyhelloworld.providers." + EmailsContract.TABLE;
	private static final String MIME_ONE = "vnd.android.cursor.item/vnd.pt.flag.android_training.dummyhelloworld.providers." + EmailsContract.TABLE;


	static {
		
		URIMATCHER.addURI(AUTHORITY, EmailsContract.TABLE+"/#", EMAIL_ID);
		URIMATCHER.addURI(AUTHORITY, EmailsContract.TABLE, EMAIL_ALL);
	}
	
	@Override
	public boolean onCreate() {
		 helper = new EmailsHelper(getContext());
		 return true;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		try
		{
			return db.delete(EmailsContract.TABLE, selection, selectionArgs);
		}
		finally
		{
			db.close();
		}
	}

	@Override
	public String getType(Uri uri) {
		return URIMATCHER.match(uri) == EMAIL_ALL ? MIME_ALL : MIME_ONE;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase db = helper.getWritableDatabase();
		try
		{
			long row = db.insert(EmailsContract.TABLE, null, values);
			return (row == -1)? null:ContentUris.withAppendedId(uri, row);
		}
		finally
		{
			db.close();
			
		}
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase db = helper.getReadableDatabase();
		return db.query(EmailsContract.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
		
		SQLiteDatabase db = helper.getWritableDatabase();
		try
		{
			return db.update(EmailsContract.TABLE, values, selection, selectionArgs);
		}
		finally
		{
			db.close();
		}
	}

	private class EmailsHelper extends SQLiteOpenHelper
	{

		public EmailsHelper(Context context) 
		{
			super(context, "helloworld.db", null, 1);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			String columns = EmailsContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
							 EmailsContract.EMAIL + " TEXT NOT NULL";
			
			String sql = "CREATE TABLE IF NOT EXISTS " + EmailsContract.TABLE + " (" + columns + ")";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO When need code for database upgrade
			
		}
		
		
	}
}
