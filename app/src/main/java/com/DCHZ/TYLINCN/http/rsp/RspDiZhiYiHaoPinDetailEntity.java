package com.DCHZ.TYLINCN.http.rsp;

import com.DCHZ.TYLINCN.entity.PDiZhiYiHaoPinDetailENtity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;
import com.DCHZ.TYLINCN.util.MyLog;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/16.
 */
public class RspDiZhiYiHaoPinDetailEntity extends RspBaseEntity{
    public PDiZhiYiHaoPinDetailENtity mEntity;
    public RspDiZhiYiHaoPinDetailEntity(JSONObject jsonObj, int seqNo) {
        super(jsonObj, seqNo);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void parseData(JSONObject jsonObj) {
        // TODO Auto-generated method stub
        MyLog.debug(TAG, "[parseData] jsonObj:" + jsonObj.toString());
        mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PDiZhiYiHaoPinDetailENtity.class);
    }
}
