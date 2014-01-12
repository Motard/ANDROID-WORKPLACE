package com.example.ambulare.services;





import com.example.ambulare.getlocation.GetLocation;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;


public class AddLocationService extends Service {

	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		

	
		
		
		

		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		
//		Receber o Intent e os Extras
		Bundle b = intent.getExtras();
		Object obj = b.get(GetLocation.LAT);
		final String lat = obj.toString();
		
//		Criar um Handler para enviar o Toast
		Handler handler = new Handler();
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(AddLocationService.this, "SERVICE ADD LOCATION - Latitude " + lat, 5000).show();
				
			}
		});
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
