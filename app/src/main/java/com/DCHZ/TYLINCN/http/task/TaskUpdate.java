package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqUpDateEntity;
import com.DCHZ.TYLINCN.http.rsp.RspUpDateEntity;

public class TaskUpdate extends BaseTask<ReqUpDateEntity>{

	public TaskUpdate(ReqUpDateEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspUpDateEntity rsp=null;
		if(isSucc){
			rsp=new RspUpDateEntity(jsonObj, seqNo);
		}else{
			rsp=new RspUpDateEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_UPDATE, seqNo, rsp);
	}
}
