package com.DCHZ.TYLINCN.http.req;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/28.
 */
public class ReqYueDuHeTongInfoEntity extends ReqBaseEntity{
    public String NianValue;

    @Override
    public String getReqUrl() {
        return Common.URL_YUEDU_HETONG_INFO;
    }

    @Override
    public Map<String, String> getReqData() {
        Map<String,String> mMap=new HashMap<String, String>();
        mMap.put("NianValue", NianValue);
        return mMap;
    }
}
