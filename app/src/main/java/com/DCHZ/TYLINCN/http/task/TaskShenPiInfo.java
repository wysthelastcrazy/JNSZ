package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqShenPiInfoEntity;
import com.DCHZ.TYLINCN.http.rsp.RspShenPiInfoEntity;

public class TaskShenPiInfo extends BaseTask<ReqShenPiInfoEntity>{

	public TaskShenPiInfo(ReqShenPiInfoEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspShenPiInfoEntity rsp=null;
		if(isSucc){
			rsp=new RspShenPiInfoEntity(jsonObj, seqNo);
		}else{
			rsp=new RspShenPiInfoEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_SHENPI_INFO, seqNo, rsp);
	}
}
