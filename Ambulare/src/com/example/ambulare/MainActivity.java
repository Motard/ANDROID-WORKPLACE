package com.example.ambulare;

import com.example.ambulare.getlocation.GetLocation;

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
		findViewById(R.id.main_bt_write_location).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				final EditText editText = new EditText(MainActivity.this);
				new AlertDialog.Builder(MainActivity.this).setTitle("Nova Rota")
				 .setNegativeButton("cancel", null)
				 .setPositiveButton("add", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent mudaEcra = new Intent(MainActivity.this, GetLocation.class);
						mudaEcra.putExtra(GetLocation.NOME_ROTA, editText.getText().toString());
						startActivity(mudaEcra);
//						startService(intent);
					}
				})
				.setView(editText)// Returns a Builder
				.create() //Returns an AlertDialog
				.show();
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
