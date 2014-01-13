package com.example.ambulare.services;

import com.example.ambulare.getlocation.GetLocation;
import com.example.ambulare.provider.GPSContract;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class AddLocationService extends IntentService {

	private Handler _handler;
	
	public AddLocationService()
	{
		super("rota");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int res =  super.onStartCommand(intent, flags, startId);
		
		_handler = new Handler();
		
		return res;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
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
			
		_handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(AddLocationService.this, "SERVICE ADD LOCATION" +
														" * Latitude " + lat + 
														" * Longitude " + lng + 
														" * Altitude " + alt + 
														" * Rota " + rota, Toast.LENGTH_LONG).show();
			}
		});
		
		Log.d("ROTAS", "SERVICE ADD LOCATION" +" * Latitude " + lat + 
											   " * Longitude " + lng + 
											   " * Altitude " + alt + 
											   " * Rota " + rota);
	}
}
