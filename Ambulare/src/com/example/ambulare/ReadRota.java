package com.example.ambulare;

import com.example.ambulare.provider.GPSContract;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ReadRota extends ListActivity 
{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new FetchRotasAsyncTask().execute();
		
			}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, ReadCoordenadas.class);
		intent.putExtra(ReadCoordenadas.ROTA_ID, id);
		startActivity(intent);
		
//		O id obtido é o ID da linha, que por acaso coincide com o ID da base dados. A maneira é usar um campo hidden com o ID da base dados e enviar esse valor	
	}
	
	
	private class FetchRotasAsyncTask extends AsyncTask<Void, Void, Cursor>
	{

		@SuppressWarnings("deprecation")
		@Override
		protected Cursor doInBackground(Void... params) 
		{
			
			Cursor cursor = getContentResolver().query(GPSContract.CONTENT_URI_ROTAS, null, null, null, null);
			startManagingCursor(cursor);
			return cursor;
		}
		
		@Override
		protected void onPostExecute(Cursor newCursor) 
		{
			@SuppressWarnings("deprecation")
			CursorAdapter adapter = new SimpleCursorAdapter(ReadRota.this, android.R.layout.simple_list_item_1, newCursor, new String[]{GPSContract.NOME_ROTA}, new int[] {android.R.id.text1});
			ReadRota.this.setListAdapter(adapter);
		}
	}
}
