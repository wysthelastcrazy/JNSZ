package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqQingJiaDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspQingJiaDetailEntity;

public class TaskQingJiaDetail extends BaseTask<ReqQingJiaDetailEntity>{

	public TaskQingJiaDetail(ReqQingJiaDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspQingJiaDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspQingJiaDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspQingJiaDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_QINGJIA_DETAIL, seqNo, rsp);
	}
}
