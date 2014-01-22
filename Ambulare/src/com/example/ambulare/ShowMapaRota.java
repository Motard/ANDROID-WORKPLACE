package com.example.ambulare;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ShowMapaRota extends FragmentActivity {

	static final LatLng HAMBURG = new LatLng(53.558,9.927);
	private GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_rota);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//		Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG).title("Hamburg"));
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG,15));
		
		map.animateCamera(CameraUpdateFactory.zoomTo(10),2000, null);
	}
}
