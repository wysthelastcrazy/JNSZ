package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PLoginEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspLoginEntity extends RspBaseEntity{
	public PLoginEntity mEntity;
	public RspLoginEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		String login=jsonObj.optString("Login");
//		String Right=jsonObj.optString("Right");
		if("Success".equals(login)){
//		if("Fail".equals(login)){
			this.mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PLoginEntity.class);
		}else{
			this.isSucc=false;
		}
	}
}
