package com.example.ambulare.services;

import com.example.ambulare.AmbulareApplication;
import com.example.ambulare.provider.GPSContract;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AddRotaService extends IntentService {

	long rota_id;
	
	public AddRotaService() {
		super("rotas");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		
//		get nome da rota via extra do intent
		Bundle bundle = intent.getExtras();
		String rota = bundle.get(GetLocationService.NOME_ROTA).toString();
		
//		enviar o nome da rota para a base de dados
		
		ContentValues values = new ContentValues();
		values.put(GPSContract.NOME_ROTA, rota);
		Uri myNewUri = getContentResolver().insert(GPSContract.CONTENT_URI_ROTAS, values);
		
//		retirar o ID do insert que foi feito
		
		rota_id = ContentUris.parseId(myNewUri);
		
//		afetar a variavel de classe da AmbulareApplication 
		((AmbulareApplication) getApplication()).set_rota_id(rota_id);
		
		Log.d("ARS", "adicionar nova rota " + rota + " URI - " + rota_id);

	}

	public long getRota_id() {
		return rota_id;
	}
	
	

}
