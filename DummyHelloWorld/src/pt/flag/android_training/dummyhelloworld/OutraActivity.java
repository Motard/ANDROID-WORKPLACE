package pt.flag.android_training.dummyhelloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class OutraActivity extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_outra);
	        
	    }
	 
	 public void voltaInicio(View v)
	 {
		 this.onBackPressed();
		 
	 }
}
