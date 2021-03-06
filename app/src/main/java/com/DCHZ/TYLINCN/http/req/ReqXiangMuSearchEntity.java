package com.DCHZ.TYLINCN.http.req;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;
import com.DCHZ.TYLINCN.util.MyLog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
public class ReqXiangMuSearchEntity extends ReqBaseEntity{
    public int pageSize=30;
    public int pageIndex;
    public String strWhere;
    public String strYHID;
    @Override
    public String getReqUrl() {
        return Common.URL_XIANGMU_SEARCH;
    }

    @Override
    public Map<String, String> getReqData() {
        Map<String,String> mMap=new HashMap<String, String>();
        mMap.put("pageSize",pageSize+"");
        mMap.put("pageIndex",pageIndex+"");
        try {
            strWhere= URLEncoder.encode(strWhere, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mMap.put("strWhere",strWhere);
        mMap.put("strYHID", strYHID);
        return mMap;
    }
}
