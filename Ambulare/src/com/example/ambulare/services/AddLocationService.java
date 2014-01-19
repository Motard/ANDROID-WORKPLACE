package com.example.ambulare.services;


import com.example.ambulare.provider.GPSContract;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


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
		
		String lat = bundle.get(GetLocationService.LAT).toString();
		String lng = bundle.get(GetLocationService.LNG).toString();
		String alt = bundle.get(GetLocationService.ALT).toString();
		long rota_id = bundle.getLong(GetLocationService.ID_ROTA);
		
//		Insert in the BD
		
		ContentValues values = new ContentValues();
		values.put(GPSContract.ROTA_ID, rota_id);
		values.put(GPSContract.LAT, lat);
		values.put(GPSContract.LNG, lng);
		values.put(GPSContract.ALT, alt);
		getContentResolver().insert(GPSContract.CONTENT_URI_COORDENADAS,values);
			
//		_handler.post(new Runnable() {
//			@Override
//			public void run() {
//				Toast.makeText(AddLocationService.this, "SERVICE ADD LOCATION" +
//														" * Latitude " + lat + 
//														" * Longitude " + lng + 
//														" * Altitude " + alt + 
//														" * Rota " + rota, Toast.LENGTH_LONG).show();
//			}
//		});
		
		Log.d("ROTAS", "SERVICE ADD LOCATION" +" * Latitude " + lat + 
											   " * Longitude " + lng + 
											   " * Altitude " + alt + 
											   " * Rota " + rota_id);
	}
}
