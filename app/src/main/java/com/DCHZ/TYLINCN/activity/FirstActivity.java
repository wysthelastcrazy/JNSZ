package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.DaiBanListAdapter;
import com.DCHZ.TYLINCN.adapter.YiBanListAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.HeaderSearchView;
import com.DCHZ.TYLINCN.component.HeaderSelectView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanListEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYiBanListEntity;
import com.DCHZ.TYLINCN.listener.IHeaderSelecterTabClickListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends BaseNormalActivity 
		 {
	private final int FLAG_DAIBAN = 0x100;
	private final int FLAG_YIBAN = 0x101;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private MsgPage mMsgPage;
	private DaiBanListAdapter mAdapterDaiBan;
			 private YiBanListAdapter mAdapterYiban;
	private int pageIndexLeft = 1;
	private int pageIndexRight=1;
//	private String YHID = "7A42F2C8-6F6B-4EA4-AB66-D81098A68380";
	private String YHID = "";
	private boolean hasNext = true;
//	private TextView textView_daiBan;
	private HeaderSelectView mHeader;
	private int mType = TYPE_DAIBAN;
	private static final int TYPE_DAIBAN = 1;
	private static final int TYPE_YIBAN = 2;
	private boolean isRefresh;
	private HeaderSearchView mSearchViewLfet;
	private HeaderSearchView mSearchViewRifht;
	private String strWhereYiBan="";
	private String strWhereDaiBan="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_DAIBAN);
		registMsgRecevier(EventCommon.EVENT_YIBAN);
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		
		int seq = ProtocalManager.getInstance().getDaiBanList(YHID, pageIndexLeft,strWhereDaiBan);
		mReqList.add(seq);
		showLoading();
	}

	private void initLayout() {
		// TODO Auto-generated method stub

		mHeader= (HeaderSelectView) findViewById(R.id.header);
		mHeader.setTabSelectClickListener(mTabSelecterListener);
		mHeader.setTabTitle("待办事务", "已办事务");
		mMsgPage = (MsgPage) findViewById(R.id.first_msgPage);
		this.mMsgPage.setRefreshListener(mRefreshListener);
		this.mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
		mSearchViewLfet= (HeaderSearchView) this.findViewById(R.id.header_search);
		mSearchViewLfet.setHint("申请人/事务标题/事务类型");
		mSearchViewLfet.addTextChangeListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				strWhereDaiBan = editable.toString();
				int seq = ProtocalManager.getInstance().getDaiBanList(YHID, refreshPage(), strWhereDaiBan);
				mReqList.add(seq);
			}
		});
		mSearchViewRifht= (HeaderSearchView) this.findViewById(R.id.header_search_right);
		mSearchViewRifht.setHint("申请人/事务标题/事务类型");
		mSearchViewRifht.addTextChangeListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				strWhereYiBan=editable.toString();
				int seq = ProtocalManager.getInstance().getYiBanList(YHID, refreshPage(), strWhereYiBan);
				mReqList.add(seq);
			}
		});
		mSearchViewLfet.setVisibility(View.VISIBLE);
		mSearchViewRifht.setVisibility(View.INVISIBLE);
	}
	private IHeaderSelecterTabClickListener mTabSelecterListener=new IHeaderSelecterTabClickListener() {
		@Override
		public void leftTabClick() {
			mType=TYPE_DAIBAN;
			if (mAdapterDaiBan==null){
				int seq = ProtocalManager.getInstance().getDaiBanList(YHID, 1,strWhereDaiBan);
				mReqList.add(seq);
				showLoading();
			}else{
				mMsgPage.setListAdapter(mAdapterDaiBan);
			}
			mSearchViewLfet.setVisibility(View.VISIBLE);
			mSearchViewRifht.setVisibility(View.INVISIBLE);
		}

		@Override
		public void rightTabClick() {
			mType=TYPE_YIBAN;
			if (mAdapterYiban==null){
				int seq = ProtocalManager.getInstance().getYiBanList(YHID, 1,strWhereYiBan);
				mReqList.add(seq);
				showLoading();
			}else{
				mMsgPage.setListAdapter(mAdapterYiban);
			}
			mSearchViewLfet.setVisibility(View.INVISIBLE);
			mSearchViewRifht.setVisibility(View.VISIBLE);
		}
	};
	// mMsgpage的监听事件，包括下拉刷新和点击加载更多
	private IRefreshListener mRefreshListener = new IRefreshListener() {
		@Override
		public void reachListViewBottom() {
			if (hasNext) {
				if (mType == TYPE_DAIBAN) {
					int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
							nextPage(),strWhereDaiBan);
					mReqList.add(seq);
				} else if (mType == TYPE_YIBAN) {
					int page=nextPage();
					int seq = ProtocalManager.getInstance().getYiBanList(YHID,
							page,strWhereYiBan);
					mReqList.add(seq);
				}
			} else {
				String str = "没有更多数据了！";
				showToast(str);
			}
		};

		@Override
		public void onRefresh(NLPullRefreshView view) {
			// TODO Auto-generated method stub
			isRefresh=true;
			if (mType == TYPE_DAIBAN) {
				mAdapterDaiBan=null;
				int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
						refreshPage(),strWhereDaiBan);
				mReqList.add(seq);
			} else if (mType == TYPE_YIBAN) {
				mAdapterYiban=null;
				int seq = ProtocalManager.getInstance().getYiBanList(YHID,
						refreshPage(),strWhereYiBan);
				mReqList.add(seq);
			}
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
		}else if (eventId == EventCommon.EVENT_YIBAN) {
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
		case FLAG_DAIBAN:
			hideLoadingDialog();
			RspDaiBanListEntity rsp = (RspDaiBanListEntity) msg.obj;
			if (isRefresh){
				mMsgPage.completeRefresh(rsp.isSucc);
				isRefresh=false;
			}
			if (rsp.isSucc) {
				if (mAdapterDaiBan == null) {
					mAdapterDaiBan = new DaiBanListAdapter(rsp.mEntity.daiBan);
					// 设置不显示底部bottom按钮条
					mAdapterDaiBan
							.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapterDaiBan);
				} else {
					if (pageIndexLeft==1){
						mAdapterDaiBan.reSetList(rsp.mEntity.daiBan);
					}else {
						mAdapterDaiBan.appendList(rsp.mEntity.daiBan);
					}
				}
				if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
					hasNext = false;
				}
			} else {
				String str = "网络异常！";
				showToast(str);
			}
			break;
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
						if (pageIndexRight==1){
							mAdapterYiban.reSetList(rsp1.mEntity.daiBan);
						}else {
							mAdapterYiban.appendList(rsp1.mEntity.daiBan);
						}

					}
					if (rsp1.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
						hasNext = false;
					}
				} else {
					String str = "网络异常！";
					showToast(str);
				}
				if (isRefresh){
					mMsgPage.completeRefresh(rsp1.isSucc);
					isRefresh=false;
				}
				break;
		default:
			break;
		}
	}

	private int nextPage() {
		if (mType==TYPE_DAIBAN){
			pageIndexLeft=pageIndexLeft+1;
			return pageIndexLeft;
		}else if (mType==TYPE_YIBAN){
			pageIndexRight=pageIndexRight+1;
			return pageIndexRight;
		}
		return 1;
	}

	private int refreshPage() {
		if (mType==TYPE_DAIBAN){
			pageIndexLeft=1;
			return pageIndexLeft;
		}else if (mType==TYPE_YIBAN){
			pageIndexRight=1;
			return pageIndexRight;
		}
		return 1;
	}
}
