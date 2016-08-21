package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqJiDuHeTongEntity;
import com.DCHZ.TYLINCN.http.rsp.RspJiDuHeTongEntity;

public class TaskJiDuHeTong extends BaseTask<ReqJiDuHeTongEntity>{

	public TaskJiDuHeTong(ReqJiDuHeTongEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspJiDuHeTongEntity rsp=null;
		if(isSucc){
			rsp=new RspJiDuHeTongEntity(jsonObj, seqNo);
		}else{
			rsp=new RspJiDuHeTongEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_JIDU_HETONG, seqNo, rsp);
	}
}
