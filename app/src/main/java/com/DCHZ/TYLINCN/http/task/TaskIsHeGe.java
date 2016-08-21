package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqIsHeGeEntity;
import com.DCHZ.TYLINCN.http.rsp.RspIsHeGeEntity;

public class TaskIsHeGe extends BaseTask<ReqIsHeGeEntity>{

	public TaskIsHeGe(ReqIsHeGeEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspIsHeGeEntity rsp=null;
		if(isSucc){
			rsp=new RspIsHeGeEntity(jsonObj, seqNo);
		}else{
			rsp=new RspIsHeGeEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_IS_HEGE, seqNo, rsp);
	}
}
