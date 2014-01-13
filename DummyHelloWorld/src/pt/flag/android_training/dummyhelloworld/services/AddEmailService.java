package pt.flag.android_training.dummyhelloworld.services;

import pt.flag.android_training.dummyhelloworld.providers.EmailsContract;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;


public class AddEmailService extends IntentService {

	public AddEmailService() {
		super("add_emails");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
//		Analyze intent and get the email
		
//		String email = "pabm71@gmail.com";
		String email = intent.getStringExtra("BATATE");
		
//		Insert in the BD
		
		ContentValues values = new ContentValues();
		values.put(EmailsContract.EMAIL, email);
		getContentResolver().insert(EmailsContract.CONTENT_URI,values);
		
//		notify the changes
		getContentResolver().notifyChange(EmailsContract.CONTENT_URI, null);
	}

	

}
