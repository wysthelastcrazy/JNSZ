package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.DaiBanListAdapter;
import com.DCHZ.TYLINCN.adapter.YiBanListAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanListEntity;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

public class FirstActivity extends BaseNormalActivity 
		 {
	private final int FLAG_DAIBAN = 0x100;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private MsgPage mMsgPage;
	private DaiBanListAdapter mAdapterDaiBan;
	private int pageIndex = 1;
//	private String YHID = "7A42F2C8-6F6B-4EA4-AB66-D81098A68380";
	private String YHID = "";
	private boolean hasNext = true;
	private TextView textView_daiBan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_DAIBAN);
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		
		int seq = ProtocalManager.getInstance().getDaiBanList(YHID, pageIndex);
		mReqList.add(seq);
		showLoading();
	}

	private void initLayout() {
		// TODO Auto-generated method stub

		textView_daiBan=(TextView) findViewById(R.id.textView_daiBan);
		mMsgPage = (MsgPage) findViewById(R.id.first_msgPage);
		this.mMsgPage.setRefreshListener(mRefreshListener);
		this.mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
	}

	// mMsgpage的监听事件，包括下拉刷新和点击加载更多
	private IRefreshListener mRefreshListener = new IRefreshListener() {
		@Override
		public void bottomClick(int state) {
		}

		public void reachListViewBottom() {
			if (hasNext) {
					int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
							nextPage());
					mReqList.add(seq);
			} else {
				String str = "没有更多数据了！";
				showToast(str);
			}
		};

		@Override
		public void onRefresh(NLPullRefreshView view) {
			// TODO Auto-generated method stub
				mAdapterDaiBan=null;
				int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
						refreshPage());
				mReqList.add(seq);
		}
	};


	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if (eventId == EventCommon.EVENT_DAIBAN) {
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if (obj instanceof RspDaiBanListEntity) {
					RspDaiBanListEntity rsp = (RspDaiBanListEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_DAIBAN;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		}
	}

	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		super.handleMsg(msg);
		int what = msg.what;
		switch (what) {
		case FLAG_DAIBAN:
			hideLoadingDialog();
			RspDaiBanListEntity rsp = (RspDaiBanListEntity) msg.obj;
			if (rsp.isSucc) {
				if (mAdapterDaiBan == null) {
					mAdapterDaiBan = new DaiBanListAdapter(rsp.mEntity.daiBan);
					// 设置不显示底部bottom按钮条
					mAdapterDaiBan
							.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapterDaiBan);
				} else {
					mAdapterDaiBan.appendList(rsp.mEntity.daiBan);
				}
				if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
					hasNext = false;
				}
			} else {
				String str = "网络异常！";
				showToast(str);
			}
			mMsgPage.completeRefresh(rsp.isSucc);
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
