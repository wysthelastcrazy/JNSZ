package com.DCHZ.TYLINCN.http.task;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqXiangMuSearchEntity;
import com.DCHZ.TYLINCN.http.rsp.RspXiangMuSearchEntity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/4.
 */
public class TaskXiangMuSearch extends BaseTask<ReqXiangMuSearchEntity>{
    public TaskXiangMuSearch(ReqXiangMuSearchEntity reqXiangMuSearchEntity) {
        super(reqXiangMuSearchEntity);
    }

    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode, int seqNo) {
        super.getResponse(jsonObj, isSucc, errorCode, seqNo);
        RspXiangMuSearchEntity rsp=null;
        if (isSucc){
            rsp=new RspXiangMuSearchEntity(jsonObj,seqNo);
        }else{
            rsp=new RspXiangMuSearchEntity(null,seqNo);
        }
        EventController.getInstance().notifyEvent(EventCommon.EVENT_XIANGMU_SEARCH, seqNo, rsp);
    }
}
