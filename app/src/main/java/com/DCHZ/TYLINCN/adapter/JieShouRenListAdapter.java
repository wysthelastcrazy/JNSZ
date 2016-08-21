package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.VJieShouRenListEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.JieShouRenListItemView;

public class JieShouRenListAdapter extends BaseListAdapter<VJieShouRenListEntity>{
	private IGongShiShenPiClickListener mListener;
	public JieShouRenListAdapter(ArrayList<VJieShouRenListEntity> mList) {
		super(mList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseItemView<VJieShouRenListEntity> getItemView(VJieShouRenListEntity t) {
		// TODO Auto-generated method stub
		JieShouRenListItemView itemView=new JieShouRenListItemView(Global.getContext());
		itemView.setListener(mListener);
		return itemView;
	}
	public void setLisntener(IGongShiShenPiClickListener mListener){
		this.mListener=mListener;
	}
}
