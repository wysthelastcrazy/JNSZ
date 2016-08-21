package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqGongChengDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongChengDetailEntity;

public class TaskGongChengDetail extends BaseTask<ReqGongChengDetailEntity>{

	public TaskGongChengDetail(ReqGongChengDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGongChengDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspGongChengDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGongChengDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_GONGCHENG, seqNo, rsp);
	}
}
