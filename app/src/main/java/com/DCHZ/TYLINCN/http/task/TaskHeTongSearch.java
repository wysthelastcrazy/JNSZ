package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqHeTongSearchEntity;
import com.DCHZ.TYLINCN.http.rsp.RspHeTongSearchEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/4.
 */
public class TaskHeTongSearch extends BaseTask<ReqHeTongSearchEntity>{
    public TaskHeTongSearch(ReqHeTongSearchEntity reqHeTongSearchEntity) {
        super(reqHeTongSearchEntity);
    }

    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode, int seqNo) {
        super.getResponse(jsonObj, isSucc, errorCode, seqNo);
        RspHeTongSearchEntity rsp=null;
        if (isSucc){
            rsp=new RspHeTongSearchEntity(jsonObj,seqNo);
        }else{
            rsp=new RspHeTongSearchEntity(null,seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_HETONG_SEARCH, seqNo, rsp);
    }
}
