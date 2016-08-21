package com.DCHZ.TYLINCN.msglist;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IPageScrollListener;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

/***
 * 下拉刷新ListView控件控件
 * @date 2015/07/16
 */
public class MsgPage extends RelativeLayout {
	private final String TAG = "MsgPage";
	
	private ListViewV6 mListView = null;
	private NLPullRefreshView mRefreshView = null;
	private IRefreshListener mRefreshListener = null;
	private ListViewEmptyView listView_Empty;
	public MsgPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public MsgPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public MsgPage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void setRefreshListener(IRefreshListener refreshListener){
		this.mRefreshListener = refreshListener;
		if(mRefreshView != null){
			mRefreshView.setRefreshListener(refreshListener);
		}
		ListAdapter adapter = mListView.getAdapter();
		if(adapter != null && adapter instanceof BaseListAdapter){
			BaseListAdapter baseLA = (BaseListAdapter) adapter;
			baseLA.setRefreshListener(refreshListener);
		}
	}

	private void init(){
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.msg_page,this,true);
		initListView();
	}

	public ListView getListView(){
		return mListView;
	}

	/***
	 * 不挂有延迟的刷新操作
	 * @param isNoDelayFinish
	 */
	public void setNoDelayFinish(boolean isNoDelayFinish){
		mRefreshView.setNoDelayFinish(isNoDelayFinish);
	}
	
	/***
	 * 是否打开下拉刷新功能
	 * @param enable 默认为true可以下拉刷新，false为不能下拉刷新操作
	 */
	public void setEnablePullDown(boolean canPullDown){
		mRefreshView.setEnablePullDown(canPullDown);
	}
	
	public void setDividerHeight(int height){
		getListView().setDividerHeight(height);
	}
	
	public void setDivider(Drawable d){
		getListView().setDivider(d);
	}
	
	private void initListView(){
		mRefreshView = (NLPullRefreshView) this.findViewById(R.id.refresh_root);
		mListView = (ListViewV6) this.findViewById(R.id.listview);
//		View emptyView = this.findViewById(R.id.listview_emptyview);
//		mListView.setEmptyView(emptyView);
		listView_Empty=(ListViewEmptyView) this.findViewById(R.id.listView_Empty);
		mListView.setDividerHeight(0);
		mListView.setAdapter(null);
		mListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView listView, int scrollState) {
				if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
					Common.isPageStop = true;
					if(mRefreshListener != null){
						mRefreshListener.onPageStop();
					}
					int childCnt = mListView.getChildCount();
					for(int i = 0;i < childCnt;i++){
						View view = mListView.getChildAt(i);
						if(view != null && view instanceof BaseItemView){
							BaseItemView<Object> baseItemView = (BaseItemView<Object>) view;
							Object o = baseItemView.getMsg();
							baseItemView.setMsg(o);
						}
					}
					//滚动到底部事件通知
					if(listView.getLastVisiblePosition() == listView.getCount() - 1){
						if(mRefreshListener != null){
							mRefreshListener.reachListViewBottom();
						}
					}
				}else{
					Common.isPageStop = false;
				}
			}
			
			@Override
			public void onScroll(AbsListView listView, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				
			}
		});
	}

	/***
	 * 页面滑动停止
	 */
	public void onPageStop(){
		int childCnt = mListView.getChildCount();
		for(int i = 0;i < childCnt;i++){
			View view = mListView.getChildAt(i);
			if(view != null && view instanceof BaseItemView){
				BaseItemView<Object> baseItemView = (BaseItemView<Object>) view;
				Object o = baseItemView.getMsg();
				baseItemView.setMsg(o);
			}
		}
	}
	
	public void setIScrollListener(IPageScrollListener mListener){
		mListView.setIPageListener(mListener);
	}
	
	public void setListAdapter(BaseAdapter baseAdapter){
		mListView.setAdapter(baseAdapter);
		if(mRefreshListener != null && baseAdapter instanceof BaseListAdapter){
			BaseListAdapter baseLA = (BaseListAdapter) baseAdapter;
			baseLA.setRefreshListener(mRefreshListener);
		}
	}
	
	public void completeRefresh(boolean isSucc){
		if(mRefreshView != null){
			mRefreshView.finishRefresh(isSucc);
		}
	}
	
	public void recyle(){
		if(mRefreshView != null){
			mRefreshView.recyle();
		}
	}
	
	/***
	 * 添加HeaderView
	 * @param view
	 */
	public void addHeaderView(View view){
		getListView().addHeaderView(view);
	}
	public void addFooterView(View view){
		getListView().addFooterView(view);
	}
	/***
	 * 更新底部状态
	 * @param state
	 */
	public void updateState(int state){
		ListAdapter mAdpter = getListView().getAdapter();
		if(mAdpter != null && mAdpter instanceof BaseListAdapter){
			BaseListAdapter<?> bLA = (BaseListAdapter<?>) mAdpter;
			bLA.updateState(state);
		}
	}
	
	public void setEmpty(int type){
		ListAdapter mAdapter = mListView.getAdapter();
		if(mAdapter != null && mAdapter instanceof BaseListAdapter){
			BaseListAdapter<?> bLA = (BaseListAdapter<?>)mAdapter;
			bLA.reSetList(null);
		}
		listView_Empty.setType(type);
		mListView.setEmptyView(listView_Empty);
	}
	
	public void removeEmptyView(){
		mListView.removeView(listView_Empty);
	}
}
