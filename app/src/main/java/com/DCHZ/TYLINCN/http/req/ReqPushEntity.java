package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqPushEntity extends ReqBaseEntity{
	public String strYHID;
	public String strClientID;
	public String strDeviceToken;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_PUSH;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("strYHID", strYHID);
		mMap.put("strClientID", strClientID);
		mMap.put("strDeviceToken", strDeviceToken);
		return mMap;
	}

}
