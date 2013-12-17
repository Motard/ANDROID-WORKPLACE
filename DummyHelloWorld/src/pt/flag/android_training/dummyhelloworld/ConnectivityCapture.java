package pt.flag.android_training.dummyhelloworld;

import java.text.BreakIterator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ConnectivityCapture extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false))
			Toast.makeText(context, "No connectivity", Toast.LENGTH_SHORT).show();
	}

}
