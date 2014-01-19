package com.example.ambulare.services;

import com.example.ambulare.AmbulareApplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GetLocationService extends Service implements LocationListener {


	private LocationManager _locationManager;
	private String 			_provider;
						

	//Defenir constantes para enviar os valores a guardar como extra nos intents 
	public static final String 	NOME_ROTA = "rota",
								ID_ROTA = "id_rota",
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
		
//		Toast.makeText(this, "GetLocationService started" + _rota, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
//		receber o nome da rota via extra do intent 
		
		String rota = intent.getStringExtra(NOME_ROTA);
		
//		Criar e enviar o intent para gravar o nome da rota na BD
		Intent intent1 = new Intent(this, AddRotaService.class);
		intent1.putExtra(NOME_ROTA, rota);
		startService(intent1);
		
//		Toast.makeText(this, "GetLocationService started " + _rota, Toast.LENGTH_SHORT).show();
		
		
				
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		
//		obter o id da rota introduzida
		long rota_id = ((AmbulareApplication) getApplication()).get_rota_id();
		
		Toast.makeText(this, "Id da rota introduzida - " + rota_id, Toast.LENGTH_SHORT).show();
		Log.d("GLS", "ID da rota introduzida " + rota_id);
		
//		aceder ao location e obter os dados da posição
		
		double lat =  location.getLatitude();
		double lng =  location.getLongitude();
		double altitude = location.getAltitude();
		
		Toast.makeText(this, "new location", Toast.LENGTH_SHORT).show();
		
//		este bloco vai arrancar o serviço que trata com a BD e envia os valores a guardar
		
		Intent intent = new Intent(this, AddLocationService.class);
		intent.putExtra(LAT, lat);
		intent.putExtra(LNG, lng);
		intent.putExtra(ALT, altitude);
		intent.putExtra(ID_ROTA, rota_id);
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
