package pt.flag.android_training.dummyhelloworld;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	boolean xpto = false;
	String txt = "Ola Mundo";
	int color = 0xff007D00; 
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
    
    
    //OnClick method
    public void kumeKie(View view){
    	
    	tv = (TextView)findViewById(R.id.my_label_id);
    	
    	old_txt = (String)tv.getText();
    	old_color = tv.getCurrentTextColor();
    	
    	
    	tv.setText(txt);
    	tv.setTextColor(color);
//    	tv.setTextColor(_color);
//    	_color = old_color;
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
    
   
    
}
