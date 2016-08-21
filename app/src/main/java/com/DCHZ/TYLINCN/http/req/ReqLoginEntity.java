package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqLoginEntity extends ReqBaseEntity{
	public String UserName;
	public String UserPwd;
	public String strClientID;
	public String strDeviceToken;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_LOGIN;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("UserName", UserName);
		mMap.put("UserPwd", UserPwd);
		mMap.put("strClientID",Global.clientid);
		mMap.put("strDeviceToken", "");
		return mMap;
	}

}
