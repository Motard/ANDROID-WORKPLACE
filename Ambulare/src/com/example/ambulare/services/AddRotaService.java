package com.example.ambulare.services;

import com.example.ambulare.provider.GPSContract;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AddRotaService extends IntentService {

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
		getContentResolver().insert(GPSContract.CONTENT_URI_ROTAS, values);
		Log.d("rota", "adicionar nova rota " + rota );
//		retirar o ID do insert que foi feito
		
//		String s = myNewUri.toString();
		
//		long id = ContentUris.parseId(myNewUri);
//		
//		Log.d("rota", "adicionar nova rota " + rota + "/n URI - " + id);

	}

}
