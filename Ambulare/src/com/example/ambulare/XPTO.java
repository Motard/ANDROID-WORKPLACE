package com.example.ambulare.getlocation;






import com.example.ambulare.R;
import com.example.ambulare.services.AddLocationService;
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

	private TextView _latitudeField,
	 				_longitudeField,
	 				_accurecyField,
	 				_altitudeField,
	 				_direcaoField,
	 				_velocidadeField;
	
	private LocationManager _locationManager;
	
	private String 	_provider,
					_rota;

//	Defenir constantes para enviar os valores a guardar como extra nos intents 
	public static final String 	NOME_ROTA = "rota",
								LAT = "lat",
								LNG = "lng",
								ALT = "alt";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location);
		
//		receber o nome da rota via extra do intent 
		Intent intt = getIntent();
//		Bundle b = intt.getExtras();
//		Object obj = b.get("pabm");

		 _rota= intt.getStringExtra(NOME_ROTA);
		((TextView) findViewById(R.id.nome_rota)).setText(_rota);
		
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
		
		
	
//		Inicializar os os campos de localiza��o
		if(location != null)
		{
			System.out.println("Provider " + _provider + " foi escolhido.");
			onLocationChanged(location);
			Toast.makeText(this, "Call onLocationChanged", Toast.LENGTH_SHORT).show();
		}
		else
		{
			_latitudeField.setText("Localiza��o indisponivel.");
			_longitudeField.setText("Localiza��o indisponivel");
		}
		
		
//		ver se o servi�o de GPS esta ligado
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(!enabled)
		{
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
		else
		{
			Toast.makeText(this, "GPS Ligado",Toast.LENGTH_SHORT).show();
		}	
    	
	}

//	Fazer update ao resumir
	@Override
	protected void onResume() {
		super.onResume();
//		TODO try catch para apanhar as exce��es possiveis
		_locationManager.requestLocationUpdates(_provider, 400, 1, this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		_locationManager.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {

//		aceder ao location e obter os dados da posi��o
		
		double lat =  location.getLatitude();
		double lng =  location.getLongitude();
		float accurecy = location.getAccuracy();
		double altitude = location.getAltitude();
		float direcao = location.getBearing();
		float velocidade = location.getSpeed();
		String locationInfo = location.toString(); //VAR_DUMP - Converter o conteudo do objecto numa String
		
		
//		Print Screen dos valores obtidos
		
		_latitudeField.setText(String.format("%.4f", lat)); //Formata a String para apresentar apenas 4 casas decimais.
		_longitudeField.setText(String.format("%.4f", lng));
		_accurecyField.setText(String.valueOf(accurecy));
		_altitudeField.setText(String.valueOf(altitude));
		_direcaoField.setText(String.valueOf(direcao));
		_velocidadeField.setText(String.valueOf(velocidade));
		
		Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();
		
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
	
	
}
