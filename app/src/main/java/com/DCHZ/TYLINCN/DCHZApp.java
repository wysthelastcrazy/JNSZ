package com.DCHZ.TYLINCN;

import android.app.Application;
import com.DCHZ.TYLINCN.commen.Global;
import com.common.http.HttpEngine;
import com.igexin.sdk.PushManager;

public class DCHZApp extends Application{
	private final String TAG = "DCHZApp";
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//init context
		Global.mContext = getApplicationContext();
		delayTask();
	}

	private void delayTask() {
		// TODO Auto-generated method stub
		Global.postDelay(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpEngine.getInstance().start();
				PushManager.getInstance().initialize(DCHZApp.this);
			}
			
		});
	}
	
}
