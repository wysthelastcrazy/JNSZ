package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PYingFuHTDetailEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspYingFuHTDetailEntity extends RspBaseEntity{
	public PYingFuHTDetailEntity mEntity;
	public RspYingFuHTDetailEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PYingFuHTDetailEntity.class);
	}

}
