package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqYueDuHeTongInfoEntity;
import com.DCHZ.TYLINCN.http.req.ReqYueDuShouKuanInfoEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYueDuHeTongInfoEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYueDuShouKuanInfoEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/28.
 */
public class TaskYueDuShouKuanInfo extends BaseTask<ReqYueDuShouKuanInfoEntity>{
    public TaskYueDuShouKuanInfo(ReqYueDuShouKuanInfoEntity reqYueDuHeTongInfoEntity) {
        super(reqYueDuHeTongInfoEntity);
    }
    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
                            int seqNo) {
        // TODO Auto-generated method stub
        RspYueDuShouKuanInfoEntity rsp=null;
        if(isSucc){
            rsp=new RspYueDuShouKuanInfoEntity(jsonObj, seqNo);
        }else{
            rsp=new RspYueDuShouKuanInfoEntity(null, seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_YUEDUSHOUKUAN_INFO, seqNo, rsp);
    }
}
