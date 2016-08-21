package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFeiYongDetailEntity;

public class TaskFeiYongDetail extends BaseTask<ReqFeiYongDetailEntity>{

	public TaskFeiYongDetail(ReqFeiYongDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspFeiYongDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspFeiYongDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspFeiYongDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_FEIYONG_DETAIL, seqNo, rsp);
	}
}
