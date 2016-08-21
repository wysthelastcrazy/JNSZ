package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGSShenPiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGSShenPiEntity;

public class TaskGSShenPi extends BaseTask<ReqGSShenPiEntity>{

	public TaskGSShenPi(ReqGSShenPiEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGSShenPiEntity rsp=null;
		if(isSucc){
			rsp=new RspGSShenPiEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGSShenPiEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVEN_GSSHENPI, seqNo, rsp);
	}
}
