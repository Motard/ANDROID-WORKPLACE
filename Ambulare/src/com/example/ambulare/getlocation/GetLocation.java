package com.example.ambulare.getlocation;


import java.text.Format;

import com.example.ambulare.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GetLocation extends Activity implements LocationListener{

	private TextView _latitudeField;
	private TextView _longitudeField;
	private TextView _accurecyField;
	private TextView _altitudeField;
	private TextView _direcaoField;
	private TextView _velocidadeField;
	private LocationManager _locationManager;
	private String _provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location);
		
		_latitudeField = (TextView) findViewById(R.id.activity_get_location_TV2);
		_longitudeField = (TextView) findViewById(R.id.activity_get_location_TV4);
		_accurecyField = (TextView) findViewById(R.id.tv_activity_get_location_accuracy_value);
		_altitudeField = (TextView) findViewById(R.id.tv_activity_get_location_altitude_value);
		_direcaoField = (TextView) findViewById(R.id.tv_activity_get_location_direcao_value);
		_velocidadeField = (TextView) findViewById(R.id.tv_activity_get_location_velocidade_value);
		
//		Defenir o backgroundColor dos layouts
		((LinearLayout)findViewById(R.id.activity_get_location)).setBackgroundColor(Color.LTGRAY);
		((LinearLayout) findViewById(R.id.activity_get_location_ll1)).setBackgroundColor(Color.GREEN);
		((LinearLayout) findViewById(R.id.activity_get_location_ll2)).setBackgroundColor(Color.RED);
		((LinearLayout) findViewById(R.id.activity_get_location_ll3)).setBackgroundColor(Color.YELLOW);
	
		
//		Aceder ao LocationManager
		_locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
//		Defenir o criterio para escolher o location provider -> use default
		Criteria criteria = new Criteria();
		_provider = _locationManager.getBestProvider(criteria, true);
		Location location = _locationManager.getLastKnownLocation(_provider);
	
//		Inicializar os os campos de localização
		if(location != null)
		{
			System.out.println("Provider " + _provider + " foi escolhido.");
			onLocationChanged(location);
			Toast.makeText(this, "Call onLocationChanged", Toast.LENGTH_SHORT).show();
		}
		else
		{
			_latitudeField.setText("Localização indisponivel.");
			_longitudeField.setText("Localização indisponivel");
		}
		
		
//		ver se o serviço de GPS esta ligado
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(!enabled)
		{
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
//		else
//		{
//			Toast.makeText(this, "GPS Ligado",Toast.LENGTH_SHORT).show();
//		}
	}

//	Fazer update ao resumir
	@Override
	protected void onResume() {
		super.onResume();
//		TODO try catch para apanhar as exceções possiveis
		_locationManager.requestLocationUpdates(_provider, 1000, 1, this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		_locationManager.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		double lat =  location.getLatitude();
		double lng =  location.getLongitude();
		float accurecy = location.getAccuracy();
		double altitude = location.getAltitude();
		float direcao = location.getBearing();
		float velocidade = location.getSpeed();
		String locationInfo = location.toString();
		
		_latitudeField.setText(String.format("%.4f", lat));
		_longitudeField.setText(String.format("%.4f", lng));
		_accurecyField.setText(String.valueOf(accurecy));
		_altitudeField.setText(String.valueOf(altitude));
		_direcaoField.setText(String.valueOf(direcao));
		_velocidadeField.setText(String.valueOf(locationInfo));
		
		Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();
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
	
	
}
