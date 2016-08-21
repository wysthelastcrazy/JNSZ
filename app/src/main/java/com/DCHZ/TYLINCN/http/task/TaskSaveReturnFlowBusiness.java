package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.req.ReqSaveReturnFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveReturnFlowBusinessEntity;

public class TaskSaveReturnFlowBusiness extends BaseTask<ReqSaveReturnFlowBusinessEntity>{

	public TaskSaveReturnFlowBusiness(ReqSaveReturnFlowBusinessEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspSaveReturnFlowBusinessEntity rsp=null;
		if(isSucc){
			rsp=new RspSaveReturnFlowBusinessEntity(jsonObj, seqNo);
		}else {
			rsp=new RspSaveReturnFlowBusinessEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS, seqNo, rsp);
	}
}
