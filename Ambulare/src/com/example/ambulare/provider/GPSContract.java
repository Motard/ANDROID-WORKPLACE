package com.example.ambulare.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class GPSContract {

//	Nome da tabela
	public static final String TABLE = "Rotas";
	
//	Nome das colunas
	public static final String 	_ID = BaseColumns._ID,
								Lat = "Latitude",
								Lng = "Longitude",
								Alt = "Altitude",
							    Rota = "NomeRota";
	
	public static Uri CONTENT_URI = Uri.withAppendedPath(GPSProvider.CONTENT_URI, TABLE);
	
}
