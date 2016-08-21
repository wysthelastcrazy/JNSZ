package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqPushEntity;
import com.DCHZ.TYLINCN.http.rsp.RspPushEntity;

public class TaskPushEntity extends BaseTask<ReqPushEntity>{

	public TaskPushEntity(ReqPushEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspPushEntity rsp=null;
		if(isSucc){
			rsp=new RspPushEntity(jsonObj, seqNo);
		}else{
			rsp=new RspPushEntity(null,seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_GONGCHENG, seqNo, rsp);
	}
}
