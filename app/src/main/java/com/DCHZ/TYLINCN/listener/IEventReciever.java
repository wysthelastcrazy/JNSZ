package com.DCHZ.TYLINCN.listener;

import java.util.ArrayList;
import java.util.List;

/***
 * 事件接收器
 * @date 2015/07/05
 * @author billywen
 */
public abstract class IEventReciever {
	public List<Integer> eventId = new ArrayList<Integer>();
	
	/***
	 * 发送消息到各个观察者
	 * @param msg
	 */
	public abstract void onReceiveMsg(int eventId,int seqNo,Object obj);
}
