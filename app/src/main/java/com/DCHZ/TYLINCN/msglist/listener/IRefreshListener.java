package com.DCHZ.TYLINCN.msglist.listener;

import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;


public abstract class IRefreshListener {
	//下拉刷新接口
	public void onRefresh(NLPullRefreshView view){};
	//bottom点击回调
	public void bottomClick(int state){};
	//reach the bottom of listview
	public void reachListViewBottom(){};
	//on page stop
	public void onPageStop(){};
}
