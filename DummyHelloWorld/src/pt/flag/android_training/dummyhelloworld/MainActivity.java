package pt.flag.android_training.dummyhelloworld;

import org.apache.http.protocol.HTTP;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
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
       
        
       
    }
    
    protected void onSaveInstanceState(Bundle outState)
    {
    	
    	super.onSaveInstanceState(outState);
    	
    	outState.putString(txt, old_txt);
    }
    
    protected void onRestoreInstanceState(Bundle returnState)
    {
    	super.onRestoreInstanceState(returnState);
    	String myString = returnState.getString("txt");
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
    	Intent intencaoMudaEcra = new Intent(this,OutraActivity.class);
    	
    	startActivity(intencaoMudaEcra);
    	
    }
    
}
