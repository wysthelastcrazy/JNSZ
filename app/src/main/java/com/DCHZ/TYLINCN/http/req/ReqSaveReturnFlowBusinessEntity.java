package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqSaveReturnFlowBusinessEntity extends ReqBaseEntity{
	public String FlowVerID;
	public String FlowSLID;
	public String YWID;
	public String FlowJDID;
	public String FLowBLID;
	public String BLUserID;
	public String CYUserID="";
	public String opinion;
	public String UrlParam;
	public String YHID;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_RETURN_BUSINESS;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("FlowVerID", FlowVerID);
		mMap.put("FlowSLID", FlowSLID);
		mMap.put("YWID", YWID);
		mMap.put("FlowJDID", FlowJDID);
		mMap.put("FLowBLID", FLowBLID);
		mMap.put("BLUserID", BLUserID);
		mMap.put("CYUserID", CYUserID);
		mMap.put("opinion", opinion);
		mMap.put("UrlParam", UrlParam);
		mMap.put("YHID", YHID);
		return mMap;
	}

}
