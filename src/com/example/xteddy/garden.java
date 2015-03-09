/*
* garden.java
*
* Copyright 2015 GreenTree96 <litteras@ukr.net>
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
* MA 02110-1301, USA.
*
*
*/



package com.example.xteddy; 


import android.view.WindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.Gravity;

import android.util.DisplayMetrics;

import android.graphics.PixelFormat;

import android.content.Context;
import android.view.ViewTreeObserver;

// import android.util.Log; //


public class garden
{
	WindowManager wm;
	
	//private static String name_of = "xteddy"; //
	
	public View garden_view;

	public DisplayMetrics window_in_garden ;
	
	Context _context_;
	//screen size //
	int width_of;
	int height_of;
	
	int delta_x ;
	int delta_y ;
	//image size //
	int garden_Width ;
	int garden_Height ;
	
	private WindowManager.LayoutParams params = new WindowManager.LayoutParams (
	                WindowManager.LayoutParams.WRAP_CONTENT,  // first
	                WindowManager.LayoutParams.WRAP_CONTENT,
	                
	                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
	                
	                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  
	                PixelFormat.TRANSLUCENT ) ;
	
	
	protected garden(Context context) {
		
		_context_ = context ;
		
		params.gravity = Gravity.NO_GRAVITY ;
		 
		LayoutInflater iflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)  ;
		garden_view = iflater.inflate(R.layout.in_garden_layout, null) ;
		
		params.x = 0;
	    	params.y = 0;
	    
	    garden_view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE); 
		wm.addView(garden_view, params);
		        
		garden_view.getViewTreeObserver().addOnPreDrawListener(
			    new ViewTreeObserver.OnPreDrawListener() {
			        public boolean onPreDraw() {
			    		garden_Width = garden_view.getWidth() ;
			    		garden_Height= garden_view.getHeight() ;
			        	after_wind(_context_, false);
			        	garden_view.getViewTreeObserver().removeOnPreDrawListener(this);
			      //  	Log.d( name_of, "init" );
			            return true;
			        }
			    });
        
		garden_view.setOnTouchListener(new OnTouchListener() {
		  
		          private int inX;
		          private int inY;
		          private float TouchX;
		          private float TouchY;     
		          
		          @Override 
		          public boolean onTouch(View v, MotionEvent event) {
		            switch (event.getAction())
		            {
		              case MotionEvent.ACTION_DOWN:
		            	  
		                inX = params.x;
		                inY = params.y;
		                TouchX = event.getRawX();
		                TouchY = event.getRawY();
		                
		             
		                return true;
		                
					case MotionEvent.ACTION_UP:
						
						if ( params.x < - delta_x )
						{
							params.x =  delta_x - 5 ;
						} 
						else 
						{
							if ( params.x > delta_x ) 
							{
								params.x = - delta_x + 5 ;
							}
						}
						
						if ( params.y < - delta_y )
						{
							params.y =  delta_y - 5 ;
						} 
						else 
						{
							if ( params.y >  delta_y ) 
							{
								params.y = - delta_y + 5 ;
							}
						}
						
						wm.updateViewLayout(garden_view, params); 
		                return true;
		                
		              case MotionEvent.ACTION_MOVE:
		            	  
		            		params.x = inX + ( int ) ( event.getRawX() - TouchX ) ;
		            		params.y = inY + ( int ) ( event.getRawY() - TouchY ) ; 
		            		
       		/*
		           			Log.d(name_of, String.valueOf(params.x) + "  " + String.valueOf( width_of ) + "  " + String.valueOf( garden_Width )+ "  " + String.valueOf( delta_x ) ); //
							
							if (garden_view.isHardwareAccelerated()) { //
							Log.d(name_of, "abc");
							} else {
								Log.d(name_of, "abcd"); } // */
							
		                wm.updateViewLayout(garden_view, params);
		                
		          
		                return true;
		          
		            } 
		            return false;
		          }	  
		});	
    } 
	
	public void after_wind(Context _context, boolean rotat) {
		window_in_garden = _context.getResources().getDisplayMetrics(); 
		
		int width_old = width_of ;
		int height_old = height_of ;

		width_of = window_in_garden.widthPixels ;
		height_of = window_in_garden.heightPixels ;
		
        delta_x = ( width_of  + garden_Width ) / 2 - 18 ; // 45 ;
        delta_y = ( height_of + garden_Height ) / 2 - 25 ; // 30 ;
        
    	if ( rotat ) 
		{ 
    			if ( params.x > 0 )
    			{
	        		params.x =  ( width_of / 2 - ( width_old / 2  - params.x ) ) ;
    			}
    			else
    			{
    				if (params.x < 0 )
    				{
    					params.x =  ( - width_of / 2 + ( width_old / 2  + params.x ) ) ;
    				}
    			}
    			
    			if ( params.y > 0 )
    			{
	        		params.y =  ( height_of / 2 - ( height_old / 2  - params.y ) ) ;
    			}
    			else
    			{
    				if ( params.y < 0 )
    				{
    					params.y =  ( - height_of / 2 + ( height_old / 2  + params.y ) ) ;
    				}
    			}
	        wm.updateViewLayout(garden_view, params);
	     //   Log.d( name_of, "screen rotation" );
		}
        
	}
	
	
	
	public void sleep_for () 
	{
		garden_view = null ;
		wm = null ;
		params = null ; // 	
	}
}
	
