package com.example.custombaseadaper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ImageTextisBaseAdapterActivity extends Activity implements
		OnItemClickListener {

	public static final String[] titles = new String[] {"Morango","Banana","Laranja","Tutifruta"};
	public static final String[] descriptions = new String[] {"" +
			"É um fruto acessório",
			"É a maior planta herbicola",
			"Citrino",
			"Salada de frutas"};
	public static final Integer[] images = {R.drawable.straw,R.drawable.banana,R.drawable.orange,R.drawable.mixed};
	
	ListView listView;
	List<RowItem> rowItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		rowItems = new ArrayList<RowItem>();
		for(int i = 0; i < titles.length; i++)
		{
			RowItem item = new RowItem(images[i], titles[i],descriptions[i]);
			rowItems.add(item);
		}
		
		listView = (ListView) findViewById(R.id.list);
		CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast toast = Toast.makeText(getApplicationContext(),"Item " + (position + 1) + ": " + rowItems.get(position), Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
	}

}
