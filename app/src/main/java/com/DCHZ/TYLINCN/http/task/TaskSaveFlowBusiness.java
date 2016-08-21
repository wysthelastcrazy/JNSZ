package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveFlowBusinessEntity;

public class TaskSaveFlowBusiness extends BaseTask<ReqSaveFlowBusinessEntity>{

	public TaskSaveFlowBusiness(ReqSaveFlowBusinessEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspSaveFlowBusinessEntity rsp=null;
		if(isSucc){
			rsp=new RspSaveFlowBusinessEntity(jsonObj, seqNo);
		}else {
			rsp=new RspSaveFlowBusinessEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_SAVE_FLOWBUSINESS, seqNo, rsp);
	}
}
