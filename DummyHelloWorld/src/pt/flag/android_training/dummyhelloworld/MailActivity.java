package pt.flag.android_training.dummyhelloworld;

import java.util.ArrayList;

import org.apache.http.protocol.HTTP;

import pt.flag.android_training.dummyhelloworld.providers.EmailsContract;
import pt.flag.android_training.dummyhelloworld.services.AddEmailService;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi") public class MailActivity  extends ListActivity{
	
//	private ArrayList<String> emails = new ArrayList<String>();
//	private ArrayAdapter<String> adapter;
	private CursorAdapter adapter;
	
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
		
	
		//OLD//emails.add("pabm71@gmail.com");
		//OLD//emails.add("pmotard@sapo.pt");
		//OLD//emails.add("drmotard@hotmail.com");
		
		//Create an adapter object to adapt the data mdel to this ListView
		//Usage of a default adapter
		
//		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,emails);
		
		
//		Usar o meu layout do tipo ListView
//		este adapter funciona
//		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, emails);
	
//		este vai ser o meu adapter
//		com o layout simples
//		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.mail_layout,emails);
	
//		este é o layout alternativo com texto e butão
//		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.mail_layout_2, R.id.my_text_view, emails);
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.mail_layout_2);
//		adapter = new ContactsAdapter();
		
		//Set adapter to the list
//		setListAdapter(adapter);
		
		
		new FetchEmailsAsyncTask().execute();
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		
		//get the email string
		//TODO what if the item is not a TextView?!
//		String email = ((TextView)v).getText().toString();
//		String email = emails.get(position);
		
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
		String email = cursor.getString(cursor.getColumnIndex(EmailsContract.EMAIL));
		
		//get the email-body
		//TODO name of the extra is hard coded 
		String body = getIntent().getStringExtra("my_body");
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		body += prefs.getString("signature", "");
		
		//create implicit intent to send the email
		Intent intencaoMail = new Intent(Intent.ACTION_SEND);
		intencaoMail.setType(HTTP.PLAIN_TEXT_TYPE);
		//Set the intent extra
		intencaoMail.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
		intencaoMail.putExtra(Intent.EXTRA_TEXT, body);
		//send the intent
		startActivity(Intent.createChooser(intencaoMail, "Escolha app."));
		
	}
	
	private class ContactsAdapter extends CursorAdapter //extends ArrayAdapter<String>
	{
		
		
		
		public ContactsAdapter( Cursor cursor) {
			
			super(MailActivity.this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		}

//		public ContactsAdapter()
//		{
////			the context is the cirrent activity(MailActivity instance)
//			super(MailActivity.this,0,emails);
//		}
		
//		@Override
//		public View getView(final int position, View convertView, ViewGroup parent) {
//			
////			final int pos = position;
//			
//			// TODO Auto-generated method stub
//			convertView = getLayoutInflater().inflate(R.layout.mail_layout_2, null);
//			
////			set the email in the row
//			((TextView)convertView.findViewById(R.id.my_mail_view)).setText(emails.get(position));
//			
////			Set onClick event for delete the email from the list
//			convertView.findViewById(R.id.bt_del_mail).setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// Delete the email on that position
////					emails.remove(pos);
//					emails.remove(position);
//					ContactsAdapter.this.notifyDataSetChanged();
//				}
//			});
//			return convertView;
//		}
		
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
		public void bindView(View v, Context ctx, Cursor cursor) {
//			set text email
			((TextView)v.findViewById(R.id.my_mail_view)).setText(cursor.getString(cursor.getColumnIndex(EmailsContract.EMAIL)));
//			TODO set the event to DEL button
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			return getLayoutInflater().inflate(R.layout.mail_layout_2, null);
		}
		
		@Override
		protected void onContentChanged() {
			// TODO Auto-generated method stub
			super.onContentChanged();
			new FetchEmailsAsyncTask().execute();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean resSuper=super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.contacts, menu);
		return resSuper && true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
//		add new contact
		switch (item.getItemId()) {
		case R.id.my_add_contact_menu_id:
			final EditText editText = new EditText(this);
//			Create a Dialog
			new AlertDialog.Builder(this).setTitle("new e-mail")
										 .setNegativeButton("cancel", null)
										 .setPositiveButton("add", new OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
//												adapter.add(editText.getText().toString());
												Intent intent = new Intent(MailActivity.this, AddEmailService.class);
												intent.putExtra("BATATE", editText.getText().toString());
												startService(intent);
											}
										})
										.setView(editText)// Returns a Builder
										.create() //Returns an AlertDialog
										.show();
			return true;
			
//			open preferences activity
		case R.id.my_open_prefs_id:
			startActivity(new Intent(this, PrefsActivity.class));
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	private class FetchEmailsAsyncTask extends AsyncTask<Void, Void, Cursor>
	{

//		Run in alternative Thread
		@Override
		protected Cursor doInBackground(Void... params) {
			// TODO Make the query.Start managing cursor and return it.
			Cursor cursor = getContentResolver().query(EmailsContract.CONTENT_URI, null, null, null, null);
			
			cursor.setNotificationUri(getContentResolver(), EmailsContract.CONTENT_URI);
//			Tells that the MailActivity controls the life-cycle of the cursor
			startManagingCursor(cursor);
			return cursor;
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Cursor newCursor) {
			
			if(adapter == null)
			{
				adapter = new ContactsAdapter(newCursor);
				MailActivity.this.setListAdapter(adapter);
			}else{
				stopManagingCursor(adapter.getCursor());
				adapter.changeCursor(newCursor);
			}
		}
	}
}
