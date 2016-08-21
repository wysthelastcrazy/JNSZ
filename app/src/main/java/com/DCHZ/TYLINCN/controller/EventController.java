package com.DCHZ.TYLINCN.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.DCHZ.TYLINCN.listener.IEventReciever;


/***
 * 事件通知管理器
 * @date 2015/07/05
 */
public class EventController {
	private final String TAG = "EventController";
	
	private static EventController instance;
	private Set<IEventReciever> mSet = new HashSet<IEventReciever>();
	
	private EventController() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static EventController getInstance(){
		if(instance == null){
			instance = new EventController();
		}
		return instance;
	}

	/***
	 * 通知事件
	 * @param eventId
	 */
	public void notityEvent(int eventId){
		
	}

	/****
	 * 通知事件
	 * @param eventId
	 * @param obj
	 */
	public void notifyEvent(int eventId,int seqNo,Object obj){
		for(IEventReciever receiver : mSet){
			List<Integer> mList = receiver.eventId;
			for(Integer id : mList){
				if(id == eventId){
					receiver.onReceiveMsg(eventId,seqNo,obj);
				}
			}
		}
	}

	/***
	 * 注册观察者
	 * @param mReceiver
	 * @param eventId
	 */
	public void registerReceiver(IEventReciever mReceiver,int eventId){
		if(mReceiver != null){
			mReceiver.eventId.add(eventId);
			mSet.add(mReceiver);
		}
	}
	
	public void unRegisterReceiver(IEventReciever mReceiver){
		if(mReceiver != null){
			mSet.remove(mReceiver);
		}
	}
}
