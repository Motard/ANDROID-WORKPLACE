package pt.flag.android_training.dummyhelloworld.services;

import pt.flag.android_training.dummyhelloworld.providers.EmailsContract;
import android.app.IntentService;
import android.content.Intent;

public class RemoveEmailService extends IntentService {

	public RemoveEmailService() {
		super("removeEmail");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		int id = intent.getIntExtra("POS", 0);
		String[] args = new String[]{id+""};
		getContentResolver().delete(EmailsContract.CONTENT_URI, EmailsContract._ID + "=?", args);
		getContentResolver().notifyChange(EmailsContract.CONTENT_URI, null);
	}

}
