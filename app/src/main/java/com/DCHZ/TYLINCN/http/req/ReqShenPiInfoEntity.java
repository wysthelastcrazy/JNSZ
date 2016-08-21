package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;
/**
 * 审批信息
 * @author wys
 *
 */
public class ReqShenPiInfoEntity extends ReqBaseEntity{
	public String YWID;
	public String SLID;
	public String LCID;
	public String JDID;
	public String UrlParam;
	public String YHID;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_SHENPI_INFO;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String,String> mMap=new HashMap<String, String>();
		mMap.put("YWID", YWID);
		mMap.put("SLID", SLID);
		mMap.put("LCID", LCID);
		mMap.put("JDID", JDID);
		mMap.put("UrlParam", UrlParam);
		mMap.put("YHID", YHID);
		return mMap;
	}

}
