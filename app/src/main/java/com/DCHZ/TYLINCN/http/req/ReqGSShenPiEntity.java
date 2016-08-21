package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqGSShenPiEntity extends ReqBaseEntity{
	public String GSIDs;
	public String SPYHID;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_GSSHENPI;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("GSIDs", GSIDs);
		mMap.put("SPYHID", SPYHID);
		return mMap;
	}

}
