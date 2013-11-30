package pt.flag.android_training.dummyhelloworld;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	boolean xpto = false;
	String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //TextView tv = (TextView)findViewById(R.id.my_label_id);
        //tv.setText("KUME KIE");
        
        //TextView tv1 = (TextView)findViewById(R.id.myId1);
        //tv1.setText(tv.getText());
        
       
    }
    
    
    //OnClick method
    public void kumeKie(View view){
    	
    	TextView tv = (TextView)findViewById(R.id.my_label_id);
    	
    	
    	if (xpto) {
    		
            tv.setText(txt);
            ((TextView)view).setText("clica");
    		xpto=false;
    	}else{
    		
    		txt = (String) tv.getText();
    		tv.setText("KUME KIE");
            ((TextView)view).setText("click");
    		xpto=true;
    		
    	}
    	
    }
    
   
    
}
