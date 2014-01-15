package com.example.ambulare.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class GetLocationService extends Service implements LocationListener {


	private LocationManager _locationManager;
	private String 			_provider,
							_rota;

//Defenir constantes para enviar os valores a guardar como extra nos intents 
	public static final String 	NOME_ROTA = "rota",
								LAT = "lat",
								LNG = "lng",
								ALT = "alt";
	

	@Override
	public void onCreate() {
		
		super.onCreate();
		
//		Aceder ao LocationManager
		_locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
//		Defenir o criterio para escolher o location provider -> use default
		Criteria criteria = new Criteria();
		_provider = _locationManager.getBestProvider(criteria, true);
		_locationManager.requestLocationUpdates(_provider, 400, 1, this);
		
		Toast.makeText(this, "GetLocationService started", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
//		receber o nome da rota via extra do intent 
		
		_rota = intent.getStringExtra(NOME_ROTA);
				
		return super.onStartCommand(intent, flags, startId);
	
	}
	
	@Override
	public void onLocationChanged(Location location) {
//		aceder ao location e obter os dados da posi��o
		
		double lat =  location.getLatitude();
		double lng =  location.getLongitude();
		double altitude = location.getAltitude();
		
		Toast.makeText(this, "new location", Toast.LENGTH_SHORT).show();
		
//		este bloco vai arrancar o servi�o que trata com a BD e envia os valores a guardar
		
		Intent intent = new Intent(this, AddLocationService.class);
		intent.putExtra(LAT, lat);
		intent.putExtra(LNG, lng);
		intent.putExtra(ALT, altitude);
		intent.putExtra(NOME_ROTA, _rota);
		startService(intent);

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Provider desligado - " + provider, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Novo provider disponivel - " + provider, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void onDestroy() 
	{

		_locationManager.removeUpdates(this);
		
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) 
	{
		
		return null;
	}
	
	
}
