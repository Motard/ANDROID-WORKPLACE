package com.example.ambulare.getlocation;


import com.example.ambulare.R;
import android.app.Activity;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GetLocation extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location);
		
//		Set activity background color
		((LinearLayout)findViewById(R.id.activity_get_location)).setBackgroundColor(Color.LTGRAY);
	
		findViewById(R.id.get_location_bt_getlocation).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				LocationManager locationManager;
				locationManager = (LocationManager)getSystemService(GetLocation.LOCATION_SERVICE);
				
				((TextView)findViewById(R.id.get_location_text_view_mostra_coor)).setText((CharSequence)locationManager);
				
			}
		});
	}
	
	
}
