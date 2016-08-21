package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqYingShouHTDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYingShouHTDetailEntity;

public class TaskYingShouHTDetail extends BaseTask<ReqYingShouHTDetailEntity>{

	public TaskYingShouHTDetail(ReqYingShouHTDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspYingShouHTDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspYingShouHTDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspYingShouHTDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_YINGSHOU_HT, seqNo, rsp);
	}
}
