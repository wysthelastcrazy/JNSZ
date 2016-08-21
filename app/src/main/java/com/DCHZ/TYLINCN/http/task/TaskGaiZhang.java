package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGaiZhangDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGaiZhangEntity;

public class TaskGaiZhang extends BaseTask<ReqGaiZhangDetailEntity>{

	public TaskGaiZhang(ReqGaiZhangDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGaiZhangEntity rsp=null;
		if(isSucc){
			rsp=new RspGaiZhangEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGaiZhangEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_GAIZHANG, seqNo, rsp);
	}

}
