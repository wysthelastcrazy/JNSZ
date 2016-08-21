package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PUpdateListEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspUpDateEntity extends RspBaseEntity{
	public PUpdateListEntity mEntity;
	public RspUpDateEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PUpdateListEntity.class);
	}

}
