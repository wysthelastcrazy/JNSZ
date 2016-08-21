package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PQingJiaDetailEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspQingJiaDetailEntity extends RspBaseEntity{
	public PQingJiaDetailEntity mEntity;
	public RspQingJiaDetailEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PQingJiaDetailEntity.class);
	}

}
