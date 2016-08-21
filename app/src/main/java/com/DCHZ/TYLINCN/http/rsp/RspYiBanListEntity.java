package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PDaiBanListEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspYiBanListEntity extends RspBaseEntity{
	public PDaiBanListEntity mEntity;
	public RspYiBanListEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PDaiBanListEntity.class);
	}

}
