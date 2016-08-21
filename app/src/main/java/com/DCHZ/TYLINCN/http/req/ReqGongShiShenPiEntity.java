package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;




import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqGongShiShenPiEntity extends ReqBaseEntity{
	public String SPYHID;
	public String TBYHID="";
	public String TBGSDate="";
	public int pageSize;
	public int pageIndex;
//	SPYHID:审批用户ID 
//		TBYHID:填报用户ID (可为空)
//			TBGSDate:填报工时日期 （可为空）
//			pageSize:页大小 
//pageIndex:当前页为第几页 
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.RUL_GONGSHI_SHENPI;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("SPYHID", SPYHID);
		mMap.put("TBYHID", TBYHID);
		mMap.put("TBGSDate", TBGSDate);
		mMap.put("pageSize", pageSize+"");
		mMap.put("pageIndex", pageIndex+"");
		return mMap;
	}

}
