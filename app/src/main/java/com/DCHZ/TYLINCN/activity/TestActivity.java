package com.DCHZ.TYLINCN.activity;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanListEntity;
import com.DCHZ.TYLINCN.http.rsp.RspLoginEntity;
import com.DCHZ.TYLINCN.http.rsp.RspTest;
import com.DCHZ.TYLINCN.util.MyLog;

import android.os.Bundle;
import android.os.Message;

public class TestActivity extends BaseNormalActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
//		registMsgRecevier(EventCommon.EVENT_FIRST);
//		ProtocalManager.getInstance().getTest();
//		registMsgRecevier(EventCommon.EVENT_DAIBAN);
		ProtocalManager.getInstance().getDaiBanList("7A42F2C8-6F6B-4EA4-AB66-D81098A68380", 1);
//		registMsgRecevier(EventCommon.EVENT_LOGIN);
//		ProtocalManager.getInstance().getLogin("yangjing", "");
		
	}

	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stubs
		if(obj instanceof RspLoginEntity){
			RspLoginEntity rsp=(RspLoginEntity) obj;
			Message msg=Message.obtain();
			if(rsp.isSucc){
				msg.what=1;
				msg.obj=rsp;
			}else{
				msg.what=2;
				msg.obj="失败";
			}
			sendMsg(msg);
		}
	}
	
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		int what=msg.what;
		MyLog.debug(TAG, "[handleMsg] userName:"+what);
		switch (what) {
		case 1:
			RspLoginEntity rsp=(RspLoginEntity) msg.obj;
			showToast(rsp.mEntity.BMName);
//			MyLog.debug(TAG, "[handleMsg] userName:"+rsp.mEntity.Login);
			break;
		case 2:
			String str=(String) msg.obj;
			showToast(str);
			MyLog.debug(TAG, "[handleMsg] userName:"+str);
			break;
		default:
			break;
		}
	}
}
