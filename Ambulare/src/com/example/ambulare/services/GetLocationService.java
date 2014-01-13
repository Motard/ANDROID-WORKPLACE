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
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class GetLocationService extends Service implements LocationListener {


	private LocationManager _locationManager;

	private String 	_provider,
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
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
//		receber o nome da rota via extra do intent 
		
		_rota = intent.getStringExtra(NOME_ROTA);
		
		Location location = _locationManager.getLastKnownLocation(_provider);

		

////		ver se o serviço de GPS esta ligado
//		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
//		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		
//		if(!enabled)
//		{
//			Intent gps_settings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			startActivity(gps_settings);
//		}
//		else
//		{
//			Toast.makeText(this, "GPS Ligado",Toast.LENGTH_SHORT).show();
//		}	
//		
		return super.onStartCommand(intent, flags, startId);
	
	}
	
	@Override
	public void onLocationChanged(Location location) {
//		aceder ao location e obter os dados da posição
		
		double lat =  location.getLatitude();
		double lng =  location.getLongitude();
		float accurecy = location.getAccuracy();
		double altitude = location.getAltitude();
		float direcao = location.getBearing();
		float velocidade = location.getSpeed();
		String locationInfo = location.toString(); //VAR_DUMP - Converter o conteudo do objecto numa String
		
		
////		Print Screen dos valores obtidos
//		
//		_latitudeField.setText(String.format("%.4f", lat)); //Formata a String para apresentar apenas 4 casas decimais.
//		_longitudeField.setText(String.format("%.4f", lng));
//		_accurecyField.setText(String.valueOf(accurecy));
//		_altitudeField.setText(String.valueOf(altitude));
//		_direcaoField.setText(String.valueOf(direcao));
//		_velocidadeField.setText(String.valueOf(velocidade));
		
		Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();
		
//		este bloco vai arrancar o serviço que trata com a BD e envia os valores a guardar
		
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
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
