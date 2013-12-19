package pt.flag.android_training.dummyhelloworld.providers;

import android.net.Uri;
import android.provider.BaseColumns;

public class EmailsContract 
{

//	table name
	public static final String TABLE = "EMAILS"; // constant
	
//	colunm names
	public static final String _ID = BaseColumns._ID,
						EMAIL = "email";
	
	public static Uri CONTENT_URI = Uri.withAppendedPath(EmailProvider.CONTENT_URI, TABLE);
	
}
