package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqBaiLiYiJianEntity;
import com.DCHZ.TYLINCN.http.rsp.RspBanLiYiJianEntity;

public class TaskBanLiYiJian extends BaseTask<ReqBaiLiYiJianEntity>{

	public TaskBanLiYiJian(ReqBaiLiYiJianEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspBanLiYiJianEntity rsp=null;
		if(isSucc){
			rsp=new RspBanLiYiJianEntity(jsonObj, seqNo);
		}else{
			rsp=new RspBanLiYiJianEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_BANLI_YIJIAN, seqNo, rsp);
	}
}
