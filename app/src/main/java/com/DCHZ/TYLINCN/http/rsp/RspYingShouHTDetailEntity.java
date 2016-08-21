package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PYingShouHTDetailEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspYingShouHTDetailEntity extends RspBaseEntity{
	public PYingShouHTDetailEntity mEntity;
	public RspYingShouHTDetailEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PYingShouHTDetailEntity.class);
	}

}
