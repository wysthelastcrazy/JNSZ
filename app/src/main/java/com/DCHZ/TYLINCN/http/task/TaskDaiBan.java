package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqDaiBanListEntity;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanListEntity;

public class TaskDaiBan extends BaseTask<ReqDaiBanListEntity>{

	public TaskDaiBan(ReqDaiBanListEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspDaiBanListEntity rsp=null;
		if(isSucc){
			rsp=new RspDaiBanListEntity(jsonObj, seqNo);
		}else{
			rsp=new RspDaiBanListEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_DAIBAN, seqNo, rsp);
	}
}
