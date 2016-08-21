package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGSTuiHuiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGSTuiHuiEntity;

public class TaskGSTuiHui extends BaseTask<ReqGSTuiHuiEntity>{

	public TaskGSTuiHui(ReqGSTuiHuiEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGSTuiHuiEntity rsp=null;
		if(isSucc){
			rsp=new RspGSTuiHuiEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGSTuiHuiEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_GSTUIHUI, seqNo, rsp);
	}
}
