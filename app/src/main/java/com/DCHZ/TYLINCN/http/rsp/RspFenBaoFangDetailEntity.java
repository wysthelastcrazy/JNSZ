package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PFenBaoFangDetailEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspFenBaoFangDetailEntity extends RspBaseEntity{
	public PFenBaoFangDetailEntity mEntity;
	public RspFenBaoFangDetailEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PFenBaoFangDetailEntity.class);
	}

}
