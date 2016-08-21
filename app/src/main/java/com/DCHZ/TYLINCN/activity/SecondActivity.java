package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.GongShiShenPiAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.GongShiHeaderView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.component.GongShiHeaderView.BtnClickListener;
import com.DCHZ.TYLINCN.entity.PGongShiItemEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanListEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGSShenPiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGSTuiHuiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongShiShenPiEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;
import com.DCHZ.TYLINCN.util.StrUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends BaseNormalActivity implements
		OnClickListener {
	private final int FLAG_SET_LSIT = 0x100;
	private final int FLAG_GS_SHENPI=0x101;
	private final int FLAG_GS_TUIHUI=0x102;
	private ArrayList<PGongShiItemEntity> mList;
	private boolean isAllSelected;
	private GongShiHeaderView mHeaderView;
	private MsgPage mMsgPage;
	private GongShiShenPiAdapter mAdapter;

//	private String YHID = "028F5499-A43E-49FA-8980-677665CAECAC";
	private String YHID;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private int pageIndex = 1;
	private boolean hasNext = true;
	private Button btn_cancel;
	private Button btn_ok;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_GONGSHI_SHENPI);
		registMsgRecevier(EventCommon.EVEN_GSSHENPI);
		registMsgRecevier(EventCommon.EVENT_GSTUIHUI);
		
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		int seq = ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
				pageIndex);
		mReqList.add(seq);
		showLoading();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		
		mList=new ArrayList<PGongShiItemEntity>();
		mHeaderView = (GongShiHeaderView) this.findViewById(R.id.gongshiHeader);
		mHeaderView.setBtnClickListener(new BtnClickListener() {
			@Override
			public void btnRightClick() {
				// TODO Auto-generated method stub
				showToast(getSelecedItem());
				if (isAllSelected) {
					for (int i = 0; i < mList.size(); i++) {
						mAdapter.getIsSelected().put(i, false);
					}
					mHeaderView.setRight("全选");
				} else {
					for (int i = 0; i < mList.size(); i++) {
						mAdapter.getIsSelected().put(i, true);
					}
					mHeaderView.setRight("取消");
				}
				dataChanged();
				isAllSelected = !isAllSelected;
			}
		});
		mMsgPage = (MsgPage) findViewById(R.id.mMsgPage);
		mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
		mMsgPage.setEnablePullDown(false);
//		mMsgPage.setRefreshListener(mRefreshListener);
//		mMsgPage.setEnablePullDown(false);
		
		btn_ok=(Button) this.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		btn_cancel=(Button) this.findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(this);
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
			int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
					refreshPage());
				mReqList.add(seq);
		};
	};
	private IGongShiShenPiClickListener mListener = new IGongShiShenPiClickListener() {

		@Override
		public void selectedListener(int pos) {
			// TODO Auto-generated method stub
			mAdapter.getIsSelected().put(pos, true);
		}

		@Override
		public void cancelSelectListener(int pos) {
			// TODO Auto-generated method stub
			mAdapter.getIsSelected().put(pos, false);
		}
	};

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==btn_ok){
			int seq=ProtocalManager.getInstance().GSShenPi(getSelecedItem(), YHID);
			mReqList.add(seq);
			mAdapter=null;
			showLoading();
		}else if(view==btn_cancel){
			AlertDialog.Builder builder=new Builder(this);
			builder.setTitle("提示");
			builder.setMessage("请确定是否退回工时?");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					int seq=ProtocalManager.getInstance().GSTuiHui(getSelecedItem());
					mReqList.add(seq);
					mAdapter=null;
					showLoading();
					dialog.dismiss();
				}
			});
			builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
				
			});
			 builder.create().show();
		}
	}

	private String getSelecedItem() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < mList.size(); i++) {
			if (mAdapter.getIsSelected().get(i)) {
				list.add(mList.get(i).GSID);
			}
		}
		return StrUtil.getString(list);
	}


	private void dataChanged() {
		// 通知listView刷新
		mAdapter.notifyDataSetChanged();
		// TextView显示最新的选中数目
	}

	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if (eventId == EventCommon.EVENT_GONGSHI_SHENPI) {
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if (obj instanceof RspGongShiShenPiEntity) {
					RspGongShiShenPiEntity rsp = (RspGongShiShenPiEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_SET_LSIT;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		}else if(eventId == EventCommon.EVEN_GSSHENPI){
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if(obj instanceof RspGSShenPiEntity){
					RspGSShenPiEntity rsp=(RspGSShenPiEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_GS_SHENPI;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		}else if(eventId == EventCommon.EVENT_GSTUIHUI){
			if (mReqList.remove(Integer.valueOf(seqNo))) {
					RspGSTuiHuiEntity rsp=(RspGSTuiHuiEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_GS_TUIHUI;
					msg.obj = rsp;
					sendMsg(msg);
			}
		}
	}
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		int what=msg.what;
		switch (what) {
		case FLAG_SET_LSIT:
			hideLoadingDialog();
			RspGongShiShenPiEntity rsp = (RspGongShiShenPiEntity) msg.obj;
			if(rsp!=null&&rsp.isSucc){
				if(mAdapter==null){
					mAdapter=new GongShiShenPiAdapter(rsp.mEntity.loadGongShiShenPiInfo);
					mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mAdapter.setLisntener(mListener);
					mMsgPage.setListAdapter(mAdapter);
					mList=rsp.mEntity.loadGongShiShenPiInfo;
				}else{
					mAdapter.appendList(rsp.mEntity.loadGongShiShenPiInfo);
					mList.addAll(rsp.mEntity.loadGongShiShenPiInfo);
				}
				if(rsp.mEntity.loadGongShiShenPiInfo.size()<MConfiger.PAGE_SIZE){
					hasNext=false;
				}
			}else{
				String str = "网络异常！";
				showToast(str);
			}
			mMsgPage.completeRefresh(rsp.isSucc);
			break;
		case FLAG_GS_SHENPI:
			hideLoadingDialog();
			RspGSShenPiEntity rsp1=(RspGSShenPiEntity) msg.obj;
			if(rsp1!=null&&rsp1.isSucc){
				int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
						refreshPage());
					mReqList.add(seq);
					showLoading();
			}
			break;
		case FLAG_GS_TUIHUI:
			hideLoadingDialog();
			RspGSTuiHuiEntity rsp2=(RspGSTuiHuiEntity) msg.obj;
			if(rsp2!=null&&rsp2.isSucc){
				int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
						refreshPage());
					mReqList.add(seq);
					showLoading();
			}
			break;
		default:
			break;
		}
	}
	private int nextPage() {
		pageIndex = pageIndex + 1;
		return pageIndex;
	}

	private int refreshPage() {
		pageIndex = 1;
		return pageIndex;
	}
}
