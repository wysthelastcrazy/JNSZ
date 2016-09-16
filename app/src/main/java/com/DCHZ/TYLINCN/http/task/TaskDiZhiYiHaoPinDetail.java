package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqDiZhiYiHaoPinDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqGuDingZiChangDetailEntiy;
import com.DCHZ.TYLINCN.http.rsp.RspDiZhiYiHaoPinDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGuDingZiChanDetailEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TaskDiZhiYiHaoPinDetail extends BaseTask<ReqDiZhiYiHaoPinDetailEntiy>{
    public TaskDiZhiYiHaoPinDetail(ReqDiZhiYiHaoPinDetailEntiy reqGongZhangJieChuDetailEntiy) {
        super(reqGongZhangJieChuDetailEntiy);
    }
    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
                            int seqNo) {
        // TODO Auto-generated method stub
        RspDiZhiYiHaoPinDetailEntity rsp=null;
        if(isSucc){
            rsp=new RspDiZhiYiHaoPinDetailEntity(jsonObj, seqNo);
        }else{
            rsp=new RspDiZhiYiHaoPinDetailEntity(null, seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_DIZHIYIHAOPIN, seqNo, rsp);
    }
}
