package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqTest;
import com.DCHZ.TYLINCN.http.rsp.RspTest;

public class TaskTest extends BaseTask<ReqTest>{

	public TaskTest(ReqTest t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspTest rsp=null;
		if(isSucc){
			rsp=new RspTest(jsonObj,seqNo);
		}else{
			rsp=new RspTest(null,seqNo);
		}
		//
		EventController.getInstance().notifyEvent(EventCommon.EVENT_LOGIN, seqNo, rsp);
	}

}
