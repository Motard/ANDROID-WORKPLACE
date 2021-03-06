package com.example.ambulare;


import com.example.ambulare.services.GetLocationService;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		RelativeLayout relativeLayout;	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		Set activity_main background color
		relativeLayout = (RelativeLayout)findViewById(R.id.layout_main);
		relativeLayout.setBackgroundColor(Color.DKGRAY);
        
//		Ver o estado do recording
		AmbulareApplication app = (AmbulareApplication) getApplication();
		if (app.isRecordingRoute())
		{
			((TextView)findViewById(R.id.main_bt_write_location)).setText("STOP");
		}
		
	//		Change activity to get gps position
		findViewById(R.id.main_bt_write_location).setOnClickListener(new View.OnClickListener() 
		{
				
			@Override
			public void onClick(View v) 
			{
				
				if (!((AmbulareApplication) getApplication()).isRecordingRoute())
				{
				
					
					final EditText editText = new EditText(MainActivity.this);
					new AlertDialog.Builder(MainActivity.this).setTitle("Nova Rota")
					 .setNegativeButton("cancel", null)
					 .setPositiveButton("add", new DialogInterface.OnClickListener() 
					 {
						
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							((TextView)findViewById(R.id.main_bt_write_location)).setText("STOP");
							
							Intent gravarRota = new Intent(MainActivity.this, GetLocationService.class);
							gravarRota.putExtra(GetLocationService.NOME_ROTA, editText.getText().toString());
							startService(gravarRota);
							
							((AmbulareApplication) getApplication()).setRecording(true);
						}
					})
					.setView(editText)// Returns a Builder
					.create() //Returns an AlertDialog
					.show();
				}
				else
				{
					((TextView)findViewById(R.id.main_bt_write_location)).setText("GRAVAR");
					
					stopService(new Intent(MainActivity.this, GetLocationService.class));
					
					((AmbulareApplication) getApplication()).setRecording(false);
				}
			}
		});
		
		
		findViewById(R.id.main_bt_ver_BD).setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				
				Intent lerBD = new Intent(MainActivity.this, ReadRota.class);
				startActivity(lerBD);
				
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
