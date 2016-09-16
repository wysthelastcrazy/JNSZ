package com.DCHZ.TYLINCN.http.req;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/16.
 */
public class ReqGuDingZiChangDetailEntiy extends ReqBaseEntity{
    public String YWID;
    public String SLID;
    public String LCID;
    public String JDID;
    public String UrlParam;
    public String YHID;
    @Override
    public String getReqUrl() {
        // TODO Auto-generated method stub
        return Common.URL_GUDINGZICHAN_DETAIL;
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
