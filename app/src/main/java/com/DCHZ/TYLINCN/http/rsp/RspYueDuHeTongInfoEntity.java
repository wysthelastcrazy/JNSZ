package com.DCHZ.TYLINCN.http.rsp;

import com.DCHZ.TYLINCN.entity.PYueDuHeTongInfoEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/28.
 */
public class RspYueDuHeTongInfoEntity extends RspBaseEntity{
    public PYueDuHeTongInfoEntity mEntity;
    public RspYueDuHeTongInfoEntity(JSONObject jsonObj, int seqNo) {
        super(jsonObj, seqNo);
    }

    @Override
    public void parseData(JSONObject jsonObj) {
        mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PYueDuHeTongInfoEntity.class);
    }
}
