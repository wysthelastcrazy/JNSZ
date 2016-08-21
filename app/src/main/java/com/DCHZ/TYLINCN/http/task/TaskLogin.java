package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqLoginEntity;
import com.DCHZ.TYLINCN.http.rsp.RspLoginEntity;

public class TaskLogin extends BaseTask<ReqLoginEntity>{

	public TaskLogin(ReqLoginEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		RspLoginEntity rsp=null;
		if(isSucc){
			rsp=new RspLoginEntity(jsonObj, seqNo);
		}else{
			rsp=new RspLoginEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_LOGIN, seqNo, rsp);
	}
}
