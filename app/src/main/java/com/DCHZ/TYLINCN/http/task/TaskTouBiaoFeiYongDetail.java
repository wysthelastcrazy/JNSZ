package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGongZhangJieChuDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqTouBiaoFeiYongDetailEntiy;
import com.DCHZ.TYLINCN.http.rsp.RspGongZhangJieChuDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspTouBiaoFeiYongDetailEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TaskTouBiaoFeiYongDetail extends BaseTask<ReqTouBiaoFeiYongDetailEntiy>{
    public TaskTouBiaoFeiYongDetail(ReqTouBiaoFeiYongDetailEntiy reqGongZhangJieChuDetailEntiy) {
        super(reqGongZhangJieChuDetailEntiy);
    }
    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
                            int seqNo) {
        // TODO Auto-generated method stub
        RspTouBiaoFeiYongDetailEntity rsp=null;
        if(isSucc){
            rsp=new RspTouBiaoFeiYongDetailEntity(jsonObj, seqNo);
        }else{
            rsp=new RspTouBiaoFeiYongDetailEntity(null, seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_TOUBIAOFEIYONG, seqNo, rsp);
    }
}
