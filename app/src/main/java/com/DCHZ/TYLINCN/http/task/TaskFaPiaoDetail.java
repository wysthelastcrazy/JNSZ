package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqFaPiaoDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFaPiaoDetailEntity;

public class TaskFaPiaoDetail extends BaseTask<ReqFaPiaoDetailEntity>{

	public TaskFaPiaoDetail(ReqFaPiaoDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspFaPiaoDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspFaPiaoDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspFaPiaoDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVEN_FAPIAO_DETAIL, seqNo, rsp);
	}
}
