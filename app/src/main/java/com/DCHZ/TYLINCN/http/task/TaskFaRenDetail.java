package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFeiYongDetailEntity;

public class TaskFaRenDetail extends BaseTask<ReqFaRenDetailEntity>{

	public TaskFaRenDetail(ReqFaRenDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspFaRenDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspFaRenDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspFaRenDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_FAREN, seqNo, rsp);
	}
}
