package com.DCHZ.TYLINCN.commen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
/**
 * 全局常量，变量
 */
public class Global {
	private static final String TAG = "Global";
	
	public static Context mContext;
	private static HandlerThread mHandlerThread = null;
	private static Looper mLooper = null;
	private static Handler bizHandler = null;
	public static String APP_NAME = "";
	private static Toast mToast;
	public static int PKG_VER = 0;
	public static boolean isFromSplash = false;
	public static String JPUSH_ID = "";
	public static String clientid="";
	static{
		mHandlerThread = new HandlerThread(TAG,Process.THREAD_PRIORITY_BACKGROUND);
		mHandlerThread.start();
		mLooper = mHandlerThread.getLooper();
		bizHandler = new Handler(mLooper);
		
		//应用名称
		APP_NAME = "";
	}
	
	public static final Context getContext(){
		return mContext;
	}
	
	public static final void postDelay(Runnable r){
		if(r != null){
			bizHandler.post(r);
		}
	}
	
	/****
	 * 非UI操作，delay
	 * @param r
	 * @param delayMillis
	 */
	public static final void postDelay(Runnable r,long delayMillis){
		bizHandler.postDelayed(r, delayMillis);
	}

	/***
	 * 移除任务
	 * @param r
	 */
	public static final void removeDelay(Runnable r){
		bizHandler.removeCallbacks(r);
	}
	
	/***
	 * 获得QUA信息
	 * @return
	 */
	public static final String getQUA(){
		return "";
	}
	
	public static void releaseAll(){
		bizHandler.removeCallbacksAndMessages(null);
		mHandlerThread.quit();
	}
	
	public static final void toast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		mToast.show();
	}
	

	

}
