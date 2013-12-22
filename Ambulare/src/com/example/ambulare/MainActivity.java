package com.example.ambulare;

import com.example.ambulare.getlocation.GetLocation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		RelativeLayout relativeLayout;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		Set activity_main background color
		relativeLayout = (RelativeLayout)findViewById(R.id.layout_main);
		relativeLayout.setBackgroundColor(Color.DKGRAY);
        
//		Change activity to get gps position
		findViewById(R.id.main_bt_write_location).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mudaEcra = new Intent(MainActivity.this, GetLocation.class);
				startActivity(mudaEcra);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
