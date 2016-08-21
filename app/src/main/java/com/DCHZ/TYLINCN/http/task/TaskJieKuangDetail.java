package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqJieKuangDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspJieKuangDetailEntity;

public class TaskJieKuangDetail extends BaseTask<ReqJieKuangDetailEntity>{

	public TaskJieKuangDetail(ReqJieKuangDetailEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspJieKuangDetailEntity rsp=null;
		if(isSucc){
			rsp=new RspJieKuangDetailEntity(jsonObj, seqNo);
		}else{
			rsp=new RspJieKuangDetailEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_JIEKUANG_DETAIL, seqNo, rsp);
	}
}
