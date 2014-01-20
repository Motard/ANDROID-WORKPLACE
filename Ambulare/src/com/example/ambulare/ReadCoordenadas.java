package com.example.ambulare;



import com.example.ambulare.provider.GPSContract;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class ReadCoordenadas extends ListActivity 
{
	private CursorAdapter adapter;
	
	public static final String ROTA_ID = "rota_id";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	
		super.onCreate(savedInstanceState);
		
//		Recebe o intent e obtem o id da rota via extra
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		long id = bundle.getLong(ROTA_ID);
		
		
		Log.d("Read", "Read Coordenadas - id = " + id);
		
		new FetchCoordenadasAsyncTask().execute(id);
		
	}

	private class CoordenadasAdapter extends CursorAdapter
	{

		public CoordenadasAdapter(Cursor cursor) {
			super(ReadCoordenadas.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
			
		}
		
		@Override
		public boolean areAllItemsEnabled() {
			// TODO Auto-generated method stub
			return super.areAllItemsEnabled();
		}
		
		@Override
		public boolean isEnabled(int position) {
			// TODO Auto-generated method stub
			return super.isEnabled(position);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			((TextView)view.findViewById(R.id.tv_altitude)).setText(cursor.getString(cursor.getColumnIndex(GPSContract.ALT)));
			((TextView)view.findViewById(R.id.tv_latitude)).setText(cursor.getString(cursor.getColumnIndex(GPSContract.LAT)));
			((TextView)view.findViewById(R.id.tv_longitude)).setText(cursor.getString(cursor.getColumnIndex(GPSContract.LNG)));
			
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			
			
			return getLayoutInflater().inflate(R.layout.read_coordenadas_layout, null);
		}
		
		
	}
	
	private class FetchCoordenadasAsyncTask extends AsyncTask<Long, Void, Cursor>
	{

		@Override
		protected Cursor doInBackground(Long... params) {
			
			Cursor cursor = getContentResolver().query(GPSContract.CONTENT_URI_COORDENADAS,null, GPSContract.ROTA_ID + "=?",new String[] {"" + params[0]},  null);
			startManagingCursor(cursor);
			return cursor;
		}
		
		@Override
		protected void onPostExecute(Cursor newCursor) {
			
			if(adapter == null)
			{
				adapter = new CoordenadasAdapter(newCursor);
				ReadCoordenadas.this.setListAdapter(adapter);
			}else{
				stopManagingCursor(adapter.getCursor());
				adapter.changeCursor(newCursor);
			}
		}
		
	}
}
