package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PGaiZhangDetailEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspGaiZhangEntity extends RspBaseEntity{
	public PGaiZhangDetailEntity mEntity;
	public RspGaiZhangEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PGaiZhangDetailEntity.class);
	}

}
