package com.DCHZ.TYLINCN.http.rsp;

import com.DCHZ.TYLINCN.entity.PHeTongSearchListEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/4.
 */
public class RspHeTongSearchEntity extends RspBaseEntity{
    public PHeTongSearchListEntity mEntity;
    public RspHeTongSearchEntity(JSONObject jsonObj, int seqNo) {
        super(jsonObj, seqNo);
    }

    @Override
    public void parseData(JSONObject jsonObj) {
        mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PHeTongSearchListEntity.class);
    }
}
