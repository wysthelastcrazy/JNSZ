package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

/**
 * 获取办理意见信息请求类
 * @author wys
 *
 */
public class ReqBaiLiYiJianEntity extends ReqBaseEntity{
	public String SLID;//实例id
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_BANLIYIJIAN;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("SLID", SLID);
		return mMap;
	}

}
