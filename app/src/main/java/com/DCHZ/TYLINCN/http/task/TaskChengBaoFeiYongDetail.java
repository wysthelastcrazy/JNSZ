package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqChengBaoFeiDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqTouBiaoFeiYongDetailEntiy;
import com.DCHZ.TYLINCN.http.rsp.RspChengBaoFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspTouBiaoFeiYongDetailEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TaskChengBaoFeiYongDetail extends BaseTask<ReqChengBaoFeiDetailEntiy>{
    public TaskChengBaoFeiYongDetail(ReqChengBaoFeiDetailEntiy reqGongZhangJieChuDetailEntiy) {
        super(reqGongZhangJieChuDetailEntiy);
    }
    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
                            int seqNo) {
        // TODO Auto-generated method stub
        RspChengBaoFeiYongDetailEntity rsp=null;
        if(isSucc){
            rsp=new RspChengBaoFeiYongDetailEntity(jsonObj, seqNo);
        }else{
            rsp=new RspChengBaoFeiYongDetailEntity(null, seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_CHENGBAOFEIYONG, seqNo, rsp);
    }
}
