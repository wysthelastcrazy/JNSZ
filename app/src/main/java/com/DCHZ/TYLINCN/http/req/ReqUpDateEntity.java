package com.DCHZ.TYLINCN.http.req;

import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqUpDateEntity extends ReqBaseEntity{

	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_UPDATE;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		return null;
	}

}
