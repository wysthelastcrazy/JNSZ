package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqFenBaoFangDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFenBaoFangDetailEntity;

public class TaskFenBaoFangDetail extends BaseTask<ReqFenBaoFangDetailEntity>{

	public TaskFenBaoFangDetail(ReqFenBaoFangDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspFenBaoFangDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspFenBaoFangDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspFenBaoFangDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_FENBAOFANG, seqNo, rsp);
	}
}
