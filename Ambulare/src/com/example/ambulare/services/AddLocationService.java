package com.example.ambulare.services;







import com.example.ambulare.getlocation.GetLocation;
import com.example.ambulare.provider.GPSContract;

import android.app.Service;
import android.content.ContentValues;
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
		
		Bundle bundle = intent.getExtras();
		
		Object obj = bundle.get(GetLocation.LAT);
		final String lat = bundle.get(GetLocation.LAT).toString();
		
		obj = bundle.get(GetLocation.LNG);
		final String lng = obj.toString();
		
		obj = bundle.get(GetLocation.ALT);
		final String alt = obj.toString();
		
		obj = bundle.get(GetLocation.NOME_ROTA);
		final String rota = obj.toString();
		

//		Insert in the BD
		
		ContentValues values = new ContentValues();
		values.put(GPSContract.Rota, rota);
		values.put(GPSContract.Lat, lat);
		values.put(GPSContract.Lng, lng);
		values.put(GPSContract.Alt, alt);
		getContentResolver().insert(GPSContract.CONTENT_URI,values);
		
		
//		Criar um Handler para enviar o Toast
		
		Handler handler = new Handler();
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(AddLocationService.this, "SERVICE ADD LOCATION" +
														" * Latitude " + lat + 
														" * Longitude " + lng + 
														" * Altitude " + alt + 
														" * Rota " + rota, Toast.LENGTH_LONG).show();
				
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
