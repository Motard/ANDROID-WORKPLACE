package com.example.ambulare.getlocation;






import com.example.ambulare.R;
import com.example.ambulare.services.AddLocationService;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GetLocation extends Service implements LocationListener{

	
	private TextView _latitudeField,
		_longitudeField,
		_accurecyField,
		_altitudeField,
		_direcaoField,
		_velocidadeField;

	
	
//	@Override
//	protected void onCreate() 
//	{
//		
//		super.onCreate();
//		setContentView(R.layout.activity_get_location);
		

//		((TextView) findViewById(R.id.nome_rota)).setText(_rota);
//		
//		_latitudeField = (TextView) findViewById(R.id.activity_get_location_TV2);
//		_longitudeField = (TextView) findViewById(R.id.activity_get_location_TV4);
//		_accurecyField = (TextView) findViewById(R.id.tv_activity_get_location_accuracy_value);
//		_altitudeField = (TextView) findViewById(R.id.tv_activity_get_location_altitude_value);
//		_direcaoField = (TextView) findViewById(R.id.tv_activity_get_location_direcao_value);
//		_velocidadeField = (TextView) findViewById(R.id.tv_activity_get_location_velocidade_value);
		
////		Defenir o backgroundColor dos layouts
//		((LinearLayout)findViewById(R.id.activity_get_location)).setBackgroundColor(Color.LTGRAY);
//		((LinearLayout) findViewById(R.id.activity_get_location_ll1)).setBackgroundColor(Color.GREEN);
//		((LinearLayout) findViewById(R.id.activity_get_location_ll2)).setBackgroundColor(Color.RED);
//		((LinearLayout) findViewById(R.id.activity_get_location_ll3)).setBackgroundColor(Color.YELLOW);
	
		

		
//		findViewById(R.id.get_location_bt_getlocation).setOnClickListener(new View.OnClickListener() 
//		{
//			
//			@Override
//			public void onClick(View v) 
//			{	
//				Toast.makeText(GetLocation.this, "NEW GPS", Toast.LENGTH_SHORT).show();
//				_locationManager.requestSingleUpdate(_provider, GetLocation.this, null);
//
//			}
//		});
		
		
	

		
		

    	
//	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		

	}
	
	
//	Fazer update ao resumir
	protected void onResume() {
		super.onResume();
//		TODO try catch para apanhar as exceções possiveis
		
	}
	
	protected void onPause() {
		super.onPause();
		_locationManager.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {


	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
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
