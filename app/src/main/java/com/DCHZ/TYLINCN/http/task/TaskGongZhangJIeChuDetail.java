package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGongZhangJieChuDetailEntiy;
import com.DCHZ.TYLINCN.http.rsp.RspGongZhangJieChuDetailEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TaskGongZhangJIeChuDetail extends BaseTask<ReqGongZhangJieChuDetailEntiy>{
    public TaskGongZhangJIeChuDetail(ReqGongZhangJieChuDetailEntiy reqGongZhangJieChuDetailEntiy) {
        super(reqGongZhangJieChuDetailEntiy);
    }
    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
                            int seqNo) {
        // TODO Auto-generated method stub
        RspGongZhangJieChuDetailEntity rsp=null;
        if(isSucc){
            rsp=new RspGongZhangJieChuDetailEntity(jsonObj, seqNo);
        }else{
            rsp=new RspGongZhangJieChuDetailEntity(null, seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_GONGZHANGJIECHU_DETAIL, seqNo, rsp);
    }
}
