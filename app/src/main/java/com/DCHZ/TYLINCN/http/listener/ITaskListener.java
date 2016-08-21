package com.DCHZ.TYLINCN.http.listener;

import org.json.JSONObject;

public interface ITaskListener {
	public void recyle();
	public void getResponse(JSONObject jsonObj,boolean isSucc,int errorCode,int seqNo);
	public Object getReq();
}