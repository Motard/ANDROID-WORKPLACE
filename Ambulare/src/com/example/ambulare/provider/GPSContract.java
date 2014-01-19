package com.example.ambulare.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class GPSContract {

//	Nome das tabelas
	public static final String TABLE_ROTAS = "rotas",
							   TABLE_COORDENADAS = "coordenadas";
	
//	Colunas da tabela coordenadas
	public static final String 	_ID = BaseColumns._ID,
								LAT = "latitude",
								LNG = "longitude",
								ALT = "altitude",
							    ROTA_ID = "IDRota";
	
//	Colunas da tabela rotas
	public static final String NOME_ROTA = "nome_rota";
	
	public static Uri CONTENT_URI_ROTAS = Uri.withAppendedPath(GPSProvider.CONTENT_URI, TABLE_ROTAS);
	public static Uri CONTENT_URI_COORDENADAS = Uri.withAppendedPath(GPSProvider.CONTENT_URI, TABLE_COORDENADAS);
	
}
