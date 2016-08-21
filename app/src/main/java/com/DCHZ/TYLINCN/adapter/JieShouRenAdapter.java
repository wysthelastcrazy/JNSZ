package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.JieShouRenItemView;

public class JieShouRenAdapter extends BaseListAdapter<VJieShouRenEntity>{

	public JieShouRenAdapter(ArrayList<VJieShouRenEntity> mList) {
		super(mList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseItemView<VJieShouRenEntity> getItemView(VJieShouRenEntity t) {
		// TODO Auto-generated method stub
		JieShouRenItemView itemView=new JieShouRenItemView(Global.getContext());
		return itemView;
	}

}
