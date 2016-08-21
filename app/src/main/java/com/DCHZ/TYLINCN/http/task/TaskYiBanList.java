package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqYiBanListEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYiBanListEntity;

public class TaskYiBanList extends BaseTask<ReqYiBanListEntity>{

	public TaskYiBanList(ReqYiBanListEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspYiBanListEntity rsp=null;
		if(isSucc){
			rsp=new RspYiBanListEntity(jsonObj, seqNo);
		}else{
			rsp=new RspYiBanListEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_YIBAN, seqNo, rsp);
	}
}
