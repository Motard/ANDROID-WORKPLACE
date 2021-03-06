package pt.flag.android_training.dummyhelloworld;

import org.apache.http.protocol.HTTP;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	boolean xpto = false;
	String txt = "Ola Mundo";
	int color = 0xffFF0DFF; 
	String old_txt;
	int old_color;
	TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    
        //TextView tv = (TextView)findViewById(R.id.my_label_id);
        //tv.setText("KUME KIE");
        
        //TextView tv1 = (TextView)findViewById(R.id.myId1);
        //tv1.setText(tv.getText());
       
        
       findViewById(R.id.my_button_open_prefs).setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainActivity.this,PrefsActivity.class));
			
		}
	});
       
//       start alarm button
       findViewById(R.id.my_button_start_alarm_id).setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// get alarm
			AlarmManager manager = (AlarmManager)MainActivity.this.getSystemService(ALARM_SERVICE);
			PendingIntent intent = PendingIntent.getActivity(MainActivity.this, 
															0, new Intent(MainActivity.this, OutraActivity.class),
															PendingIntent.FLAG_ONE_SHOT);
			manager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+5000, intent);
			
		}
	});
    }
    
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
    	
    	super.onSaveInstanceState(savedInstanceState);
    	tv = (TextView)findViewById(R.id.my_label_id);
    	savedInstanceState.putString("saveText",tv.getText().toString());
    	
    }
    
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
    	super.onRestoreInstanceState(savedInstanceState);
    	tv = (TextView)findViewById(R.id.my_label_id);
    	tv.setText(savedInstanceState.getString("saveText"));
    }
    
      
    
    //OnClick method
    public void kumeKie(View view){
    	
    	tv = (TextView)findViewById(R.id.my_label_id);
    	
    	old_txt = (String)tv.getText();
    	old_color = tv.getCurrentTextColor();
    	
    	tv.setText(txt);
    	tv.setTextColor(color);

    	color = old_color;
    	txt = old_txt;
    	
    	
//    	if (xpto) {
//    		
//            tv.setText(txt);
//            ((TextView)view).setText("clica");
//    		xpto=false;
//    	}else{
//    		
//    		txt = (String) tv.getText();
//    		tv.setText("KUME KIE");
//            ((TextView)view).setText("click");
//    		xpto=true;
//    		
//    	}
    	
   }
    
   //OnClick method for toast
    public void showToast(View v)
    {
    	
    	Toast.makeText(this, "This is a toast", Toast.LENGTH_SHORT).show();
    	
    }
    
    //OnClick method for send email
    public void enviaMail(View v)
    {
    	
    	/*
    	 * Create an implicit intent to start a component that allows 
    	 * sending an email 
    	 * */
    	
    	
    	//Extras
    	Intent intencaoMail = new Intent(Intent.ACTION_SEND);
    	intencaoMail.setType(HTTP.PLAIN_TEXT_TYPE);
    	intencaoMail.putExtra(Intent.EXTRA_EMAIL, new String[] {"pabm71@gmail.com"});
    	intencaoMail.putExtra(Intent.EXTRA_SUBJECT, "Mail de teste!!!");
    	intencaoMail.putExtra(Intent.EXTRA_TEXT, "Enviei isto automaticamente!!!");
    	
    	
    	//Send the intent
    	//this.startActivity(intencaoMail);
    	
    	//always show choose
    	
    	startActivity(Intent.createChooser(intencaoMail, "Escolha app."));
    }
    
    
    public void mudaEcra(View v)
    {
    	Toast.makeText(this, "Bazar", Toast.LENGTH_SHORT).show();
    	Intent intencaoMudaEcra = new Intent(this,OutraActivity.class);
    	
    	startActivity(intencaoMudaEcra);
    	
    }
    
    public void mandaMailPara(View v)
    {
    	
    	//  	first create an intent to open the list activity ith the email adresses
    	Intent intencaoMudaEcra = new Intent(this,MailActivity.class);

    	//    	set intent extra with the e-mail body string
    	//		step1: get the email body from EditText
    	EditText et = (EditText)findViewById(R.id.cxTexto);
    	String body = et.getText().toString();
    	
    	// 		step2: set as extras
    	
    	intencaoMudaEcra.putExtra("my_body", body);
    	
    	//		Send the intent :-(    	
    	startActivity(intencaoMudaEcra);
    }
    
  
    
}
