package com.DCHZ.TYLINCN.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.BanLiYiJianAdapter;
import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.component.DetailShenPiYiJianTitleView;
import com.DCHZ.TYLINCN.component.HeaderDetailView;
import com.DCHZ.TYLINCN.component.JZADScoreTextView;
import com.DCHZ.TYLINCN.component.ListBottomView;
import com.DCHZ.TYLINCN.component.ListTopItemView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.entity.PBanLiYiJianEntity;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.entity.PDetailBXInfoEntity;
import com.DCHZ.TYLINCN.entity.PDetailJKInfoEntity;
import com.DCHZ.TYLINCN.entity.PDetailQJInfoEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspBanLiYiJianEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspJieKuangDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspQingJiaDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveReturnFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspShenPiInfoEntity;
import com.DCHZ.TYLINCN.listener.IDaiBanClickListener;
import com.DCHZ.TYLINCN.listener.IHeaderClickListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.IntentUtils;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.ParseUtil;

public class QingJiaDetailActivity extends BaseNormalActivity{
	private List<Integer> mReqList = new ArrayList<Integer>();
	private int type;
	private PDaiBanEntity entity;
	private HeaderDetailView mHeader;
	private MsgPage mMsgPage;
	private final int FLAG_SET_LIST=0x100;
	private final int FLAG_SET_TOP=0x101;
	private final int FLAG_TJ=0x102;
	private final int FLAG_HT=0x103;
	private BanLiYiJianAdapter mAdapter;
	private final int GET_TYPE=100;
	private final int GET_JIESHOUREN=101;
	private ArrayList<VJieShouRenEntity> mList;
	private String TJtype;
	private RspQingJiaDetailEntity rsp;
	private ListBottomView mBottomView;
	
	private String mBLUserName;
	private String mBLUserID;
//	private List<String> mStrList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		initExtras();
		initLayout();
		registMsgRecevier(EventCommon.EVENT_QINGJIA_DETAIL);
		registMsgRecevier(EventCommon.EVENT_BANLI_YIJIAN);
		registMsgRecevier(EventCommon.EVENT_SAVE_FLOWBUSINESS);
		registMsgRecevier(EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS);
		//		String LCID=entity.LCID;
//		String YWID=entity.YWID;
		int seq=ProtocalManager.getInstance().getQingJiaDetail(entity);
		mReqList.add(seq);
//		String SLID=entity.SLID;
		showLoading();
	}
	
	private void initExtras(){
		Intent intent=getIntent();
		type=intent.getIntExtra(IntentUtils.KEY_TYPE, 0);
		entity=(PDaiBanEntity) intent.getSerializableExtra(IntentUtils.KEY_ENTITY);
	}
	private void initLayout() {
		// TODO Auto-generated method stub
		
//		showToast("type:"+type+"  entity:"+entity.LCID);
		mHeader=(HeaderDetailView) this.findViewById(R.id.header_detail);
		mHeader.setData(entity);
		mHeader.setClickListener(new IHeaderClickListener() {
			
			@Override
			public void backClick() {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		this.mMsgPage=(MsgPage) this.findViewById(R.id.mMsgPage_detail);
//		mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
		mMsgPage.setEnablePullDown(false);
//		mMsgPage.setRefreshListener(mRefreshListener);
		if(this.type==Common.TYPE_DAIBAN){
			mBottomView=new ListBottomView(this);
			mMsgPage.addFooterView(mBottomView);
			mBottomView.setClickListener(new IDaiBanClickListener() {
				
				@Override
				public void typeClickListener() {
					// TODO Auto-generated method stub
					String noTag=rsp.mEntity.htInfo.get(0).nodeTag;
					IntentUtils.starTiJiaoActivity(QingJiaDetailActivity.this, GET_TYPE,noTag);
				}
				
				@Override
				public void nextClickListener() {
					// TODO Auto-generated method stub
					MyLog.debug(TAG, "[nextClickListener]  mList:"+mList.size());
					IntentUtils.starJieShouRenActivity(QingJiaDetailActivity.this, mList, GET_JIESHOUREN);
				}
				
				@Override
				public void btnClickListener() {
					// TODO Auto-generated method stub
					String BLUserID="";
					String opinion=mBottomView.getOpinion();
					if("同意".equals(TJtype)){
						BLUserID=mList.get(0).TJInfoEntity.nodeID+"$$"+mBLUserID;
						try {
							opinion = URLEncoder.encode(opinion, "GBK");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int seq=ProtocalManager.getInstance().SaveFlowBusiness(entity, BLUserID, opinion);
						mReqList.add(seq);
						showLoading();
					}else{
						BLUserID=mList.get(0).HTInfoEntity.nodeID+"$$"+mBLUserID;
						try {
							opinion = URLEncoder.encode(opinion, "GBK");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int seq=ProtocalManager.getInstance().SaveReturnFlowBusiness(entity, BLUserID, opinion);
						mReqList.add(seq);
						showLoading();
					}
				}

				@Override
				public void noNextListener() {
					// TODO Auto-generated method stub
					showToast("请选择下一环节");
				}
			});
		}
		
		JZADScoreTextView img_banjie=(JZADScoreTextView) findViewById(R.id.img_banjie);
		if("已办结".equals(entity.JianKong)){
			img_banjie.setVisibility(View.VISIBLE);
		}
	}
	
//	private IRefreshListener mRefreshListener=new IRefreshListener() {
//		@Override
//		public void bottomClick(int state) {
//			// TODO Auto-generated method stub
//			super.bottomClick(state);
//		}
//		public void reachListViewBottom() {
//		}
//		public void onRefresh(NLPullRefreshView view) {
//			int seq=ProtocalManager.getInstance().getFeiYongDetail(entity);
//			mReqList.add(seq);
//			String SLID=entity.SLID;
//			int seq1=ProtocalManager.getInstance().getBanLiYiJian(SLID);
//			mReqList.add(seq1);
//		};
//	};
	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if(eventId==EventCommon.EVENT_QINGJIA_DETAIL){
			if(obj instanceof RspQingJiaDetailEntity){
				RspQingJiaDetailEntity rsp=(RspQingJiaDetailEntity) obj;
				Message msg=Message.obtain();
				msg.obj=rsp;
				msg.what=FLAG_SET_TOP;
				sendMsg(msg);
			}
		}
		 if(eventId==EventCommon.EVENT_BANLI_YIJIAN){
			if(obj instanceof RspBanLiYiJianEntity){
				RspBanLiYiJianEntity rsp=(RspBanLiYiJianEntity) obj;
				Message msg=Message.obtain();
				msg.obj=rsp;
				msg.what=FLAG_SET_LIST;
				sendMsg(msg);
			}
		}
		 if(eventId==EventCommon.EVENT_SAVE_FLOWBUSINESS){
			 if(obj instanceof RspSaveFlowBusinessEntity){
				 RspSaveFlowBusinessEntity rsp=(RspSaveFlowBusinessEntity) obj;
				 Message msg=Message.obtain();
					msg.obj=rsp;
					msg.what=FLAG_TJ;
					sendMsg(msg);
			 }
		 }
		 if(eventId==EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS){
			 if(obj instanceof RspSaveReturnFlowBusinessEntity){
				 RspSaveReturnFlowBusinessEntity rsp=(RspSaveReturnFlowBusinessEntity) obj;
				 Message msg=Message.obtain();
					msg.obj=rsp;
					msg.what=FLAG_HT;
					sendMsg(msg);
			 }
		 }
	}
	
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		int what=msg.what;
		switch (what) {
		case FLAG_SET_TOP:
			 rsp=(RspQingJiaDetailEntity)msg. obj;
			if(rsp!=null&&rsp.isSucc){
//				List<PShenPiInfoEntity> mList=rsp.mEntity.BXMXInfo;
				ArrayList<PDetailQJInfoEntity> bxInfo=rsp.mEntity.qingJiaInfo;
				if(bxInfo!=null&&bxInfo.size()>0)
				mHeader.setItem(bxInfo.get(0).BMName);
				View view=getHeaderView(bxInfo);
				mMsgPage.addHeaderView(view);
				int seq1=ProtocalManager.getInstance().getBanLiYiJian(entity.SLID);
				mReqList.add(seq1);
			}
			break;
		case FLAG_SET_LIST:
			hideLoadingDialog();
			RspBanLiYiJianEntity rsp1=(RspBanLiYiJianEntity) msg.obj;
			if(rsp1!=null&&rsp1.isSucc){
				ArrayList<PBanLiYiJianEntity> mList=rsp1.mEntity.banLiYiJian;
				MyLog.debug(TAG, "[handleMsg]  mList :"+mList.size());
				if(mAdapter==null){
					mAdapter=new BanLiYiJianAdapter(rsp1.mEntity.banLiYiJian);
					mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapter);
				}else{
					mAdapter.reSetList(rsp1.mEntity.banLiYiJian);
				}
			}
			mMsgPage.completeRefresh(rsp1.isSucc);
			break;
		case FLAG_TJ:
			hideLoadingDialog();
			RspSaveFlowBusinessEntity rsp=(RspSaveFlowBusinessEntity) msg.obj;
			if(rsp!=null&&rsp.isSucc){
				showToast("请求成功！");
				IntentUtils.startFirstActivity(this);
				finish();
			}else{
				showToast("请求失败！");
			}
			break;
		case FLAG_HT:
			hideLoadingDialog();
			RspSaveReturnFlowBusinessEntity rsp2=(RspSaveReturnFlowBusinessEntity) msg.obj;
			if(rsp2!=null&&rsp2.isSucc){
				showToast("请求成功！");
				IntentUtils.startMainActivity(this);
				finish();
			}else{
				showToast("请求失败！");
			}
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		MyLog.debug(TAG, "[onActivityResult] resultCode:"+resultCode);
		// TODO Auto-generated method stub
		if(resultCode!=Activity.RESULT_OK){
			return;
		}
		if(requestCode==GET_TYPE){
			TJtype=data.getStringExtra("type");
			mBottomView.setType(TJtype);
			if("同意".equals(TJtype)){
				mList=ParseUtil.getJieShouRen1(rsp.mEntity.tjInfo);
			}else{
				mList=ParseUtil.getJieShouRen(rsp.mEntity.htInfo);
			}
			MyLog.debug(TAG, "[onActivityResult]  mList:"+mList.size());
			showToast(TJtype);
		}
		if(requestCode==GET_JIESHOUREN){
			mBLUserName=data.getStringExtra("name");
			mBLUserID=data.getStringExtra("id");
			mBottomView.setname(mBLUserName);
			showToast(mBLUserName);
		}
	}
	private View getHeaderView(ArrayList<PDetailQJInfoEntity> bxInfo){
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		if(bxInfo!=null&&bxInfo.size()>0){
			for(int i=0;i<bxInfo.size();i++){
				ArrayList<String> mList=new ArrayList<String>();
				ListTopItemView topView=new ListTopItemView(this);
				mList.add(bxInfo.get(i).QJXiangQing);
				mList.add(bxInfo.get(i).QJKaiShiRiQi.split(" ")[0]+"至"+bxInfo.get(i).QJJieShuRIQi.split(" ")[0]);
				mList.add(bxInfo.get(i).QJXiangMuQingKuang);
				topView.setData(entity.LCID, mList);
//				if(i==bxInfo.size()-1){
//					topView.showBottom();
//				}
				layout.addView(topView);
			}
			DetailShenPiYiJianTitleView view=new DetailShenPiYiJianTitleView(QingJiaDetailActivity.this);
			layout.addView(view);
		}
		return layout;
	}
}
