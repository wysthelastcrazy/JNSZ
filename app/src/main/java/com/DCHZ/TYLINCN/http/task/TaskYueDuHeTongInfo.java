package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqYueDuHeTongInfoEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYueDuHeTongInfoEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/28.
 */
public class TaskYueDuHeTongInfo extends BaseTask<ReqYueDuHeTongInfoEntity>{
    public TaskYueDuHeTongInfo(ReqYueDuHeTongInfoEntity reqYueDuHeTongInfoEntity) {
        super(reqYueDuHeTongInfoEntity);
    }
    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
                            int seqNo) {
        // TODO Auto-generated method stub
        RspYueDuHeTongInfoEntity rsp=null;
        if(isSucc){
            rsp=new RspYueDuHeTongInfoEntity(jsonObj, seqNo);
        }else{
            rsp=new RspYueDuHeTongInfoEntity(null, seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_YUEDUHETONG_INFO, seqNo, rsp);
    }
}
