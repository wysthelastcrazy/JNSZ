package com.DCHZ.TYLINCN.activity;


import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.ThirdListAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.component.BaoBiaoListTopView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.entity.VThirdItemEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspJiDuHeTongEntity;
import com.DCHZ.TYLINCN.http.rsp.RspJiDuShouKuanEntity;
import com.DCHZ.TYLINCN.listener.IBaoBiaoClickListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.ParseUtil;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;
import com.common.util.DateUtil;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ThreeActivity extends BaseNormalActivity implements OnClickListener{
	private final int TYPE_HETONG=1;
	private final int TYPE_SHOUKUAN=2;
	private final int FLAG_SET_HETONG=0x100;
	private final int FLAG_SET_SHOUKUAN=0x101;
	private int mType=TYPE_HETONG;
	private TextView textView_heTong;
	private TextView textView_shouKuan;
	private MsgPage mMsgPage;
	private ThirdListAdapter mAdapter;
	private BaoBiaoListTopView topView;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private String year;
	private String LeaderRole;
	private String IsBMLeader;
	private String BMID;
	private boolean hasBottom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_JIDU_HETONG);
		registMsgRecevier(EventCommon.EVENT_JIDU_SHOUKUAN);
		year=DateUtil.getDate();
		int seq=ProtocalManager.getInstance().getJiDuHeTongInfo(year);
		mReqList.add(seq);
		showLoading();
		String[] strs=year.split("-");
		topView.setInfo(strs[0]+"合同信息查询");
		
		LeaderRole=SharePreLoginUtil.loadLoginInfo().LeaderRole;
		IsBMLeader=SharePreLoginUtil.loadLoginInfo().IsBMLeader;
		BMID=SharePreLoginUtil.loadLoginInfo().BMID;
	}
	private void initLayout() {
		// TODO Auto-generated method stub
		this.textView_heTong=(TextView) this.findViewById(R.id.textView_daiBan);
		this.textView_heTong.setOnClickListener(this);
		this.textView_heTong.setEnabled(false);
		this.textView_shouKuan=(TextView) this.findViewById(R.id.textView_yiBan);
		this.textView_shouKuan.setOnClickListener(this);
		
		topView=(BaoBiaoListTopView) this.findViewById(R.id.topView);
		topView.setClickListener(new IBaoBiaoClickListener() {
			
			@Override
			public void thistYearClickListener() {
				// TODO Auto-generated method stub
				year=DateUtil.getDate();
				int seq=ProtocalManager.getInstance().getJiDuHeTongInfo(year);
				mReqList.add(seq);
				showLoading();
			}
			
			@Override
			public void nextYearClickListener() {
				// TODO Auto-generated method stub
				year=DateUtil.changeYear(year, DateUtil.TYPE_NEXT);
				int seq=ProtocalManager.getInstance().getJiDuHeTongInfo(year);
				mReqList.add(seq);
				showLoading();
			}
			
			@Override
			public void lastYearClickListener() {
				// TODO Auto-generated method stub
				year=DateUtil.changeYear(year, DateUtil.TYPE_LAST);
				int seq=ProtocalManager.getInstance().getJiDuHeTongInfo(year);
				mReqList.add(seq);
				showLoading();
			}
		});
		
		this.mMsgPage=(MsgPage) findViewById(R.id.mMsgPage_three);
//		this.mMsgPage.setEmpty(ListViewEmptyView.TYPE_ENROLL);
		this.mMsgPage.setRefreshListener(mRefreshListener);
		mMsgPage.setEnablePullDown(false);
	}
	
	private IRefreshListener mRefreshListener=new IRefreshListener() {
		@Override
		public void bottomClick(int state) {
			// TODO Auto-generated method stub
			super.bottomClick(state);
		}
		public void reachListViewBottom() {
//			if (hasNext) {
//				int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
//						nextPage());
//					mReqList.add(seq);
//					showLoading();
//			} else {
//				String str = "没有更多数据了！";
//				showToast(str);
//			}
		}
		public void onRefresh(NLPullRefreshView view) {
			mAdapter=null;
			if(mType==TYPE_HETONG){
				int seq=ProtocalManager.getInstance().getJiDuHeTongInfo(year);
				mReqList.add(seq);
			}else if(mType==TYPE_SHOUKUAN){
				int seq=ProtocalManager.getInstance().getJiDuShouKuanInfo(year);
				mReqList.add(seq);
			}
		};
	};
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==this.textView_heTong){
			textView_shouKuan.setEnabled(true);
			textView_heTong.setEnabled(false);
			int seq=ProtocalManager.getInstance().getJiDuHeTongInfo(year);
			mReqList.add(seq);
			showLoading();
			mType=TYPE_HETONG;
		}else if(view==this.textView_shouKuan){
			textView_shouKuan.setEnabled(false);
			textView_heTong.setEnabled(true);
			int seq=ProtocalManager.getInstance().getJiDuShouKuanInfo(year);
			mReqList.add(seq);
			showLoading();
			mType=TYPE_SHOUKUAN;
		}
	}
	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if(eventId==EventCommon.EVENT_JIDU_HETONG){
			if(mReqList.remove(Integer.valueOf(seqNo))){
				RspJiDuHeTongEntity rsp=(RspJiDuHeTongEntity) obj;
				Message msg = Message.obtain();
				msg.what = FLAG_SET_HETONG;
				msg.obj = rsp;
				sendMsg(msg);
			}
		}else if(eventId==EventCommon.EVENT_JIDU_SHOUKUAN){
			if(mReqList.remove(Integer.valueOf(seqNo))){
				RspJiDuShouKuanEntity rsp=(RspJiDuShouKuanEntity) obj;
				Message msg = Message.obtain();
				msg.what = FLAG_SET_SHOUKUAN;
				msg.obj = rsp;
				sendMsg(msg);
			}
		}
	}
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		String[] strs=year.split("-");
		if(mType==TYPE_HETONG){
			topView.setInfo(strs[0]+"合同信息查询");
		}else{
			topView.setInfo(strs[0]+"收款信息查询");
		}
		int what=msg.what;
		switch (what) {
		case FLAG_SET_HETONG:
			hideLoadingDialog();
			if(!hasBottom){
				mMsgPage.setEmpty(ListViewEmptyView.TYPE_ENROLL);
				hasBottom=true;
			}
			RspJiDuHeTongEntity rsp=(RspJiDuHeTongEntity) msg.obj;
			if(rsp!=null&&rsp.isSucc){
				ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
				if("False".equals(LeaderRole)){
					if("True".equals(IsBMLeader)){
						 mList=ParseUtil.getThirdList(rsp.mEntity.JiDuHeTongInfo,BMID);
					}
				}else{
					 mList=ParseUtil.getThirdList(rsp.mEntity.JiDuHeTongInfo);
				}
				
//				showToast("mList:"+rsp.mEntity.JiDuShouKuanInfo.size());
				 if(mAdapter==null){
					mAdapter=new ThirdListAdapter(mList);
					mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapter);
				}else{
					mAdapter.reSetList(mList);
				}
			}else{
				showToast("网络异常");
			}
//			mMsgPage.completeRefresh(rsp.isSucc);
			break;
		case FLAG_SET_SHOUKUAN:
			hideLoadingDialog();
			if(!hasBottom){
				mMsgPage.setEmpty(ListViewEmptyView.TYPE_ENROLL);
				hasBottom=true;
			}
			RspJiDuShouKuanEntity rsp1=(RspJiDuShouKuanEntity) msg.obj;
			if(rsp1!=null&&rsp1.isSucc){
				ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
				if("False".equals(LeaderRole)){
					if("True".equals(IsBMLeader)){
						 mList=ParseUtil.getThirdList1(rsp1.mEntity.JiDuShouKuanInfo,BMID);
					}
				}else{
					 mList=ParseUtil.getThirdList1(rsp1.mEntity.JiDuShouKuanInfo);
				}
				 if(mAdapter==null){
						mAdapter=new ThirdListAdapter(mList);
						mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
						mMsgPage.setListAdapter(mAdapter);
						mMsgPage.setEmpty(ListViewEmptyView.TYPE_ENROLL);
					}else{
						mAdapter.reSetList(mList);
					}
				}else{
					showToast("网络异常");
				}
//			mMsgPage.completeRefresh(rsp1.isSucc);
			break;
		default:
			break;
		}
	}
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
