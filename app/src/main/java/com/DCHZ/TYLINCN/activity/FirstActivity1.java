package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.YiBanListAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspYiBanListEntity;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

public class FirstActivity1 extends BaseNormalActivity 
		 {
	private final int FLAG_YIBAN = 0x101;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private MsgPage mMsgPage;
	private YiBanListAdapter mAdapterYiban;
	private int pageIndex = 1;
//	private String YHID = "7A42F2C8-6F6B-4EA4-AB66-D81098A68380";
	private String YHID = "";
	private boolean hasNext = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_YIBAN);
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		
		int seq = ProtocalManager.getInstance().getYiBanList(YHID, pageIndex);
		mReqList.add(seq);
		showLoading();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
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
					int page=nextPage();
					int seq = ProtocalManager.getInstance().getYiBanList(YHID,
							page);
					mReqList.add(seq);
					showLoading();
			} else {
				String str = "没有更多数据了！";
				showToast(str);
			}
		};

		@Override
		public void onRefresh(NLPullRefreshView view) {
			// TODO Auto-generated method stub
				mAdapterYiban=null;
				int seq = ProtocalManager.getInstance().getYiBanList(YHID,
						refreshPage());
				mReqList.add(seq);
//			}
		}
	};


	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		 if (eventId == EventCommon.EVENT_YIBAN) {
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if (obj instanceof RspYiBanListEntity) {
					RspYiBanListEntity rsp = (RspYiBanListEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_YIBAN;
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
		case FLAG_YIBAN:
			hideLoadingDialog();
			RspYiBanListEntity rsp1 = (RspYiBanListEntity) msg.obj;
			if (rsp1.isSucc) {
				if (mAdapterYiban == null) {
					mAdapterYiban = new YiBanListAdapter(rsp1.mEntity.daiBan);
					// 设置不显示底部bottom按钮条
					mAdapterYiban
							.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapterYiban);
				} else {
					mAdapterYiban.appendList(rsp1.mEntity.daiBan);

				}
				if (rsp1.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
					hasNext = false;
				}
			} else {
				String str = "网络异常！";
				showToast(str);
			}
			mMsgPage.completeRefresh(rsp1.isSucc);
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
