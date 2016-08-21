package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGongShiShenPiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongShiShenPiEntity;

public class TaskGongShiShenPi extends BaseTask<ReqGongShiShenPiEntity>{

	public TaskGongShiShenPi(ReqGongShiShenPiEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGongShiShenPiEntity rsp=null;
		if(isSucc){
			rsp=new RspGongShiShenPiEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGongShiShenPiEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_GONGSHI_SHENPI, seqNo, rsp);
	}
}
