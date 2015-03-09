package com.example.xteddy;
 
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
//import android.util.Log;
import android.content.res.Configuration;

 
public class daemon_of extends Service {
 
//	private static String name_of = "xteddy";
	static Context context;
	garden garden_of;
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		daemon_of.context = getApplicationContext(); 
		garden_of = new garden(this); //
		
		//Log.d(name_of, "daemon_of started");
		return START_NOT_STICKY;
	}
 
	@Override
	public void onDestroy() {
		super.onDestroy();
		garden_of.sleep_for();
		garden_of = null ;
	}
	
	public static Context getAppContext() {
		return daemon_of.context; 
	}


	@Override
	public void onConfigurationChanged(Configuration _configure) {
	    super.onConfigurationChanged(_configure);
		
		garden_of.after_wind(this, true) ;
	    
	  //  Log.d(name_of, "screen rotation");
	}
	
	@Override 
	public void onTaskRemoved(Intent rootIntent) {
		
		//Log.d(name_of, "daemon_of destroyed");
		garden_of.sleep_for();
		garden_of = null ;
	}
}