package pt.flag.android_training.dummyhelloworld;

import java.util.ArrayList;

import org.apache.http.protocol.HTTP;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MailActivity  extends ListActivity{
	ListView listView;
	ArrayList<String> emails = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		/*
		 * In this example no layout is defined
		 * So is not necessary to inflate the view
		 * 
		 * */
		
		//Criar lista emails.
		
		/*String[] emails = new String[]{	"pabm71@gmail.com",
										"pmotard@sapo.pt",
										"drmotard@hotmail.com"};*/
		
		//TODO: do i need reference to e-mail list?!
		emails.add("pabm71@gmail.com");
		emails.add("pmotard@sapo.pt");
		emails.add("drmotard@hotmail.com");
		
		//Create an adapter object to adapt the data mdel to this ListView
		//Usage of a default adapter
		
//		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,emails);
		
		
//		Usar o meu layout do tipo ListView
//		este adapter funciona
//		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, emails);
	
//		este vai ser o meu adapter
//		com o layout simples
//		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.mail_layout,emails);
	
//		este � o layout alternativo com texto e but�o
		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.mail_layout_2, R.id.my_text_view, emails);
		

		//Set adapter to the list
		setListAdapter(adapter);
		
		
//		TODO explorar esta parte
//		http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews
//		ListView listView = (ListView) findViewById(R.id.listview);
//		listView.setAdapter(adapter);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		
		//get the email string
		//TODO what if the item is not a TextView?!
		String email = ((TextView)v).getText().toString();

		
		//get the email-body
		//TODO name of the extra is hard coded 
		String body = getIntent().getStringExtra("my_body");
		
		
		//create implicit intent to send the email
		Intent intencaoMail = new Intent(Intent.ACTION_SEND);
		intencaoMail.setType(HTTP.PLAIN_TEXT_TYPE);
		//Set the intent extra
		intencaoMail.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
		intencaoMail.putExtra(Intent.EXTRA_TEXT, body);
		//send the intent
		startActivity(Intent.createChooser(intencaoMail, "Escolha app."));
		
	}
	
	
}