package com.example.ambulare.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class GPSContract {

//	Nome das tabelas
	public static final String TABLE = "rotas",
							   TABLE_COORDENADAS = "coordenadas";
	
//	Nome das colunas
	public static final String 	_ID = BaseColumns._ID,
								Lat = "latitude",
								Lng = "longitude",
								Alt = "altitude",
							    Rota = "IDRota",
							    NomeRota = "nome_rota";
	
	
	public static Uri CONTENT_URI = Uri.withAppendedPath(GPSProvider.CONTENT_URI, TABLE);
	
}
