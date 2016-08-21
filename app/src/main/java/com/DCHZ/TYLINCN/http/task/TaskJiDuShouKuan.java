package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqJiDuShouKuanEntity;
import com.DCHZ.TYLINCN.http.rsp.RspJiDuShouKuanEntity;

public class TaskJiDuShouKuan extends BaseTask<ReqJiDuShouKuanEntity>{

	public TaskJiDuShouKuan(ReqJiDuShouKuanEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspJiDuShouKuanEntity rsp=null;
		if(isSucc){
			rsp=new RspJiDuShouKuanEntity(jsonObj, seqNo);
		}else{
			rsp=new RspJiDuShouKuanEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_JIDU_SHOUKUAN, seqNo, rsp);
	}
}
