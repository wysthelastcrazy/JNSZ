package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PGongShiListEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;
import com.DCHZ.TYLINCN.util.MyLog;

public class RspGongShiShenPiEntity extends RspBaseEntity{
	public PGongShiListEntity mEntity;
	public RspGongShiShenPiEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		MyLog.debug(TAG, "[parseData] jsonObj:"+jsonObj.toString());
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PGongShiListEntity.class);
	}

}
