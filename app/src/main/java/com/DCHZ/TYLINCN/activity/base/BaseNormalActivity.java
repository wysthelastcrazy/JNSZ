package com.DCHZ.TYLINCN.activity.base;


import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.listener.IEventReciever;

import android.os.Message;

/****
 * 网络回调事件Activity基类文件
 * @date 2015/07/05
 * @author wys
 */
public abstract class BaseNormalActivity extends BaseHandlerActivity{

	/***
	 * UI事件相关处理
	 */
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventController.getInstance().unRegisterReceiver(mReceiver);
	}

	protected void registMsgRecevier(int eventId){
		EventController.getInstance().registerReceiver(mReceiver,eventId);
	}
	
	protected IEventReciever mReceiver = new IEventReciever() {
		@Override
		public void onReceiveMsg(int eventId,int seqNo,Object obj) {
			handleReceiveMsg(eventId,seqNo,obj);
		}
	};
	
	public abstract void handleReceiveMsg(int eventId,int seqNo,Object obj);
}
