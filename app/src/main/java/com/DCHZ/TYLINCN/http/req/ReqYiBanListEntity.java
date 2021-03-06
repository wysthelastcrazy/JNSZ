package com.DCHZ.TYLINCN.http.req;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqYiBanListEntity extends ReqBaseEntity{
	public String YHID;
	public int pageSize;
	public int pageIndex;
	public String strWhere="";

	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_YIBAN_FENYE_V2;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String,String> mMap=new HashMap<String, String>();
		mMap.put("YHID", YHID);
		if(pageSize>0){
			mMap.put("pageSize", pageSize+"");
		}
		if(pageIndex>0){
			mMap.put("pageIndex", pageIndex+"");
		}
		try {
			strWhere= URLEncoder.encode(strWhere, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mMap.put("strWhere",strWhere);
		return mMap;
	}

}
