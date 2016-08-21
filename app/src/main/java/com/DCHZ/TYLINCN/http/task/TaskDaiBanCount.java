package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqDaiBanCountEntity;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanCountEntity;

public class TaskDaiBanCount extends BaseTask<ReqDaiBanCountEntity>{

	public TaskDaiBanCount(ReqDaiBanCountEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspDaiBanCountEntity rsp=null;
		if(isSucc){
			rsp=new RspDaiBanCountEntity(jsonObj, seqNo);
		}else{
			rsp=new RspDaiBanCountEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_DAIBAN_COUNT, seqNo, rsp);
	}
}
