package com.example.xteddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

	Intent daemon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_main);
		//restarting with MainActivity death.
		daemon = new Intent(this, daemon_of.class);
		
	}

	 public void stand_by_me (View v) {
			 startService(daemon);
			 finish();
			 System.exit(0);
	 }
	 
	 public void for_sleep (View v) {
		 stopService(daemon);
		 finish();
		 System.exit(0);
	 }
}
	
	
	

