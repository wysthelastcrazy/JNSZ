package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqYingFuHTDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYingFuHTDetailEntity;

public class TaskYingFuHTDetail extends BaseTask<ReqYingFuHTDetailEntity>{

	public TaskYingFuHTDetail(ReqYingFuHTDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspYingFuHTDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspYingFuHTDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspYingFuHTDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_YINGFU_HT, seqNo, rsp);
	}
}
