package com.common.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import android.text.TextUtils;

import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.SharePreIPUtil;
import com.common.common.NetCommon;
import com.common.thread.ThreadPool;

/***
 * HttpEngin
 * 添加对应的任务到这个引擎中
 * @date 2015/06/27
 * @author wys
 */
public class HttpEngine extends Thread{
	private final String TAG = "HttpEngine";
	
	private static HttpEngine instace;
	private Vector<HttpBaseTask<Object>> mVector = new Vector<HttpBaseTask<Object>>();
	private boolean isRunning = true;
	private Map<Integer,String> mMapUrl = new HashMap<Integer,String>();
	private int mDevType = 0;
	private String defultTestIp="192.168.1.2:8010";
	private String testIp="";
	private String defultIp="60.208.75.182:9080";
	private String ip="";
	private HttpEngine() {
		// TODO Auto-generated constructor stub
		super("HttpEnginePool");
		mMapUrl.put(NetCommon.NET_TYPE_INDEX_OFFICAL,"192.168.1.2:8001/DCMobileWebService/MobileService.asmx");
		setPriority(Thread.MAX_PRIORITY);
	}

	/***
	 * 启动Http引擎
	 */
	public void startEngine(){
		this.start();
	}
	
	@Override
	public void run() {
		super.run();
		while(isRunning){
			synchronized (mVector) {
				while(mVector.size() <= 0){
				     try {
				    	 mVector.wait();
				     	} catch (InterruptedException e) {
				     		e.printStackTrace();
				     	}
					 }
				 if(mVector.size() > 0){
					 final HttpBaseTask task = mVector.remove(0);
					 try {
						mVector.wait(100);
					 } catch (InterruptedException ee) {
							MyLog.error(TAG,ee);
					 }
				     ThreadPool.getInstance().submmitJob(new Runnable() {
							@Override
							public void run() {
								task.doTask();
								task.recyle();
							}
						});
				 }
			}
		}
	}
	
	public static final HttpEngine getInstance(){
		if(instace == null){
			instace = new HttpEngine();
		}
		return instace;
	}

	/***
	 * 添加Http任务
	 * @param task
	 */
	public void addTask(HttpBaseTask<Object> task){
		synchronized (mVector) {
			if(MyLog.isDebugable()){
				MyLog.debug(TAG,"[addTask]" + " size:" + mVector.size());
			}
			mVector.add(task);
			mVector.notify();
		}
	}
	
	public void stopAll(){
		isRunning = false;
	}
	
	public String getRefer(){
		testIp=SharePreIPUtil.loadIpInfo();
		ip=SharePreIPUtil.loadIpInfo();
//		if(!TextUtils.isEmpty(testIp)){
//			return "http://"+testIp+"/CCSFlat/CCSMobile/MobileService.asmx";
//		}else{
//			SharePreIPUtil.saveIpInfo(defultTestIp);
//			return "http://"+defultTestIp+"/CCSFlat/CCSMobile/MobileService.asmx";
//		}
		if(!TextUtils.isEmpty(ip)){
			return "http://"+ip+"/CCSFlat/CCSMobile/MobileService.asmx";
		}else{
			SharePreIPUtil.saveIpInfo(defultTestIp);
			return "http://"+defultIp+"/CCSFlat/CCSMobile/MobileService.asmx";
		}
	}
}
