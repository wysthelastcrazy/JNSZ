package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONArray;
import org.json.JSONObject;

import com.DCHZ.TYLINCN.http.base.RspBaseEntity;
import com.DCHZ.TYLINCN.util.MyLog;


public class RspTest extends RspBaseEntity{

	public RspTest(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		MyLog.debug(TAG, "[parseData]"+jsonObj.toString());
	}
}
