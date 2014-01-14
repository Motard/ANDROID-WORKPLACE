package pt.pabm.listviewexampleactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.mipmap;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ListView listview = (ListView)findViewById(R.id.listview);
		String[] values = new String[]{"Android", "iPhone", "WindowsMobile", "BlackBerry", "WebOS", "Ubuntu", "Windows7", "Mac OS X",
										"Linux", "OS/2", "Ubuntu", "Windows7", "Mac OS X", "Linux", "OS/2", "Ubuntu", "Windows7", 
										"Mac OS X", "Linux", "OS/2", "Android", "iPhone", "WindowsMobile"};
		
		final ArrayList<String> list = new ArrayList<String>();
		
		for (int i=0 ; i<values.length ;  ++i)
		{
			list.add(values[i]);
		}
		
		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) 
			{
				final String item = (String) parent.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0)
					.withEndAction(new Runnable() 
					{
						
						@Override
						public void run() {
							list.remove(item);
							adapter.notifyDataSetChanged();
							view.setAlpha(1);
							
						}
					});
			
			}
		
		});
	}

	private class StableArrayAdapter extends ArrayAdapter<String>
	{
		
		HashMap<String, Integer> mIDMap = new HashMap<String, Integer>();
		
		public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects)
		{
			super(context, textViewResourceId, objects);
			for (int i=0 ; i<objects.size() ; ++i)
			{
				mIDMap.put(objects.get(i), i);
			}
		}
		
	
	
	
		public long getItemId(int position)
		{
			String item = getItem(position);
			return mIDMap.get(item);
		}
		
		public boolean hassStableIds()
		{
			return true;
		}
	}

}
