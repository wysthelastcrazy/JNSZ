package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqIsHeGeEntity extends ReqBaseEntity{
	public String YWID;
	public String GFPingShenJieLun;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_ISHEGE;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("YWID", YWID);
		mMap.put("GFPingShenJieLun", GFPingShenJieLun);
		return mMap;
	}

}
