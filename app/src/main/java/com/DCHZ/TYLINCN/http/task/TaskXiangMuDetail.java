package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqXiangMuDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspXiangMuDetailEntity;

public class TaskXiangMuDetail extends BaseTask<ReqXiangMuDetailEntity>{

	public TaskXiangMuDetail(ReqXiangMuDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspXiangMuDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspXiangMuDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspXiangMuDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_XIANGMU, seqNo, rsp);
	}
}
